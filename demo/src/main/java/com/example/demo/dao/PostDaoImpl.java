package com.example.demo.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Posts;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PostDaoImpl extends BaseDao implements PostDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoImpl.class);

	public Posts addPost(Posts post) {
		LOGGER.info("Started add post call.");
		Posts savedPost = null;
		try {
			Serializable id = getSession().save(post);
			savedPost = (Posts) getSession().get(Posts.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Completed add post call.");
		return savedPost;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Posts> getAllPostsByProfile(String profileId) {
		LOGGER.info("Completed add post by profile call.");
		String getPostSql = "Select * from posts where user_profile_id = " + profileId;
		List<Posts> posts = new ArrayList<>();
		try {

			NativeQuery<Posts> queryObj = getSession().createNativeQuery(getPostSql);
			List<Posts> tagList = queryObj.getResultList();

			Iterator it = tagList.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				Posts post = new Posts();
				post.setId(((BigInteger) line[0]).longValue());
				post.setDescription(String.valueOf(line[1]));
				post.setTitle(String.valueOf(line[2]));
				posts.add(post);
			}

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed add post by profile call.");
		return posts;
	}

	@Override
	public Posts findById(Long id) {
		LOGGER.info("Started find post by id call.");
		String getPostSql = "Select * from posts where id = " + id;
		Posts post = null;
		try {

			NativeQuery<Posts> queryObj = getSession().createNativeQuery(getPostSql);
			List<Posts> postList = queryObj.getResultList();
			Iterator it = postList.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				post = new Posts();
				post.setId(((BigInteger) line[0]).longValue());
				post.setDescription(String.valueOf(line[1]));
				post.setTitle(String.valueOf(line[2]));
			}
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed find post by id call.");
		return post;
	}

	@Override
	public void deleteById(String profileId, String postId) {
		
		LOGGER.info("Started delete post by id call.");
		
		String deletePostSql = "Delete from posts where id = " + postId + "AND user_profile_id = " + profileId;
		try {
			NativeQuery<Posts> queryObj = getSession().createNativeQuery(deletePostSql);
			queryObj.executeUpdate();

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
	}

	@Override
	public void updatePost(String profileId, Posts post) {
		LOGGER.info("Started update post by id call.");
		try {
			getSession().update(post);
		}catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		
		LOGGER.info("Completed update post by id call.");
	}

}
