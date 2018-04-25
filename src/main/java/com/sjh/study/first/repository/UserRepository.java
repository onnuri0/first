package com.sjh.study.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjh.study.first.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
