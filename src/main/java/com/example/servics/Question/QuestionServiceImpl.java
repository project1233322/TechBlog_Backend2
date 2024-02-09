package com.example.servics.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dtos.AllQuestionResponseDTO;
import com.example.dtos.AnswerDTO;
import com.example.dtos.QuestionDTO;
import com.example.dtos.QuestionSearchResponseDTO;
import com.example.dtos.SingleQuestionDTO;
import com.example.entities.Answers;
import com.example.entities.Questions;
import com.example.entities.User;
import com.example.repositories.AnswerRepositories;
import com.example.repositories.QuestionRepository;
import com.example.repositories.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionServices {
	
	public static final int Search_Result_Per_Page = 5;

	//public static final int QuestionPage = 0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepositories answerRepositories;

    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Optional<User> optionalUser = userRepository.findById(questionDTO.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Questions question = new Questions();
            question.setTitle(questionDTO.getTitle());
            question.setBody(questionDTO.getBody());
            question.setTags(questionDTO.getTags());
            question.setUser(user); // Set the user
            question.setCreatedDate(new Date());
            Questions createdQuestion = questionRepository.save(question);
            QuestionDTO createdQuestionDTO = new QuestionDTO();
            createdQuestionDTO.setId(createdQuestion.getId());
            createdQuestionDTO.setTitle(createdQuestion.getTitle());
            return createdQuestionDTO;
        }
        return null;
    }
    // Add other methods as needed

//	@Override
//	public AllQuestionResponseDTO getAllQuestion(int pageNumber) {
//		Pageable paging=PageRequest.of(pageNumber,Search_Result_Per_Page);
//		Page<Questions> questionsPage = questionRepository.findAll(paging); //get all question from repository
//		AllQuestionResponseDTO allQuestionResponseDTO=new AllQuestionResponseDTO();
//		allQuestionResponseDTO.setQuestionDTOs(questionsPage.getContent().stream().map(Questions::getQuestionDTO).collect(Collectors.toList()));
//		
//		allQuestionResponseDTO.setPageNumber(questionsPage.getPageable().getPageNumber());
//		allQuestionResponseDTO.setTotalPages(questionsPage.getTotalPages());
//		return allQuestionResponseDTO;
//	}
    @Override
    public AllQuestionResponseDTO getAllQuestion(int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber, Search_Result_Per_Page);

        // Retrieve questions page by page, sorted by descending createdDate
        Page<Questions> questionsPage = questionRepository.findAllByOrderByCreatedDateDesc(paging);

        // Map Questions entities to QuestionDTOs
        List<QuestionDTO> questionDTOs = questionsPage.getContent()
                .stream()
                .map(Questions::getQuestionDTO)
                .collect(Collectors.toList());

        // Create AllQuestionResponseDTO and set values
        AllQuestionResponseDTO allQuestionResponseDTO = new AllQuestionResponseDTO();
        allQuestionResponseDTO.setQuestionDTOs(questionDTOs);
        allQuestionResponseDTO.setPageNumber(pageNumber); // Set the correct page number
        allQuestionResponseDTO.setTotalPages(questionsPage.getTotalPages());

        return allQuestionResponseDTO;
    }
    @Override
	public SingleQuestionDTO getQuestionById(Long questionId) {
		Optional<Questions> optionalQuestion=	questionRepository.findById(questionId);	
		if(optionalQuestion.isPresent()) {
			SingleQuestionDTO singleQuestionDTO=new SingleQuestionDTO();
			singleQuestionDTO.setQuestionDTO(optionalQuestion.get().getQuestionDTO());
			List<AnswerDTO> answerDTOList=new ArrayList<AnswerDTO>();
			List<Answers> answerList=answerRepositories.findAllByQuestionsId(questionId);
			for(Answers answers:answerList) {
				AnswerDTO answerDTO=answers.getAnswerDTO();
				answerDTOList.add(answerDTO);
			}
			singleQuestionDTO.setAnswerDTOList(answerDTOList);
			return singleQuestionDTO;
		}
		return null;
	}
	
	@Override
	public AllQuestionResponseDTO getQusetionsByUserId(Long userId, int pageNumber) {
		Pageable paging=PageRequest.of(pageNumber,Search_Result_Per_Page);
		Page<Questions > questionsPage = questionRepository.findAllByUserIdOrderByCreatedDateDesc(userId,paging); //get all question from repository
		AllQuestionResponseDTO allQuestionResponseDTO=new AllQuestionResponseDTO();
		allQuestionResponseDTO.setQuestionDTOs(questionsPage.getContent().stream().map(Questions::getQuestionDTO).collect(Collectors.toList()));
//		here setQuestionDTOList
		allQuestionResponseDTO.setPageNumber(questionsPage.getPageable().getPageNumber());
		allQuestionResponseDTO.setTotalPages(questionsPage.getTotalPages());
		return allQuestionResponseDTO;
	}
	
	@Override
	public QuestionSearchResponseDTO SerchQuestionByTitle(String title, int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber, Search_Result_Per_Page);
		Page<Questions> questionpage;
		if (title==null || title.equals(null)) {
			questionpage = questionRepository.findAll(pageable);			
		}
		else {
			questionpage=questionRepository.findAllByTitleContaining(title,pageable);
		}
		QuestionSearchResponseDTO questionSearchResponseDTO=new QuestionSearchResponseDTO();
		questionSearchResponseDTO.setQuestionDTOs(questionpage.stream().map(Questions:: getQuestionDTO).collect(Collectors.toList()));
		questionSearchResponseDTO.setPageNumber(questionpage.getPageable().getPageNumber());
		questionSearchResponseDTO.setTotalPages(questionpage.getTotalPages());
		return questionSearchResponseDTO;
		
	
	}

}
