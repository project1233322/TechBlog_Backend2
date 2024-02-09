package com.example.servics.Question;

import com.example.dtos.AllQuestionResponseDTO;
import com.example.dtos.QuestionDTO;
import com.example.dtos.QuestionSearchResponseDTO;
import com.example.dtos.SingleQuestionDTO;

public interface QuestionServices {
	QuestionDTO addQuestion(QuestionDTO questionDTO);

	AllQuestionResponseDTO getAllQuestion(int pageNumber);

	SingleQuestionDTO getQuestionById(Long questionId);
	
	AllQuestionResponseDTO getQusetionsByUserId(Long userId, int pageNumber);
	
	QuestionSearchResponseDTO SerchQuestionByTitle(String title, int pageNumber);

}
