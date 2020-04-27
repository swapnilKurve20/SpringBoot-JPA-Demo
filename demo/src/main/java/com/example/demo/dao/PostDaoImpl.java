package com.example.demo.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Posts;
import com.example.demo.model.Tags;
import com.example.demo.model.UserProfiles;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PostDaoImpl extends BaseDao implements PostDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoImpl.class);

	@Autowired
	private TagDao tagDao;

	@Autowired
	private UserProfileDao profileDao;

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
	public Map<String, Posts> getAllPostsByProfile(String profileId) {
		LOGGER.info("Completed add post by profile call.");
		String q1 = "Select p.id,p.description, p.title, p.user_profile_id, pt.post_id as tagsPostId, tag_id, l.post_id as likedPostId, l.user_profile_id as likeByUser from posts  p inner join post_tags pt on p.user_profile_id = 2 AND p.id = pt.post_id left join liked_by l on p.id = l.post_id";
		List<Posts> posts = new ArrayList<>();
		Map<String, Posts> postMap = new HashMap();
		try {

			NativeQuery<Posts> queryObj = getSession().createNativeQuery(q1);
			List<Posts> tagList = queryObj.getResultList();

			Iterator it = tagList.iterator();
			while (it.hasNext()) {
				Object[] line = (Object[]) it.next();
				Posts post = new Posts();
				post.setId(((BigInteger) line[0]).longValue());
				post.setDescription(String.valueOf(line[1]));
				post.setTitle(String.valueOf(line[2]));
				UserProfiles likedBy = profileDao.getUserProfile(Long.parseLong(String.valueOf(line[7])));
				Tags tag = tagDao.getTag(Long.parseLong(String.valueOf(line[5])));
				if (postMap.keySet().contains(post.getTitle())) {
					post = postMap.get(post.getTitle());

				}
				post.getLikedBy().add(likedBy);
				post.getTags().add(tag);
				postMap.put(post.getTitle(), post);
				posts.add(post);
			}

		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
		}
		LOGGER.info("Completed add post by profile call.");
		return postMap;
	}

	@Override
	public Posts findById(Long id) {
		LOGGER.info("Started find post by id call.");
		Posts p = getSession().get(Posts.class, id);

		LOGGER.info("Completed find post by id call.");
		return p;
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
			String updatePostSql = "Insert into liked_by (post_id, user_profile_id) values (" + post.getId() + ","
					+ profileId + ")";
			NativeQuery createNativeQuery = getSession().createNativeQuery(updatePostSql);
			int executeUpdate = createNativeQuery.executeUpdate();
		} catch (Exception e) {
			getCustomExceptionHandler().logExcepton(e);
			e.printStackTrace();
		}

		LOGGER.info("Completed update post by id call.");
	}

}
