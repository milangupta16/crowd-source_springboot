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
import java.util.*;

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
        itemsDao.setRequested_item(item.get());
        item.get().getPosted_items().add(itemsDao);
        userDao1.getItems().add(itemsDao);

        return ResponseEntity.ok(userRepository.save(userDao1));
    }

    @GetMapping("/getAllItems")
    public List<ItemsDao> getAllItems(HttpServletRequest request) {
        System.out.println("In getAllItems");
        List<ItemsDao> item=itemsRepository.findDistinctByReqIs(1);
            List<ItemsDao> item_user = new ArrayList<ItemsDao>();
        UserDao userDao = userRepository.findByUsername(request.getParameter("user"));
        List<ItemsDao> uitems = userDao.getItems();
        
         List<Integer> item_user_id = new ArrayList<Integer>();
        for(ItemsDao item1 : uitems)
        {
          
                item_user_id.add(item1.getItem_id());
          
        }

        for(ItemsDao item1 : item)
        {
            System.out.println(item1.getDescription());
            if(!item_user_id.contains(item1.getItem_id()))
                item_user.add(item1);
          
        }
         
        return item_user;
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

    @GetMapping("/getUserReqItems")
    public List<ItemsDao> getUserReqItems(HttpServletRequest request) {
           System.out.println("In getAll REqItems");
        UserDao userDao = userRepository.findByUsername(request.getParameter("user"));
        List<ItemsDao> items = userDao.getItems();
        List<ItemsDao> item_req = new ArrayList<ItemsDao>();
        for(ItemsDao i:items)
        {
            if(i.getReq()==1)
                item_req.add(i);
        }

        return item_req;
    }


     @GetMapping("/isDuplicateUser")
    public boolean isDuplicateUser(HttpServletRequest request) {
        System.out.println("In user duplicacy");
       
        UserDao userDao = userRepository.findByUsername(request.getParameter("user"));
     
    if(userDao == null){
      return true;
    }
    else{
      return false;
    }


    }


}