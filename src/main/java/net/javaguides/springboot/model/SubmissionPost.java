package net.javaguides.springboot.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SubmissionPost {

	private static AtomicInteger count = new AtomicInteger(0);
	private Long id;
	private String text;
	private Long replyId;
	private Date date;
	
	public SubmissionPost() {
		super();
	}

	public SubmissionPost(String text, Long replyId) {
		super();
		this.text = text;
		this.replyId = replyId;
	}

	public Long getId() {
		return id;
	}

	public void setId() {
		this.id = Long.valueOf(count.incrementAndGet());
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
