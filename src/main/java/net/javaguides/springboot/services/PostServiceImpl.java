package net.javaguides.springboot.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Post;
import net.javaguides.springboot.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	private List<Post> postList = new ArrayList<>();
	
	@Autowired
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	@Override
	public List<Post> save(Long id, Long replyId, String text) {
		// TODO Auto-generated method stub
		Post post = new Post(id, text, replyId);
		post.setDate(new Date());
		postList.add(post);
		return postList;
	}

	@Override
	public List<Post> listAllPost() {
		// TODO Auto-generated method stub
		return postList;
	}



	@Override
	public void deletePost(long id) {
		this.postRepository.deleteById(id);
		
	}


	@Override
	public Post getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}



	
	}


	

	

