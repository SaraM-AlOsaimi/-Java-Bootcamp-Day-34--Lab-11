package com.example.blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Post {

    public Post(Integer postId, Integer categoryId, Integer userId, String title, String content, LocalDate publishDate) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    //post_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    // category_id // foreign key for the category entity
    @NotNull(message = "Category id is empty")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    // user_id  // foreign key for the user entity
    @NotNull(message = "User id is empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    //title
    @NotEmpty(message = "Title is empty")
    @Size(min = 5 , message = "Title length must be more than 4 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    //content
    @NotEmpty(message = "Content is empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;

    //publish_date
    private LocalDate publishDate;

}
