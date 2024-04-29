package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
//		super();
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}


	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		Comment comment = mapToEntity(commentDto);
		
//		retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
//		set post to comment entity
		comment.setPost(post);
		
//		comment entity to DB
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		commentDto.setName(comment.getName());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		comment.setName(commentDto.getName());
		return comment;
	}


	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		// TODO Auto-generated method stub
		
//		retrieve to comments by postId
		List<Comment> comments = commentRepository.findByPostId(postId);
		
//		convert list of comment entities to list of comment dto's  
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}


	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		
//		retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
//		retrieve comment by id
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		return mapToDto(comment);
	}

}
