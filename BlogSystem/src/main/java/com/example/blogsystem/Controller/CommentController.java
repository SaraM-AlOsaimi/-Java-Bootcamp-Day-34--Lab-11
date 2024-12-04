package com.example.blogsystem.Controller;

import com.example.blogsystem.API.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog-system/comment")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id , @RequestBody @Valid Comment comment,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted"));
    }

    // Endpoint 3 :
    // get all comment for one post by post_id
    @GetMapping("/get-all-comment-for-one-post/{id}")
    public ResponseEntity<?> findCommentsByPostId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.findCommentsByPostId(id));
    }

    // Endpoint 5 :
    // get All Comment For One User by User id
    @GetMapping("/get-comment-for-one-user/{id}")
    public ResponseEntity<?> getAllCommentForOneUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getAllCommentForOneUser(id));
    }

    // Endpoint 8 :
    @GetMapping("/count-for-comment/{postId}")
    public ResponseEntity<?> getCommentCountForPost(@PathVariable Integer postId) {
        int count = commentService.getCommentCountForPost(postId);
        return ResponseEntity.status(200).body(new ApiResponse("Comment count for post: " + count));
    }


}
