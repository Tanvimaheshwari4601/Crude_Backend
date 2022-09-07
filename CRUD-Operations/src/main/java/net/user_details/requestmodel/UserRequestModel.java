package net.user_details.requestmodel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {

    private long id;

    private String password;

    private String firstname;

    private String lastname;

    private String emailid;

    private String city;

    private String phonenumber;


    private String role;

    private Boolean approved=true;

    private String approvedStatus="PENDING";


    UserRequestModel(long id, String emailid, String password, String firstname, String lastname, String city, String phonenumber, String role, Boolean approved, String approvedStatus){
        this.id = id;
        this.emailid = emailid;
        this.password =  password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phonenumber = phonenumber;
        this.role = role;
        this.approved = approved;
        this.approvedStatus = approvedStatus;
    }




}
