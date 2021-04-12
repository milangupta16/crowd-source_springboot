package com.techgeeknext.controller;

import com.techgeeknext.config.JwtTokenUtil;
import com.techgeeknext.model.ItemsDao;
import com.techgeeknext.model.ItemsDto;
import com.techgeeknext.model.UserDao;
import com.techgeeknext.repository.ItemsRepository;
import com.techgeeknext.repository.UserRepository;
import com.techgeeknext.service.UserInfo;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    UserInfo userInfo;

    @PostMapping("/requestItem")
    public ResponseEntity<?> requestItem(HttpServletRequest request, @RequestBody ItemsDto items) {
        UserDao userDao = userRepository.findByUsername(userInfo.getUserName(request));

        System.out.println(userDao.getEmail());
        items.setReq(1);
        ItemsDao itemsDao = new ItemsDao();
        itemsDao.setBase_price(items.getBase_price());
        itemsDao.setItem_name(items.getItem_name());
        itemsDao.setDescription(items.getDescription());
        itemsDao.setReq(items.getReq());

        userDao.getItems().add(itemsDao);

        return ResponseEntity.ok(userRepository.save(userDao));
    }

    @PostMapping("/postItem/{item_id}")
    public ResponseEntity<?> postItem(HttpServletRequest request, @RequestBody ItemsDto items,@PathVariable Integer item_id) {
        UserDao userDao1 = userRepository.findByUsername(userInfo.getUserName(request));
        items.setReq(0);
        System.out.println(userDao1.getEmail());
        Optional<ItemsDao> item =itemsRepository.findById(item_id);
        ItemsDao itemsDao = new ItemsDao();
        itemsDao.setBase_price(items.getBase_price());
        itemsDao.setItem_name(items.getItem_name());
        itemsDao.setDescription(items.getDescription());
        itemsDao.setReq(items.getReq());
        System.out.println(item.get().getItem_id());
        //itemsDao.setRequested_item(item.get());
        item.get().getPosted_items().add(itemsDao);
        userDao1.getItems().add(itemsDao);

        return ResponseEntity.ok(userRepository.save(userDao1));
    }

    @GetMapping("/getAllItems")
    public List<ItemsDao> getAllItems() {
        System.out.println("In getAllItems");

        return itemsRepository.findAll();
    }

    @GetMapping("/getAllPostedItems/{item_id}")
    public Set<ItemsDao> getAllPostedItems(HttpServletRequest request,@PathVariable Integer item_id){
        Optional<ItemsDao> item=itemsRepository.findById(item_id);
        System.out.println(item.get().getDescription());
        System.out.println(item.get().getPosted_items());
        Set<ItemsDao> Posted= item.get().getPosted_items();
        for(ItemsDao item1 : Posted)
        {
            System.out.println(item1.getDescription());
        }
        return (Set<ItemsDao>) item.get().getPosted_items();
    }

    @GetMapping("/getUserItems")
    public List<ItemsDao> getUserItems(HttpServletRequest request) {
        UserDao userDao = userRepository.findByUsername(userInfo.getUserName(request));
        List<ItemsDao> items = userDao.getItems();
        return items;
    }


}
