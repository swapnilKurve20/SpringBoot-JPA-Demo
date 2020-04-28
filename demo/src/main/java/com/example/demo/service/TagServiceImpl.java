package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TagResponseDto;
import com.example.demo.model.Tags;
import com.example.demo.rest.TagsController;

@Service
@Transactional(transactionManager="hibernateTransactionManager",rollbackFor=Exception.class) // specify transaction manager
public class TagServiceImpl extends BaseService implements TagService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);


	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserService userService;
	
	public TagResponseDto getTag(Long tagId) {
		LOGGER.info("Started get tag call.");
		TagResponseDto tagResponseDto = new TagResponseDto();
		try {
			Tags tag = getTagDao().getTag(tagId);
			tagResponseDto.setId(tag.getId());
			tagResponseDto.setName(tag.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Completed get tag call.");
		return tagResponseDto;
	}

	public List<TagResponseDto> getAllTags() {

		LOGGER.info("Started get all tags call.");
		List<TagResponseDto> tagList = new ArrayList<TagResponseDto>();
		try {
			List<Tags> allTags = getTagDao().getAllTags();

			for (Tags tag : allTags) {
				TagResponseDto dto = new TagResponseDto();
				dto.setId(tag.getId());
				dto.setName(tag.getName());
				tagList.add(dto);
			}

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed get all tags call.");
		return tagList;
	}
	
	public String testTrnsaction() throws Exception {
		LOGGER.info("Started test transaction.");
	    userProfileService.insert1();
		if (true) {
			throw new Exception("Testing Transaction..");
		}
		userService.insert2();
		LOGGER.info("Completed test transaction.");
		return "transaction op";
	}
}
