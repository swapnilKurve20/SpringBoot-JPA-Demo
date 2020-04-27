package com.example.demo.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tags;
import com.example.demo.rest.TagsController;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class TagDaoImpl extends BaseDao implements TagDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);
	
	@Override
	public Tags getTag(Long tagId) {
		LOGGER.info("Started get tag call.");
		Tags tag=null;
		try {
			tag = (Tags) getSession().get(Tags.class, tagId);
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed get tag call.");
		return tag;
	}

	@Override
	public Tags getTagByName(String tagname) {
		LOGGER.info("Started get tag by name call.");
		Tags tag = null;
		String getTagSql = "Select * from tags where name='" + tagname + "'";
		try {

			NativeQuery<Tags> queryObj = getSession().createNativeQuery(getTagSql);
			List<Tags> tagList = queryObj.list();
			tag = tagList.size() == 1 ? tagList.get(0) : null;
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed get tag by name call.");
		return tag;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Tags> getAllTags() {
		LOGGER.info("Started get all tags call.");
		String getTagSql = "Select * from tags";
		List<Tags> tags = new ArrayList<>();
		try {
			NativeQuery<Tags> queryObj = getSession().createNativeQuery(getTagSql);
			List<Tags> tagList = queryObj.getResultList();

			Iterator it = tagList.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				Tags tag = new Tags();
				tag.setId(((BigInteger) line[0]).longValue());
				tag.setName(line[1].toString());
				tags.add(tag);
			}

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed get all tags call.");
		return tags;
	}
}
