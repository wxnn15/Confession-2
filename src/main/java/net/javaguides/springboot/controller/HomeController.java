package net.javaguides.springboot.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.javaguides.springboot.model.SubmissionPost;
import net.javaguides.springboot.services.PostService;

@Controller
public class HomeController {

	@Autowired
	private PostService postService;

	public HomeController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@GetMapping("admin")
	private String homePage(Model model) {
		model.addAttribute("posts", postService.listAllPost());
		return "admin.html";
	}
	
	@GetMapping("")
	private String listPost(Model model) {
		model.addAttribute("posts", postService.listAllSubmittedPost());
		return "index.html";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
        model.addAttribute("statusDropdown", postService.listAllPost());
		model.addAttribute("posts", postService.listAllPost());
		return "create.html";
	}
	
	//handler method which will handle http post request
	@PostMapping("create")
	public String postCreation(HttpServletRequest request, Model model) throws JsonProcessingException {
		Long id;
		if(request.getParameter("replyId").equals(""))
			id = null;
		else
			id = Long.valueOf(request.getParameter("replyId"));
		String text = request.getParameter("text");
		for (SubmissionPost item: postService.listAllSubmittedPost()) {
            if(text.equals(item.getText()))
            	return "redirect:/create?error";
        }
		String[] badTone = {"fuck", "nigga", "bastard", "dick head", "bitch", "ashole", "cock"};
		for(int i=0; i<badTone.length; i++) {
			if(text.toLowerCase().contains(badTone[i].toLowerCase()))
				return "redirect:/create?error";
		}
		postService.saveSubmissionPost(text, id);
		return "redirect:/create?success";
	}
}