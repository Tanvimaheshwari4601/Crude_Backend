package net.user_details.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="password",nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "first_name",nullable = false)
    @NotEmpty
    private String firstname;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(name = "email_id",nullable = false)
    @NotEmpty
    @Email
    private String emailid;

    @Column(name = "City",nullable = false)
    @NotEmpty
    private String city;

    @Column(name = "phone_number",nullable = false)
    @NotEmpty
    @Size(min = 10,max=10, message = "Enter valid 10 digit number")
    private String phonenumber;

    @NotEmpty
    @Column(name = "role",nullable = false)
    private String role;




}
