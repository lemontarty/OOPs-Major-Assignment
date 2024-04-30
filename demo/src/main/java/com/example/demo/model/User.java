package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "userid")
    private Integer userid;

//    @Column(nullable = false, name = "name")
    private String name;

//    @Column(nullable = false, unique = true, name = "emailid")
    private String emailid;

//    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "postCreator", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "commentCreator", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public User()
    {

    }
    public User(@JsonProperty("name") String name, @JsonProperty("email")String emailid, String password)
    {
        this.name = name;
        this.emailid = emailid;
        this.password = password;
    }

    public Integer getId()
    {
        return userid;
    }

    public String getName()
    {
        return name;
    }

    public String getEmailid()
    {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts()
    {
        return posts;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
