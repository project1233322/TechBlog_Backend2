package com.example.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.dtos.QuestionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="questions")
public class Questions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	private String title;
	
	@Lob
	@Column(name = "body" ,length=512)
	private String body;
	
	private Date createdDate;
	
	
	private List<String> tags;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	public QuestionDTO getQuestionDTO() {
		QuestionDTO questionDTO=new QuestionDTO();
		questionDTO.setId(id);
		questionDTO.setBody(body);
		questionDTO.setTitle(title);
		questionDTO.setTags(tags);
		questionDTO.setCreatedDate(createdDate);
		questionDTO.setUserId(user.getId());
		questionDTO.setUsername(user.getName());
		return questionDTO;
		
		
		
		
	}
	
	

}
