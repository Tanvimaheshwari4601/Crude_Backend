package net.user_details.controller;


import net.user_details.dto.Login;
import net.user_details.exception.ResourceNotFoundException;
import net.user_details.model.User;
import net.user_details.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( "/api/v1/user")
public class UserController {
    @Autowired
    private UserService uservice;


    //Get all details of user
    @GetMapping
    public List<User> getALLUser(){
        return (List<User>) uservice.findAll();
    }



    //create new user in details
    @PostMapping
    public User createUser(@Valid  @RequestBody User user) {
        Optional<User> userObj=uservice.findByEmail(user.getEmailid());
        if(userObj.isPresent()){
            throw new ResourceNotFoundException("User already exist");
        }
        User savedUser = uservice.save(user);
        savedUser.setPassword(null);
        return savedUser;
    }


    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id){
        Object user= uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No User Exist with such id" + id));
        return ResponseEntity.ok(user);
    }

    //Update the user details
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User user_details) {
//      IF
        User updateUser = uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user exist with such id: " + id));
        updateUser.setFirstname(user_details.getFirstname());
        updateUser.setLastname(user_details.getLastname());
        updateUser.setEmailid(user_details.getEmailid());
        updateUser.setCity(user_details.getCity());
        updateUser.setPhonenumber(user_details.getPhonenumber());

        uservice.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }


    //delete the user details
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User user = uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user exist with such id:  " + id));

        uservice.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/login")
    public User loginUser(@Valid  @RequestBody Login login) {
        System.out.println(login.emailid);
        System.out.println(login.password.toString());
        Optional<User> user =uservice.findByEmail(login.emailid);
        User userObj = user.get();

        if(!user.isPresent() || !userObj.getPassword().equals(login.password)){
            throw new ResourceNotFoundException("Invalid Credentials");
        }
        userObj.setPassword(null);
        return userObj;
    }



}
