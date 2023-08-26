package com.rentkaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentkaro.pojos.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}
