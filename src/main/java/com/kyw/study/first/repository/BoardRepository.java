package com.kyw.study.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyw.study.first.entity.Board;
public interface BoardRepository extends JpaRepository<Board, String> {

}
