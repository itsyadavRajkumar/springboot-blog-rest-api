package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponce;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	private ModelMapper mapper;
	
	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
//		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
//		convert DTO Entity
		Post post = mapToEntity(postDto);

//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		
		Post newPost = postRepository.save(post);
		
//		convert entity to DTO
		PostDto postResponse = mapToDTO(newPost);
//		PostDto postResponse = new PostDto();
//		postResponse.setId(newPost.getId());
//		postResponse.setTitle(newPost.getTitle());
//		postResponse.setDescription(newPost.getDescription());
//		postResponse.setContent(newPost.getContent());
		
		return postResponse;
	}

	@Override
//	public List<PostDto> getAllPosts() {
//	public List<PostDto> getAllPosts(int pageNo, int pageSize) {
//	public PostResponce getAllPosts(int pageNo, int pageSize) {
//	public PostResponce getAllPosts(int pageNo, int pageSize, String sortBy) {
	public PostResponce getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		
//		create pageable instance
//		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		
		
		/*
//		For Ascending Order
		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//		For Descending Order
//		PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		*/
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		
//		List<Post> posts = postRepository.findAll();
		Page<Post> posts = postRepository.findAll(pageable);
		
//		get content for page object
		List<Post> listOfPosts = posts.getContent();
		
//		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
//		return listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(content);
		postResponce.setPageNo(posts.getNumber());
		postResponce.setPageSize(posts.getSize());
		postResponce.setTotalElements(posts.getTotalElements());
		postResponce.setTotalPages(posts.getTotalPages());
		postResponce.setLast(posts.isLast());
		
		return postResponce;
	}
	

	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDTO(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// TODO Auto-generated method stub
//		get post by id from  the database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		
		Post updatedPost = postRepository.save(post);
		
		return mapToDTO(updatedPost);
	}

	@Override
	public void deletePostById(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
	}

	
//	Convert Entity to DTO
	private PostDto mapToDTO(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
					
//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}

	
//	Convert DTO to Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
		
//		Post post = tent(postDto.getContent());
		
		return post;
	}
	

}
