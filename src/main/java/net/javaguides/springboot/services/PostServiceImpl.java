package net.javaguides.springboot.services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.javaguides.springboot.model.Post;
import net.javaguides.springboot.model.SubmissionPost;
import net.javaguides.springboot.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService{
	
	  // Create ObjectMapper object.
    ObjectMapper mapper = new ObjectMapper();
 

	private Queue<SubmissionPost> submittedQueue = new LinkedList<>();
	private PostRepository postRepository;
	Runnable task =() -> save();
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	
	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}
	
	
	@Override
	public Queue<SubmissionPost> saveSubmissionPost(String text, Long replyId) throws JsonProcessingException {
		// TODO Auto-generated method stub
		SubmissionPost submittedPost = new SubmissionPost(text, replyId);
		submittedPost.setId();
		submittedPost.setDate(new Date());
		submittedQueue.add(submittedPost);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		   String json = mapper.writeValueAsString(submittedQueue);
		   try {
	            FileWriter file = new FileWriter("posts.json");
	            file.write(json.toString());
	            file.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		if(submittedQueue.size()<=5)
			executor.schedule(task, 1, TimeUnit.MINUTES);
		else if(submittedQueue.size()<=10)
			executor.schedule(task, 2, TimeUnit.MINUTES);
		else
			executor.schedule(task, 3, TimeUnit.MINUTES);
		return submittedQueue;	
		 
	}


	@Override
	public Queue<SubmissionPost> listAllSubmittedPost() {
		// TODO Auto-generated method stub
		return submittedQueue;
	}

	@Scheduled(fixedRate = 60000)
	@Override
	public void save() {
		// TODO Auto-generated method stub
		while(!submittedQueue.isEmpty()) {
			SubmissionPost submittedPost = submittedQueue.poll();
			Post post = new Post(submittedPost.getId(), submittedPost.getText(), submittedPost.getReplyId(), submittedPost.getDate());
			postRepository.save(post);
		}
	}


	@Override
	public List<Post> listAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findAll(Sort.by(Sort.Direction.DESC, "Id"));
	}
	
	
	
}

