package com.ivamly.beanbuddies.repository;

import com.ivamly.beanbuddies.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
