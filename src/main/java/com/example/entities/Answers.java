package com.example.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.dtos.AnswerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Answers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	@Column(name = "body" ,length=512)
	private String body;
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)  /*if the user is deleted then all data related to user deleted*/
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "question_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)  /*if the user is deleted then all data related to user deleted*/
	@JsonIgnore
	private Questions questions;
	
	public AnswerDTO getAnswerDTO() {
		AnswerDTO answerDTO=new AnswerDTO();
		answerDTO.setId(id);
		answerDTO.setBody(body);
		answerDTO.setUserId(user.getId());
		answerDTO.setQuestionId(questions.getId());
		answerDTO.setCreatedDate(createdDate);
		answerDTO.setUsername(user.getName());		
		return answerDTO;
		
		
		}
}
