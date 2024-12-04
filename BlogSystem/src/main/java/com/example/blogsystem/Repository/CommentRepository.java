package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentByCommentId(Integer commentId);

    // Endpoint 3 :
    // get all comment for one post by post_id
    List<Comment> findCommentsByPostId(Integer post_id);

    // Endpoint 5 :
    // get all comment for one user by user id
    @Query("select c from Comment c where c.userId=?1")
    List<Comment> getAllCommentForOneUser(Integer user_id);

    int countCommentByPostId(Integer postId);

}
