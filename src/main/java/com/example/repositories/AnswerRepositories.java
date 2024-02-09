package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Answers;

@Repository
public interface AnswerRepositories extends JpaRepository<Answers, Long> {
	List<Answers> findAllByQuestionsId(Long questionId);
}
