package com.somosmas.app.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequest {

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")
    @NotNull(message = "Email cannot be null.")
    private String email;

    @Size(min = 4, max = 20, message = "Password must have between 4 and 20 characters.")
    @NotNull(message = "Password cannot be null.")
    private String password;
    private String firstName;
    private String lastName;
    private String photo;

    public UserDetailsRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}


