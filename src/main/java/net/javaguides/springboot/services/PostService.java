package net.javaguides.springboot.services;

import java.util.List;

import net.javaguides.springboot.model.Post;

public interface PostService {

	List<Post> save(Long id, Long replyId, String text);	 
		 
	 List<Post> listAllPost();
	 
	 Post getById(Long id);
	 
	 void deletePost(long id);

	 
	

}
