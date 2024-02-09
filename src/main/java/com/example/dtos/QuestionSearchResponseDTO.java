package com.example.dtos;

import java.util.List;

import lombok.Data;

@Data
public class QuestionSearchResponseDTO {
	  
	private List<QuestionDTO> questionDTOs;
	private Integer totalPages;
	private Integer pageNumber;

}
