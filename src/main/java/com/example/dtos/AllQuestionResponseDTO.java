package com.example.dtos;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class AllQuestionResponseDTO {
	  
	private List<QuestionDTO> questionDTOs;
	private Integer totalPages;
	private Integer pageNumber;
	
}
