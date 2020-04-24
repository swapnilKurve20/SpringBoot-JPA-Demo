package com.example.demo.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tags;

@Repository
@Transactional
public class TagDaoImpl extends BaseDao implements TagDao {

	@Override
	public Tags getTag(Long tagId) {
		return (Tags) getSession().get(Tags.class, tagId);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public Tags getTagByName(String tagname) {
		
		String getTagSql = "Select * from tags where name='"+tagname+"'";
		
		SQLQuery<Tags> queryObj = getSession().createSQLQuery(getTagSql);
		List<Tags> tagList = queryObj.list();
		Tags tag= tagList.size() ==1 ? tagList.get(0) : null;
		return tag;
	}
	
	@Override
	public List<Tags> getAllTags() {
		
		String getTagSql = "Select * from tags";
		List<Tags> tags= new ArrayList<>();
		SQLQuery queryObj = getSession().createSQLQuery(getTagSql);
		List<Object[]> tagList = queryObj.getResultList();
		
		Iterator it = tagList.iterator();
		while(it.hasNext()){
		     Object[] line = (Object[]) it.next();
		     Tags tag = new Tags();
		     tag.setId(((BigInteger) line[0]).longValue());
		     tag.setName(line[1].toString());
		     tags.add(tag);
		}
		return tags;
	}
}
