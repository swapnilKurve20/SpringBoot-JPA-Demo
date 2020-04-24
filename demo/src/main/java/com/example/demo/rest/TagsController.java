package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tags;
import com.example.demo.pojo.PostDetails.TagsDetails;
import com.example.demo.repo.ITagsRepo;

@RestController
@RequestMapping("/tags")
public class TagsController {

	//@Autowired
	private ITagsRepo tagsRepo;
	
	
	@GetMapping("/getAllTags")
	public List<TagsDetails> getAllTags(){
		List<TagsDetails> tags = new ArrayList<>();
		for(Tags t : tagsRepo.findAll()) {
			tags.add(new TagsDetails(t.getId(), t.getName()));
		}
		return tags;
	}
}
