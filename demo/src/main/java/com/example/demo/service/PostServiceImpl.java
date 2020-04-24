package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.model.UserProfiles;


@Service
public class PostServiceImpl extends BaseService implements PostService {

	public Posts addPost(Long id, PostRequestDto post) {
		
		UserProfiles up = getUserProfileDao().getUserProfile(id);

		Posts p = null;
		if (up.getId() != null) {
			p = new Posts(post.getTitle(), post.getDescription());
			p.setUserProfile(up);

			Set<Tags> tags = new HashSet<>();

			for (TagsDetails s : post.getTags()) {
				Tags tag = tagDao.getTagByName(s.getName());
				if (tag == null) {
					tag = new Tags();
					//tag.setId(s.getId());
					tag.setName(s.getName());
					tag.getPosts().add(p);
					tags.add(tag);
				} else {
					tag = tagDao.getTag(tag.getId());
					tag.getPosts().add(p);
					tags.add(tag);
				}
			}
			p.getTags().addAll(tags);

			p = getPostDao().addPost(p);
		}

		/*
		 * if (p == null) throw new Exception("Invalid post"); else
		 */
			return p;
	}
}
