package com.example.servics.answer;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dtos.AnswerDTO;
import com.example.entities.Answers;
import com.example.entities.Questions;
import com.example.entities.User;
import com.example.repositories.AnswerRepositories;
import com.example.repositories.QuestionRepository;
import com.example.repositories.UserRepository;

@Service
public class AnswerserviceImpl implements Answerservice{

	
	public final UserRepository userRepository;
	public final QuestionRepository  questionRepository;
	public final AnswerRepositories answerRepositories;
	
	
	public AnswerserviceImpl(UserRepository userRepository, QuestionRepository questionRepository,
			AnswerRepositories answerRepositories) {
		super();
		this.userRepository = userRepository;
		this.questionRepository = questionRepository;
		this.answerRepositories = answerRepositories;
	}


	public AnswerDTO postAnswer(AnswerDTO answerDTO) {
		Optional<User> optionalUser =userRepository.findById(answerDTO.getUserId());
		Optional<Questions> optionalquestion=questionRepository.findById(answerDTO.getQuestionId());
		if(optionalUser.isPresent() && optionalquestion.isPresent()) {
			Answers answer=new Answers();
			answer.setBody(answerDTO.getBody());
			answer.setCreatedDate(new Date());
			answer.setQuestions(optionalquestion.get());
			answer.setUser(optionalUser.get());
			Answers createanswer= answerRepositories.save(answer);
			AnswerDTO createdAnswerDTO=new AnswerDTO();
			createdAnswerDTO.setId(createanswer.getId());
			return createdAnswerDTO;
		}
		return null;
	}
	
	
}
