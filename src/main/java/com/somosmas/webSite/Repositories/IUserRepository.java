package com.somosmas.webSite.Repositories;

import com.somosmas.webSite.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository< Long, User>{
    
}
