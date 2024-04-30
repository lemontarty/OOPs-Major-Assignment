package com.example.demo.service;

import com.example.demo.dao.CommentRepository;
import com.example.demo.dao.PostRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

//    public int createComment(Comment comment)
//    {
//        User commentCreator = comment.getCommentCreator();
//        Post tempPost = comment.getPost();
//
//        Post parentPost = postRepository.findById(tempPost.getPostid()).orElse(null);
//
//        if(!userRepository.existsById(commentCreator.getId())) return -1;
//        if(!postRepository.existsById(tempPost.getPostid())) return -2;
//
//        commentRepository.save(comment);
//        if(parentPost!=null) parentPost.addComment(comment);
//        commentCreator.addComment(comment);
//
//        return 0;
//
//    }

    public int createComment(String commentBody, Integer userID, Integer postID) {
        // Retrieve the user by ID
        Optional<User> optionalUser = userRepository.findById(userID);
        Optional<Post> optionalPost = postRepository.findById(postID);

        // Check if the user exists
        if (optionalUser.isPresent() && optionalPost.isPresent()) {
            User commentCreator = optionalUser.get();
            Post parentPost = optionalPost.get();

            Comment comment = new Comment();
            comment.setCommentBody(commentBody);
            comment.setCommentCreator(commentCreator);
            comment.setParentPost(parentPost);

            commentRepository.save(comment);
            commentCreator.addComment(comment);
            parentPost.addComment(comment);

            return 3;
        } else if(!optionalUser.isPresent()) {
            // User does not exist
            return 1;
        }
        return 2;
    }

    public boolean editComment(String newCommentBody, Integer commentid)
    {
        if(!commentRepository.existsById(commentid)) return false;

        Comment comment = commentRepository.findById(commentid).orElse(null);
        if(comment!=null)
        {
            comment.setCommentBody(newCommentBody);
            commentRepository.save(comment);

        }
        return true;
    }

    @Transactional
    public boolean deleteComment(Integer commentid)
    {
        if (commentRepository.existsById(commentid)) {
            commentRepository.deleteById(commentid);
            return true;
        }
        return false;
    }

    public Comment getCommentById(Integer id)
    {
        return commentRepository.findById(id).orElse(null);
    }
}
