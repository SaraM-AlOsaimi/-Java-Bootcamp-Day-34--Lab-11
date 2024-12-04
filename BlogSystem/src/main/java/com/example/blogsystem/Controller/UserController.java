package com.example.blogsystem.Controller;

import com.example.blogsystem.API.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/blog-system/user")
public class UserController {

    private final UserService userService;
    Logger logger= LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        logger.info("getAllUsers");
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user , Errors errors){
      if(errors.hasErrors()){
          return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
      }
      userService.addUser(user);
      return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id , @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }

    // get User by email
    @GetMapping("/get-user-by-{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    // Endpoint 6 :
    //Check if username and password are correct
    @GetMapping("/check-by/username/password/{username}/{password}")
    public ResponseEntity<?> findUserByUsernameAndPassword(@PathVariable String username , @PathVariable String password){
        return ResponseEntity.status(200).body(userService.findUserByUsernameAndPassword(username,password));
    }

    // Endpoint 7 :
    // get all users registered after date by date
    @GetMapping("/get-users-by/{date}")
    public ResponseEntity<?> getUserByRegistrationDateAfter(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(userService.userByRegistrationDateAfter(date));
    }


}
