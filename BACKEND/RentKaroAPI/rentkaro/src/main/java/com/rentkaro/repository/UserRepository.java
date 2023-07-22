package com.rentkaro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rentkaro.pojos.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserEmailAndUserPassword(String email,String pass);
}
