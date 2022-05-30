package net.javaguides.springboot.controllers.dto;

import java.util.Date;

public class PostDto {

	private Long id;
	private String text;
	private Long replyId;
	private Date date;
	
	public PostDto() {
		
	}
	public PostDto(Long id, String text, Long replyId, Date date) {
		super();
		this.id = id;
		this.text = text;
		this.replyId = replyId;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
