package com.example.demo.dao;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    Post findById(Integer commentid);

}
