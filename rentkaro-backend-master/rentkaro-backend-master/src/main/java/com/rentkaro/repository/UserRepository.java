package com.rentkaro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentkaro.pojos.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserEmailAndUserPassword(String email,String pass);

	Optional<User> findByUserEmailAndAuthKey(String email,String key);

	Optional<User> findByUserEmail(String email);

//	Optional<User> findByUserEmailAndUserPasswordAndAuthKeyIsNull(String userEmail, String userPassword);
	
		
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.ownedProductList WHERE u.id = :userId")
    Optional<User> findByIdWithOwnedProductList(@Param("userId") Long userId);
	
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.orderList WHERE u.id = :userId")
    Optional<User> findByIdWithOrderList(@Param("userId") Long userId);
}
