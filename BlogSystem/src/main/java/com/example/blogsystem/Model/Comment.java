package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;


@Entity
public class Comment {

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    // user_id  // foreign key for the user entity
    @NotNull(message = "User id is empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    //  post_id // foreign key for the post entity
    @NotNull(message = "Post id is empty")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    //content
    @NotEmpty(message = "Content is empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;

    //comment_date
    private LocalDate commentDate;

    public Comment(Integer commentId, Integer userId, Integer postId, String content, LocalDate commentDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.commentDate = commentDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }
}
