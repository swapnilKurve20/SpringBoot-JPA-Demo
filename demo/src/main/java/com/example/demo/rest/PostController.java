package com.example.demo.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.model.UserProfiles;
import com.example.demo.pojo.PostDetails;
import com.example.demo.pojo.PostDetails.TagsDetails;
import com.example.demo.repo.IPostRepo;
import com.example.demo.repo.IUserProfiles;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	IUserProfiles userProfileRepo;
	
	@Autowired
	IPostRepo postRepo;
	
	
	@PostMapping("/{userProfileId}")
	public String addPost(@PathVariable(value = "userProfileId") String id, @RequestBody PostDetails post) throws Exception {
		
		Optional<UserProfiles> optional = userProfileRepo.findById(Long.parseLong(id));
		
		UserProfiles up = optional.get();
		Posts p = null;
		if(up.getId() != null) {
			p = new Posts(post.getTitle(), post.getDescription());
			p.setUserProfile(up);

			Set<Tags> tags = new HashSet<>();
			for(TagsDetails s : post.getTags()) {
				Tags tag = new Tags();
				tag.setId(s.getId());
				tag.setName(s.getName());
				
				tag.getPosts().add(p);
				tags.add(tag);
			}
//			p.setTags(tags);
			p.getTags().addAll(tags);
			
			p = postRepo.save(p);
		}
		
		if(p == null)
			throw new Exception("Invalid post");
		else
			return String.valueOf(p.getId());
	}
	
	
	@GetMapping("/getAllPosts")
	public List<Posts> getAllPosts(){
		return postRepo.findAll();
	}
	
	
}
