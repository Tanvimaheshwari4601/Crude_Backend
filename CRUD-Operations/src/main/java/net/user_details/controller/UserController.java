package net.user_details.controller;


import net.user_details.dto.Login;
import net.user_details.exception.ResourceNotFoundException;
import net.user_details.model.User;
import net.user_details.requestmodel.UserRequestModel;
import net.user_details.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping( "/api/v1/user")
public class UserController {

    private static final String APPROVED = "APPROVED";
    private static final String PENDING = "PENDING";
    private static final String REJECTED = "REJECTED";

    private static final String MESSAGE = "No user exist with such id: ";
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService uservice;


    //Get all details of user
    @GetMapping
    public List<User> getALLUser(){
        return (List<User>) uservice.findAll();
    }



    //create new user in details
    @PostMapping
    public User createUser(@Valid  @RequestBody UserRequestModel user) {
        Optional<User> userObj=uservice.findByEmail(user.getEmailid());
        if(userObj.isPresent()){
            throw new ResourceNotFoundException("User already exist");
        }
        if(user.getRole().equals("Admin")){
            user.setApprovedStatus(PENDING);
            user.setApproved(false);
        }
        else{
            user.setApprovedStatus(APPROVED);

            user.setApproved(true);
        }
        User convertedUser = convertToEntity(user);
        System.out.println("Converted User" + convertedUser.getEmailid());
        User savedUser = uservice.save(convertedUser);
        savedUser.setPassword(null);
        return savedUser;
    }


    //get user by id
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id){
        Object user= uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + id));
        return ResponseEntity.ok(user);
    }

    //Update the user details
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody UserRequestModel user) {
//      IF
        User updateUser = uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + id));
        if(user.getEmailid() != updateUser.getEmailid()){
            Optional<User> userObj=uservice.findByEmail(user.getEmailid());
           
            if(userObj.isPresent()){

                throw new ResourceNotFoundException("Emailid already exist");
            }

        }
        updateUser.setFirstname(user.getFirstname());
        updateUser.setLastname(user.getLastname());

        updateUser.setEmailid(user.getEmailid());
        updateUser.setCity(user.getCity());
        updateUser.setPhonenumber(user.getPhonenumber());

        uservice.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }


    //delete the user details
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User user = uservice.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE + id));

        uservice.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PostMapping("/login")
    public User loginUser(@Valid  @RequestBody Login login) {

        Optional<User> user =uservice.findByEmail(login.emailid);


        if(!user.isPresent() || !user.get().getPassword().equals(login.password)){
            throw new ResourceNotFoundException("Invalid Credentials");

        }
        boolean status = user.get().getApproved();
        if(!status){
            throw new ResourceNotFoundException("Your account is not approved yet.");


        }
        user.get().setPassword(null);
        return user.get();
    }

    @GetMapping("/getAdmins")
    public List<User> getAdmin(){
        return (List<User>) uservice.getAllAdmin();
    }


    @GetMapping("/getApprovedUsers/{currentUserId}")
    public List<User> getAllApprovedUser(@PathVariable long currentUserId){
        return uservice.getAllApprovedUser(APPROVED, currentUserId);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<User> adminAccess(@PathVariable long id){
        User adminAccess = uservice.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No user exist with such id:  " + id));

        adminAccess.setApprovedStatus(APPROVED);
        adminAccess.setApproved(Boolean.TRUE);

        uservice.save(adminAccess);
        return ResponseEntity.ok(adminAccess);
    }
    @PostMapping("/{id}/deny")
    public ResponseEntity<User> adminAccessDeny(@PathVariable long id){
        User adminAccessDeny = uservice.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No user exist with such id:  " + id));
        adminAccessDeny.setApprovedStatus(REJECTED);
        adminAccessDeny.setApproved(Boolean.FALSE);
        uservice.save(adminAccessDeny);
        return ResponseEntity.ok(adminAccessDeny);
    }
    private UserRequestModel convertToDto(User user) {
        UserRequestModel userRequestModel = modelMapper.map(user, UserRequestModel.class);
        return userRequestModel;
    }

    private User convertToEntity(UserRequestModel userRequestModel)  {
        User user = modelMapper.map(userRequestModel, User.class);
        return user;
    }

}
