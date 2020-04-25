package com.example.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TagResponseDto;

@RestController
//@RequestMapping("/tags")
public class TagsController extends BaseController {

	@GetMapping(path="/getalltags")
	public List<TagResponseDto> getAllTags(){
		
		List<TagResponseDto> tagList = null;
		try {
			tagList = getTagService().getAllTags();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tagList;
	}
	
	public TagResponseDto getTag(@RequestParam("id") Long tagId) {
		return null;
	}
}
