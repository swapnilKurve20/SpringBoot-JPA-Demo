package com.example.demo.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TagResponseDto;

@RestController
@RequestMapping("/tags")
public class TagsController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);
	
	@GetMapping
	public List<TagResponseDto> getAllTags() {
		LOGGER.info("Started get all tags call.");
		List<TagResponseDto> tagList = null;
		try {
			tagList = getTagService().getAllTags();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Completed get all tags call.");
		return tagList;
	}

}
