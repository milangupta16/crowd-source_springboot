package com.techgeeknext.repository;

import com.techgeeknext.model.ItemsDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<ItemsDao, Integer> {

    List<ItemsDao> findDistinctByReqIs(Integer req);
}