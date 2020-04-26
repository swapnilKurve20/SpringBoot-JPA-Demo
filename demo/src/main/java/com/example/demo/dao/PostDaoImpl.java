package com.example.demo.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Posts;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PostDaoImpl extends BaseDao implements PostDao {

	public Posts addPost(Posts post) {
		Posts savedPost = null;
		try {
			Serializable id = getSession().save(post);
			savedPost = (Posts) getSession().get(Posts.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savedPost;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Posts> getAllPostsByProfile(String profileId) {
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
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public Posts findById(Long id) {

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
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public void deleteById(String profileId, String postId) {
		String deletePostSql = "Delete from posts where id = " + postId + "AND user_profile_id = " + profileId;

		try {
			NativeQuery<Posts> queryObj = getSession().createNativeQuery(deletePostSql);
			queryObj.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePost(String profileId, Posts post) {

		getSession().update(post);

	}

}
