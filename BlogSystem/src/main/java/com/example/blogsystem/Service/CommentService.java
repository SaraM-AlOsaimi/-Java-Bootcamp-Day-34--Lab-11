package com.example.blogsystem.Service;

import com.example.blogsystem.API.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CommentRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import jakarta.websocket.Endpoint;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        for (User user : userRepository.findAll()){
            if(user.getUserId().equals(comment.getUserId())){
                for (Post post : postRepository.findAll()){
                    if(post.getPostId().equals(comment.getPostId())){
                        comment.setCommentDate(LocalDate.now());
                        commentRepository.save(comment);
                    }
                }
                throw new ApiException("Post not found");
            }
        }
        throw new ApiException("User not found");
    }

    public void updateComment(Integer id , Comment comment){
        Comment comment1 = commentRepository.findCommentByCommentId(id);
        if(comment1 == null){
            throw new ApiException("Comment not found");
        }
        comment1.setUserId(comment.getUserId());
        comment1.setContent(comment.getContent());
        comment1.setCommentDate(LocalDate.now());
        comment1.setPostId(comment.getPostId());
        commentRepository.save(comment1);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentByCommentId(id);
        if(comment == null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(comment);
    }


    // Endpoint 3 :
    // get all comment for one post by post_id
   public List<Comment> findCommentsByPostId(Integer post_id){
        for (Post post : postRepository.findAll()){
            if(post.getPostId().equals(post_id)){
                List<Comment> commentList = commentRepository.findCommentsByPostId(post_id);
                if(commentList == null){
                    throw new ApiException("No comment on that post");
                }
                return commentList;
            }
        }
        throw new ApiException("Post not found");
   }


   // Endpoint 5 :
    // get All Comment For One User by User id
    public List<Comment> getAllCommentForOneUser(Integer user_id){
        List<Comment> commentList = commentRepository.getAllCommentForOneUser(user_id);
        for (User user : userRepository.findAll()){
            if (user.getUserId().equals(user_id)){
                if(commentList == null){
                    throw new ApiException("No comments found");
                }
                return commentList;
            }
        }
        throw new ApiException("User not found");
    }

  //  Endpoint 8 :
    public int getCommentCountForPost(Integer postId) {
        if (!postRepository.existsById(postId)) {
            throw new ApiException("Post not found");
        }
        return commentRepository.countCommentByPostId(postId);
    }

}
