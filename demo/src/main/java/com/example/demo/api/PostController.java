package com.example.demo.api;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.CreatePostRequest;
import com.example.demo.dto.EditPostRequest;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping("/post")
    public Object createPost(@RequestBody CreatePostRequest request) {



        boolean created = postService.createPost(request.getPostBody(), request.getUserID());
        if (created) {
            return "Post created successfully";

        } else {
            return new ErrorResponse("User does not exist");
        }
    }

    @PatchMapping("/post")
//    public Object editPost(@RequestBody String newPostBody, Integer postid) {
//        boolean edited = postService.editPost(newPostBody, postid);
//        if (edited) {
//            return "Post edited successfully";
//        } else {
//            return new ErrorResponse("Error editing post: Post does not exist");
//        }
//    }
    public Object editPost(@RequestBody EditPostRequest request) {
        boolean edited = postService.editPost(request.getPostBody(), request.getPostID());
        if (edited) {
            return "Post edited successfully";
        } else {
            return new ErrorResponse("Post does not exist");
        }
    }

    @DeleteMapping("/post")
    public Object deletePost(@RequestParam(value = "postID") Integer postid) {
        boolean deleted = postService.deletePost(postid);
        if (deleted) {
            return "Post deleted successfully";
        } else {
            return new ErrorResponse("Post does not exist");
        }
    }

    @GetMapping("/")
    public Object getUserPosts()
    {
        return postService.getUserFeed();
    }

    @GetMapping("/post")
    public Object getPostById(@RequestParam(value = "postID") Integer postid)
    {
        if(!postService.exists(postid)) return new ErrorResponse("Post does not exist");
        return postService.getPostById(postid);
    }
}
