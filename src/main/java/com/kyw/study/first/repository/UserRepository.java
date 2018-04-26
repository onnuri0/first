package com.kyw.study.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyw.study.first.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
