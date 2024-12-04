package com.example.blogsystem.Controller;

import com.example.blogsystem.API.ApiResponse;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-system/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllPosts(){
        return  ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       postService.addPost(post);
       return ResponseEntity.status(200).body(new ApiResponse("Post added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id , @RequestBody @Valid Post post, Errors errors){
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
       postService.updatePost(id,post);
       return ResponseEntity.status(200).body(new ApiResponse("Post Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted"));
    }

    // Endpoint 1:
    // get all post by user id
    @GetMapping("/user/{userId}/post")
    public ResponseEntity<?> getAllPostByUserId(@PathVariable Integer userId){
        List<Post> postList = postService.getAllPostByUserId(userId);
        return ResponseEntity.status(200).body(postList);
    }

    // Endpoint 2:
    // get post by title
    @GetMapping("/get-post-by/{title}")
    public ResponseEntity<?> getPostByTitle(@PathVariable String title){
        Post postByTitle = postService.getPostByTitle(title);
        return ResponseEntity.status(200).body(postByTitle);
    }

    // Endpoint 4 :
    // get all post before date by date
    @GetMapping("/get-all-posts/{publishDateBefore}")
    public ResponseEntity<?> findAllPostsByPublishDateBefore(@PathVariable LocalDate publishDateBefore){
        List<Post> postList = postService.findAllPostsByPublishDateBefore(publishDateBefore);
        return ResponseEntity.status(200).body(postList);
    }


}
