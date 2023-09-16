package com.example.kodomoproject.domain.comment.repository;

import com.example.kodomoproject.domain.comment.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query(sort = "{createdDate: -1}")
    List<Comment> findByProductId(String id);

}
