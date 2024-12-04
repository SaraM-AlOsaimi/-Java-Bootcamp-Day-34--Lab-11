package com.example.blogsystem.Service;

import com.example.blogsystem.API.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CategoryRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        if(!userRepository.existsById(post.getUserId())){
            throw new ApiException("User not found");
        }
        if (!categoryRepository.existsById(post.getCategoryId())) {
            throw new ApiException("Category not found");
        }
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }


    public void updatePost(Integer id , Post post){
        if(!userRepository.existsById(post.getUserId())){
            throw new ApiException("User not found");
        }
        if(!categoryRepository.existsById(post.getCategoryId())){
            throw new ApiException("Category not found");
        }
        Post oldpost = postRepository.findPostByPostId(id);
        oldpost.setCategoryId(post.getCategoryId());
        oldpost.setUserId(post.getUserId());
        oldpost.setContent(post.getContent());
        oldpost.setTitle(post.getTitle());
        postRepository.save(oldpost);
    }

    public void deletePost(Integer id){
      Post post = postRepository.findPostByPostId(id);
      if(post == null){
          throw new ApiException("Post not found");
      }
      postRepository.delete(post);
    }


    // Endpoint 1:
    // get all post by user id
    public List<Post> getAllPostByUserId(Integer user_id){
        List<Post> postList = postRepository.findPostByUserId(user_id);
        if(postList == null){
            throw new ApiException("No posts found");
        }
        return postList;
    }

    // Endpoint 2:
    // get post by title
    public Post getPostByTitle(String title){
        Post post = postRepository.getPostsByTitle(title);
        if (post == null){
            throw new ApiException("Not found");
        }
        return post;
    }

    // Endpoint 4 :
    // get all post before date by date
    public List<Post> findAllPostsByPublishDateBefore(LocalDate publishDateBefore){
       List<Post> postList = postRepository.findAllPostsByPublishDateBefore(publishDateBefore);
       if(postList == null){
           throw new ApiException("No posts found");
       }
       return postList;
    }

}
