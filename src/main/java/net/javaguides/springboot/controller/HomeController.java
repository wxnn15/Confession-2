package net.javaguides.springboot.controller;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import net.javaguides.springboot.controllers.dto.PostDto;
import net.javaguides.springboot.model.Post;
import net.javaguides.springboot.services.PostService;
import net.javaguides.springboot.services.PostServiceImpl;


@Controller
public class HomeController {

	@Autowired
	private PostService postService;
	private PostServiceImpl postServiceImpl;
	
	
	Random rand = new Random();
	
	public HomeController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	
	
	

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.postService.deletePost(id);
		return "redirect:/admin";
	}

	@GetMapping("/update/{id}")
    public String updatePost(@PathVariable(value = "id") long id, Model model) {
        Post post= postServiceImpl.getById(id);
        model.addAttribute("posts", post);
        return "admin.html";
    }

	
	
	@GetMapping("/create")
	public String create(Model model) {
        model.addAttribute("posts", postService.listAllPost());
		return "create.html";
	}
	

	@GetMapping("/admin")
	public String admin(Model model) {
        model.addAttribute("posts", postService.listAllPost());
		return "admin.html";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postService.listAllPost());
		return "index";
	}
	

	
	
	//whenever user enter a form data, the html will get the object from here
	@ModelAttribute("posts")
	public PostDto postDto() {
		return new PostDto();
	}
	
	
	
	@PostMapping("create")
	public String save(HttpServletRequest request) {
		Long replyId;
		Long id = (long) rand.nextInt(10000);
		if(request.getParameter("replyId").equals("null"))
			replyId = null;
		else
		replyId = Long.valueOf(request.getParameter("replyId"));
		String text = request.getParameter("text");
		postService.save(id, replyId, text);
		return "redirect:/create?success";
	}
	
	
	
	
	
}
