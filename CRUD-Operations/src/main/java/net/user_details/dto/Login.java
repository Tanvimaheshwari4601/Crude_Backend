package net.user_details.dto;

public class Login {
     public String emailid;
     public String password;

     Login(String email, String password){
          this.emailid = email;
          this.password =  password;
     }
}
