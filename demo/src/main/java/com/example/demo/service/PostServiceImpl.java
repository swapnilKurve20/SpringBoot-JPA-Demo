package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.dto.UserProfilesDto;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.model.UserProfiles;

@Service
public class PostServiceImpl extends BaseService implements PostService {

	public Posts addPost(Long id, PostRequestDto post) {

		Posts p = null;
		try {
			UserProfiles up = getUserProfileDao().getUserProfile(id);

			if (up.getId() != null) {
				p = new Posts(post.getTitle(), post.getDescription());
				p.setAuthor(up);

				Set<Tags> tags = new HashSet<>();

				for (TagsDetails s : post.getTags()) {
					Tags tag = getTagDao().getTagByName(s.getName());
					if (tag == null) {
						tag = new Tags();
						tag.setName(s.getName());
						tag.getPosts().add(p);
						tags.add(tag);
					} else {
						tag = getTagDao().getTag(tag.getId());
						tag.getPosts().add(p);
						tags.add(tag);
					}
				}
				p.getTags().addAll(tags);
				p = getPostDao().addPost(p);
				if (p == null)
					throw new Exception("Invalid post");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public List<PostRequestDto> getAllPostsByProfile(String profileId) {

		List<Posts> posts = getPostDao().getAllPostsByProfile(profileId);
		List<PostRequestDto> postDtos = new ArrayList<>();
		if (posts == null)
			throw new DataNotFoundException("Posts not found for Profile id " + profileId);
		else {
			for (Posts p : posts) {
				postDtos.add(getModelMapper().map(p, PostRequestDto.class));
			}
		}

		return postDtos;
	}

	@Override
	public void deleteById(String profileId, String postId) {
		getPostDao().deleteById(profileId, postId);
	}

	@Override
	public void updatePost(String userProfileId, PostRequestDto requestDto) {
		
		Posts post = getPostDao().findById(Long.valueOf(requestDto.getId()));
		if(post == null)
			throw new DataNotFoundException("Post not found for id "+requestDto.getId());
		
		Set<UserProfiles> profiles = new HashSet<>();
		for(UserProfilesDto up : requestDto.getLikedBy()) {
			profiles.add(new UserProfiles(up.getGender(), up.getAddress()));
		}
		
		post.setLikedBy(profiles);
		
		getPostDao().updatePost(userProfileId, post);
		
		
		
	}
}
