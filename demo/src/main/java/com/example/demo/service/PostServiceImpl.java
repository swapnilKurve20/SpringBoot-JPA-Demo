package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.model.UserProfiles;

@Service
@Transactional
public class PostServiceImpl extends BaseService implements PostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

	public Posts addPost(Long id, PostRequestDto post) {
		LOGGER.info("Started add post call.");
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
		LOGGER.info("Completed add post call.");
		return p;
	}

	@Override
	public List<PostRequestDto> getAllPostsByProfile(String profileId) {

		Map<String, Posts> posts = getPostDao().getAllPostsByProfile(profileId);
		List<PostRequestDto> postDtos = new ArrayList<>();
		if (posts == null)
			throw new DataNotFoundException("Posts not found for Profile id " + profileId);
		else {

			for (Posts p : posts.values()) {
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
	public void updatePost(String userProfileId, PostRequestDto requestDto) throws Exception {

		Posts post = getPostDao().findById(Long.valueOf(requestDto.getId()));

		if (post == null)
			throw new DataNotFoundException("Post not found for id " + requestDto.getId());

		if (post.getAuthor().getId() == Long.valueOf(userProfileId)) {
			UserProfiles profile = getUserProfileDao().getUserProfile(Long.valueOf(requestDto.getLikedBy().getId()));
			if (profile == null)
				throw new DataNotFoundException("User profile not found for id " + requestDto.getId());

			getPostDao().updatePost(requestDto.getLikedBy().getId(), post);

		} else {
			throw new Exception("Invalid post");
		}
	}
}
