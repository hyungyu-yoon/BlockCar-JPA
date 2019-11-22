package com.bollock.blockcar.post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUserNo(Long no);
}


