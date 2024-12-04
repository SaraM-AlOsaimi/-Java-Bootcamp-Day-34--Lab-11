package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostByPostId(Integer postId);

    // Endpoint 1:
    // get all post by user_id
    List<Post> findPostByUserId(Integer userId);

    // Endpoint 2:
    // get post by title
    @Query("select p from Post p where p.title=?1")
    Post getPostsByTitle(String title);

    // Endpoint 4 :
    // get all post before date by date
    List<Post> findAllPostsByPublishDateBefore(LocalDate publishDateBefore);

}
