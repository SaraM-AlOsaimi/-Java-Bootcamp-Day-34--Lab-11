package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserId(Integer userId);

    @Query("select u from User u where u.email=?1")
    User getUserByEmail(String email);

    // Endpoint 6 :
    //Check if username and password are correct
    User findUserByUsernameAndPassword(String username,String password);

    // Endpoint 7 :
    // get all users registered after date by date
    List<User> findUserByRegistrationDateAfter(LocalDate registrationDateAfter);

}
