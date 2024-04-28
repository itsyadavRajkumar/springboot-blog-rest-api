package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponce;
import com.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
//		super();
		this.postService = postService;
	}
	
//	create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
//	get all post by api
	@GetMapping
	/*
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}
	*/
//	Using Pagination
/*	public List<PostDto> getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
	)
	*/
	/*
	public PostResponce getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
	) 
	*/
	/*
	public PostResponce getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
	)
	*/
	public PostResponce getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "id", required = false) String sortDir
	) {
//		return postService.getAllPosts();
//		return postService.getAllPosts(pageNo, pageSize);
//		return postService.getAllPosts(pageNo, pageSize, sortBy);
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
//	get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
//	update post by id rest api
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
		PostDto postResponce = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponce, HttpStatus.OK);
	}
	
//	delete post by id rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
		postService.deletePostById(id);
		return new ResponseEntity<>("Post entity Deleted Successfully!", HttpStatus.OK);
	}
}
