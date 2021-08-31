package com.somosmas.webSite.Models;

import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idUser;
    
    @NotNull
    @Column
    private String firstName;
    
    @NotNull
    @Column
    private String lastName;
    
    @NotNull
    @Column( unique = true)
    private String email;
    
    @NotNull
    @Column
    private String password;
    
    @Column
    private String photo;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
