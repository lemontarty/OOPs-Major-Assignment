package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "comments")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentPost", nullable = false)
    private Post parentPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentCreator", nullable = false)
    private User commentCreator;

//    @Column(nullable = false, name = "commentbody")
    private String commentBody;

    public Comment()
    {

    }
    public Comment(@JsonProperty("commentid") Integer commentid, @JsonProperty("postid") Post parentPost, @JsonProperty("commentcreator") User commentCreator, @JsonProperty("commentbody") String commentBody) {
        this.commentid = commentid;
        this.parentPost = parentPost;
        this.commentCreator = commentCreator;
        this.commentBody = commentBody;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public Post getPost() {
        return parentPost;
    }

    public User getCommentCreator() {
        return commentCreator;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String newCommentBody)
    {
        commentBody = newCommentBody;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public void setCommentCreator(User commentCreator) {
        this.commentCreator = commentCreator;
    }
}
