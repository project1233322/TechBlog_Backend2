package com.example.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entities.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long> {

    // Define a custom query method to retrieve questions sorted by descending createdDate
    Page<Questions> findAllByOrderByCreatedDateDesc(Pageable pageable);

	Page<Questions> findAllByUserId(Long userId, Pageable paging);
	
	Page<Questions> findAllByTitleContaining(String title, Pageable pageable);
	
	Page<Questions> findAllByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);
}