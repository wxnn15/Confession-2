package net.javaguides.springboot.services;

import java.util.List;
import java.util.Queue;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.javaguides.springboot.model.Post;
import net.javaguides.springboot.model.SubmissionPost;

public interface PostService {

	Queue<SubmissionPost> saveSubmissionPost(String text, Long replyId) throws JsonProcessingException;
	void save();
	Queue<SubmissionPost> listAllSubmittedPost();
	List<Post> listAllPost();
}
