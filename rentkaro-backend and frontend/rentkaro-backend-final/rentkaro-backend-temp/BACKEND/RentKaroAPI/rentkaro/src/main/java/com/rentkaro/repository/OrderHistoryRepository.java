package com.rentkaro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentkaro.pojos.OrderHistory;
import com.rentkaro.pojos.Product;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	Optional<List<OrderHistory>>  findByImgPath1(String imgPath1);

}
