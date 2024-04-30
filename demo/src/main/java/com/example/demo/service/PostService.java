package com.example.demo.service;

import com.example.demo.api.CommentCreatorResponse;
import com.example.demo.api.CommentResponse;
import com.example.demo.api.PostResponse;
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
import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

//    public boolean createPost(Post post)
//    {
//        User postCreator = post.getPostCreator();
//
//        if(!userRepository.existsById(postCreator.getId())) return false;
//        postRepository.save(post);
//        postCreator.addPost(post);
//        return true;
//    }
public boolean createPost(String postBody, int userID) {
    // Retrieve the user by ID
    Optional<User> optionalUser = userRepository.findById(userID);

    // Check if the user exists
    if (optionalUser.isPresent()) {
        User postCreator = optionalUser.get();

        // Create the new post
        Post post = new Post();
        post.setPostBody(postBody);
        post.setPostCreator(postCreator);
        post.setCurrtime(LocalTime.now());
        post.setCurrdate(LocalDate.now());

        // Save the post
        postRepository.save(post);

        // Add the post to the user's list of posts
        postCreator.addPost(post);

        return true;
    } else {
        // User does not exist
        return false;
    }
}


    public boolean editPost(String newPostBody, int postid )
    {
        if(!postRepository.existsById(postid)) return false;

        Post post = postRepository.findById(postid).orElse(null);
        if(post!=null)
        {
            post.setPostBody(newPostBody);
            postRepository.save(post);
        }
        return true;
    }

    @Transactional
    public boolean deletePost(Integer postid)
    {
        if (postRepository.existsById(postid)) {
            postRepository.deleteById(postid);
            return true;
        }
        return false;
    }

    public boolean exists(Integer postid)
    {
        if(postRepository.existsById(postid)) return true;
        return false;
    }

    public PostResponse getPostById(Integer postid)
    {
        Post post = postRepository.findById(postid).orElse(null);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            // Convert Comment entity to CommentResponse
            CommentResponse commentResponse = new CommentResponse(
                    comment.getCommentid(),
                    comment.getCommentBody(),
                    comment.getCommentCreator()
            );
            commentResponses.add(commentResponse);
        }

        // Convert Post entity to PostResponse
        PostResponse postResponse = new PostResponse(
                post.getPostid(),
                post.getPostBody(),
                post.getDate(),
                commentResponses
        );

        return postResponse;

    }

    public Map<String, List<PostResponse>> getUserFeed() {
        List<Post> posts = postRepository.findAllByOrderByDateDescTimeDesc(); // Assuming this method exists in your PostRepository
        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                // Convert Comment entity to CommentResponse
                CommentResponse commentResponse = new CommentResponse(
                        comment.getCommentid(),
                        comment.getCommentBody(),
                        comment.getCommentCreator()
                );
                commentResponses.add(commentResponse);
            }

            // Convert Post entity to PostResponse
            PostResponse postResponse = new PostResponse(
                    post.getPostid(),
                    post.getPostBody(),
                    post.getDate(),
                    commentResponses
            );
            postResponses.add(postResponse);
        }

        // Wrap postResponses within an object labeled "posts"
        Map<String, List<PostResponse>> responseMap = new HashMap<>();
        responseMap.put("posts", postResponses);

        return responseMap;
    }



}
