package com.hyuk.blog.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query(value = "SELECT * FROM post p WHERE p.title LIKE %:keyword% or p.content like %:keyword%", nativeQuery = true)
	Page<Post> findByKeyword(String keyword, Pageable pageable);
}