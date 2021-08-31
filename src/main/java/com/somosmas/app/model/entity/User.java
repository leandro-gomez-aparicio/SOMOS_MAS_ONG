package com.somosmas.app.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user")
<<<<<<< HEAD
public class User implements Serializable{
    
=======
public class User implements Serializable {

>>>>>>> master
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(unique = true, name  = "id_user")
=======
    @Column(unique = true, name = "id_user")
>>>>>>> master
    private Long idUser;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

<<<<<<< HEAD
    @Column( unique = true, nullable = false, name = "email")
=======
    @Column(unique = true, nullable = false, name = "email")
>>>>>>> master
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Temporal(TemporalType.TIMESTAMP)
<<<<<<< HEAD
    @Column(name = "time_stamp")
    private Timestamp timeStamp;
=======
    @Column(name = "timestamp")
    private Timestamp timestamp;
>>>>>>> master

    @Column(name = "soft_delete")
    private boolean softDelete;

<<<<<<< HEAD
    public User(Long idUser, String firstName, String lastName, String email, String password, String photo, Timestamp timeStamp) {
=======
    public User(Long idUser, String firstName, String lastName, String email, String password, String photo, Timestamp timestamp) {
>>>>>>> master
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.photo = photo;
<<<<<<< HEAD
        this.timeStamp = timeStamp;
    }
    
    public Long getIdUser() {
        return idUser;
    }
    
=======
        this.timestamp = timestamp;
    }

    public Long getIdUser() {
        return idUser;
    }

>>>>>>> master
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

<<<<<<< HEAD
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
=======
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
>>>>>>> master
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }
<<<<<<< HEAD
    
=======

>>>>>>> master
}
