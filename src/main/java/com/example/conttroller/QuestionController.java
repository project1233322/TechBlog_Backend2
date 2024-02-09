package com.example.conttroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;   
import com.example.dtos.AllQuestionResponseDTO;
import com.example.dtos.QuestionDTO;
import com.example.dtos.QuestionSearchResponseDTO;
import com.example.dtos.SingleQuestionDTO;
import com.example.servics.Question.QuestionServices;

@RestController
//@RequestMapping("/api/question")
public class QuestionController {
	

	private final QuestionServices questionServices;
	  
	public QuestionController(QuestionServices  questionServices) {
		this.questionServices=questionServices;
	}
	@PostMapping("/question")
	public ResponseEntity<?> postQuestion(@RequestBody QuestionDTO questionDTO){
		QuestionDTO createQuestionDTO= questionServices.addQuestion(questionDTO);
		if(createQuestionDTO==null) {
			return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createQuestionDTO);
	}
	
	@GetMapping("/questions/page/{pageNumber}")
	public ResponseEntity<AllQuestionResponseDTO> getQusetions(@PathVariable int pageNumber){
		AllQuestionResponseDTO allQuestionResponseDTO =questionServices.getAllQuestion(pageNumber);
		return ResponseEntity.ok(allQuestionResponseDTO);
	}
	
	@GetMapping("/questions/id/{questionId}")
	public ResponseEntity<?> getQusetionById(@PathVariable Long questionId){
		SingleQuestionDTO singlequestionDTO=questionServices.getQuestionById(questionId);
		if(singlequestionDTO==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singlequestionDTO);
		
	}
	
	@GetMapping("/questions/{userId}/{pageNumber}")
	public ResponseEntity<AllQuestionResponseDTO> getQusetionsByUserId(@PathVariable long userId,@PathVariable int pageNumber){
		AllQuestionResponseDTO allQuestionResponseDTO =questionServices.getQusetionsByUserId(userId,pageNumber);
		return ResponseEntity.ok(allQuestionResponseDTO);
	}
	
	@GetMapping("/search/{title}/{pageNumber}")
	public ResponseEntity<?> searchQuestionByTile(@PathVariable int pageNumber, @PathVariable String title) {
	    QuestionSearchResponseDTO questionSearchResponseDTO = questionServices.SerchQuestionByTitle(title, pageNumber);
	    if (questionSearchResponseDTO == null)
	        return ResponseEntity.notFound().build();
	    else
	        return ResponseEntity.ok(questionSearchResponseDTO);
	}
	}

