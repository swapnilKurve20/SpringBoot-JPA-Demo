package com.example.demo.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tags;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class TagDaoImpl extends BaseDao implements TagDao {

	@Override
	public Tags getTag(Long tagId) {
		return (Tags) getSession().get(Tags.class, tagId);
	}

	@Override
	public Tags getTagByName(String tagname) {

		Tags tag = null;
		String getTagSql = "Select * from tags where name='" + tagname + "'";
		try {

			NativeQuery<Tags> queryObj = getSession().createNativeQuery(getTagSql);
			List<Tags> tagList = queryObj.list();
			tag = tagList.size() == 1 ? tagList.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Tags> getAllTags() {

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
			e.printStackTrace();
		}
		return tags;
	}
}
