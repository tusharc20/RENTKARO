package com.rentkaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentkaro.pojos.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
