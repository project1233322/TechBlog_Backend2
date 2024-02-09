package com.example.conttroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.servics.answer.AnswerserviceImpl;
import com.example.dtos.AnswerDTO;
import com.example.servics.answer.Answerservice;

@RestController
@RequestMapping
public class AnswerController {
	private Answerservice answerservice;
	
	public AnswerController(Answerservice answerservice) {
		this.answerservice=answerservice;
	}
	
	
	@PostMapping("/answer")
	public ResponseEntity<?> addAnswer(@RequestBody AnswerDTO answerDTO){
		AnswerDTO createdAnswerDTO=answerservice.postAnswer(answerDTO);
		if(createdAnswerDTO==null) 
			return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDTO);
			
	}
}
