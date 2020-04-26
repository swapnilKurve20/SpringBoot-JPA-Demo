package com.example.demo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TagResponseDto;

@RestController
@RequestMapping("/tags")
public class TagsController extends BaseController {

	@GetMapping
	public List<TagResponseDto> getAllTags() {

		List<TagResponseDto> tagList = null;
		try {
			tagList = getTagService().getAllTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagList;
	}

}
