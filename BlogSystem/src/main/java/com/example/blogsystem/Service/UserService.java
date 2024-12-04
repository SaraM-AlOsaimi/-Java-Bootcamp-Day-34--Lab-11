package com.example.blogsystem.Service;

import com.example.blogsystem.API.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CRUD
    //get
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // add
    public void addUser(User user){
      user.setRegistrationDate(LocalDate.now());
      userRepository.save(user);
    }

    // update
    public void updateUser(Integer id , User user){
        User oldUser = userRepository.findUserByUserId(id);
        if(oldUser == null){
            throw new ApiException("User not found");
        }
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        userRepository.save(oldUser);
    }

    // delete
    public void deleteUser(Integer id){
        User user = userRepository.findUserByUserId(id);
        if(user == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    // get User by email
    public User getUserByEmail(String email){
        User user = userRepository.getUserByEmail(email);
        if(user == null){
            throw new ApiException("user not found");
        }
        return user;
    }

    // Endpoint 6 :
    // Check if username and password are correct
    public User findUserByUsernameAndPassword(String username , String password){
      User user = userRepository.findUserByUsernameAndPassword(username,password);
      if(user == null){
          throw new ApiException("User not found");
      }
      return user;
    }

    // Endpoint 7 :
    // get all users registered after date by date
    public List<User> userByRegistrationDateAfter(LocalDate registrationDateAfter){
        List<User> users = userRepository.findUserByRegistrationDateAfter(registrationDateAfter);
        if(users == null){
            throw new ApiException("no users found");
        }
        return users;
    }

}
