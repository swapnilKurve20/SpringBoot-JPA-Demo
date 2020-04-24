package com.example.demo.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.pojo.PostDetails;
import com.example.demo.pojo.PostDetails.TagsDetails;
import com.example.demo.repo.IPostRepo;
import com.example.demo.repo.ITagsRepo;
import com.example.demo.repo.IUserProfiles;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

	//@Autowired
	IUserProfiles userProfileRepo;

	//@Autowired
	IPostRepo postRepo;

	//@Autowired
	ITagsRepo tagRepo;

	@PostMapping("/{userProfileId}")
	public String addPost(@PathVariable(value = "userProfileId") String id, @RequestBody PostRequestDto post)
			throws Exception {

		Posts posts = null;
		try {
			posts = getPostService().addPost(Long.parseLong(id), post);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if (posts == null)
			throw new Exception("Invalid post");
		else
			return String.valueOf(posts.getId());
	}

	@GetMapping("/{userProfileId}/getAllPosts")
	public List<PostDetails> getAllPosts(@PathVariable(value = "userProfileId") String id) {
		
		List<PostDetails> posts = new ArrayList<>();
		Set<TagsDetails> tags = new HashSet<>();
		for (Posts p : postRepo.findByUserProfile(Long.parseLong(id))) {
			tags.clear();
			for (Tags t : p.getTags()) {
				tags.add(new TagsDetails(t.getId(), t.getName()));
			}
			posts.add(new PostDetails(p.getTitle(), p.getDescription(), tags));
		}
		return posts;
	}

	@DeleteMapping("/{userProfileId}/removePostById")
	public void removePostById(@PathVariable(value = "userProfileId") String userProfileId,
			@RequestParam(value = "postId") String id) {

		Optional<Posts> posts = postRepo.findById(Long.parseLong(id));
		if (posts.isPresent() && (posts.get().getUserProfile().getId() == Long.parseLong(userProfileId))) {
			postRepo.deleteById(Long.parseLong(id));
		}

	}

}
