package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
//	No need to provide any data here 
//	bcz in jpa repository it have been already taken all 
// 	no need to explicit derived here
}
