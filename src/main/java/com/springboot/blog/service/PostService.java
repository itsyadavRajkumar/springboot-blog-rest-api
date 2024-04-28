package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponce;

public interface PostService {
	PostDto createPost(PostDto postDto);
	
//	List<PostDto> getAllPosts();
//	List<PostDto> getAllPosts(int pageNo, int pageSize);
//	PostResponce getAllPosts(int pageNo, int pageSize);
//	PostResponce getAllPosts(int pageNo, int pageSize, String sortBy);
	PostResponce getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	void deletePostById(long id);
}
