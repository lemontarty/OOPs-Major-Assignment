package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
//import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "posts")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postid;

//    @Column(nullable = false)
    private LocalDate date;

//    @Column(nullable = false)
    private LocalTime time;

//    @Column(nullable = false, name = "postbody" )
    private String postBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postCreator", nullable = false)
    private User postCreator;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    private String dateFormatting()
    {
        LocalDate tempDate = LocalDate.now();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return tempDate.format(d);
    }

    private String timeFormatting()
    {
        LocalTime tempTime = LocalTime.now();
        DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm:ss");
        return tempTime.format(t);

    }
    public Post() {
    }

    public Post( @JsonProperty("postBody") String postBody, @JsonProperty("postCreator") User postCreator) {
        this.postBody = postBody;
        this.postCreator = postCreator;
//        this.currdate = LocalDate.now();
//        this.currtime = LocalTime.now();
        setCurrdate(LocalDate.now());
        setCurrtime(LocalTime.now());
    }

    public int getPostid() {
        return postid;
    }

    public String getDate() {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(d);
    }

    public String getTime(){
        DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm:ss");
        return time.format(t);
    }

    public String getPostBody() {
        return postBody;
    }


    public User getPostCreator() {
        return postCreator;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setPostBody(String newPostBody){
        postBody = newPostBody;
    }


    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setPostCreator(User postCreator) {
        this.postCreator = postCreator;
    }

    public void setCurrdate(LocalDate date) {
        this.date = date;
    }

    public void setCurrtime(LocalTime time) {
        this.time = time;
    }
}
