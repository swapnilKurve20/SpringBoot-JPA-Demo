package com.example.demo.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.demo.dto.PostRequestDto;
import com.example.demo.model.Posts;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

	@PostMapping("/{userProfileId}")
	public ResponseEntity<Object> addPost(@PathVariable(value = "userProfileId") String id,
			@RequestBody PostRequestDto post) throws Exception {
		LOGGER.info("Started add post call.");

		Posts posts = null;
		try {
			posts = getPostService().addPost(Long.parseLong(id), post);
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed add post call.");
		return new ResponseEntity<>(posts.getId(), HttpStatus.OK);
	}

	@GetMapping("/{userProfileId}")
	public List<PostRequestDto> getAllPosts(@PathVariable(value = "userProfileId") String id) {

		return getPostService().getAllPostsByProfile(id);
	}

	@DeleteMapping("/{userProfileId}")
	public ResponseEntity<Object> removePostById(@PathVariable(value = "userProfileId") String userProfileId,
			@RequestParam(value = "postId") String id) {

		getPostService().deleteById(userProfileId, id);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PutMapping("/{userProfileId}")
	public ResponseEntity<Object> updatePost(@PathVariable(value = "userProfileId") String userProfileId,
			@RequestBody PostRequestDto post) {

		try {
			getPostService().updatePost(userProfileId, post);
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
