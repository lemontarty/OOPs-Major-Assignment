package com.example.demo.api;

import com.example.demo.dto.CreateCommentRequest;
import com.example.demo.dto.EditCommentRequest;
import com.example.demo.dto.EditPostRequest;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CommentController {
//    private final CommentService commentService;
//
//    @Autowired
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public Object createComment(@RequestBody CreateCommentRequest request) {
        int result = commentService.createComment(request.getCommentBody(), request.getUserID(), request.getPostID());
        switch (result) {
            case 1:
                return new ErrorResponse( "User does not exist");
            case 2:
                return new ErrorResponse( "Post does not exist");
            default:
                return "Comment created successfully";
        }
    }

    @GetMapping("/comment")
    public Object getCommentById(@RequestParam("commentID") Integer commentID) {
        Comment comment = commentService.getCommentById(commentID);
        if (comment == null) {
            return new ErrorResponse( "Comment does not exist");
        } else {
            User commentCreator = comment.getCommentCreator();
            return new CommentResponse(comment.getCommentid(), comment.getCommentBody(), commentCreator);
        }
    }

    @PatchMapping("/comment")
    public Object editComment(@RequestBody EditCommentRequest request) {
        boolean edited = commentService.editComment(request.getNewCommentBody(), request.getCommentId());
        if (edited) {
            return "Comment edited successfully";
        } else {
            return new ErrorResponse("Comment does not exist");
        }
    }

    @DeleteMapping("/comment")
    public Object deleteComment(@RequestParam(value = "commentID") Integer commentid) {
        boolean deleted = commentService.deleteComment(commentid);
        if (deleted) {
            return "Comment deleted successfully";
        } else {
            return new ErrorResponse("Comment does not exist");
        }
    }


//    static class CommentResponse {
//        private int commentID;
//        private String commentBody;
//        private CommentCreatorResponse commentCreator;
//
//        public CommentResponse(int commentID, String commentBody, User commentCreator) {
//            this.commentID = commentID;
//            this.commentBody = commentBody;
//            this.commentCreator = new CommentCreatorResponse(commentCreator.getId(), commentCreator.getName());
//        }
//
//        public int getCommentID() {
//            return commentID;
//        }
//
//        public String getCommentBody() {
//            return commentBody;
//        }
//
//        public CommentCreatorResponse getCommentCreator() {
//            return commentCreator;
//        }
//    }
}
