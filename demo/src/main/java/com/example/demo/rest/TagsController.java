package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostRequestDto.TagsDetails;
import com.example.demo.dto.TagResponseDto;
import com.example.demo.model.Tags;
import com.example.demo.repo.ITagsRepo;

@RestController
//@RequestMapping("/tags")
public class TagsController extends BaseController {

	//@Autowired
	private ITagsRepo tagsRepo;
	
	/*
	 * @GetMapping("/getAllTags") public List<TagsDetails> getAllTagsd(){
	 * List<TagsDetails> tags = new ArrayList<>(); for(Tags t : tagsRepo.findAll())
	 * { tags.add(new TagsDetails(t.getId(), t.getName())); } return tags; }
	 */
	
	@GetMapping(path="/getalltags")
	public List<TagResponseDto> getAllTags(){
		
		return getTagService().getAllTags();

	}
	
	public TagResponseDto getTag(@RequestParam("id") Long tagId) {
		return null;
	}
}
