package com.example.Nutri_Nest.service;

import com.example.Nutri_Nest.entity.Users;
import com.example.Nutri_Nest.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserDetailsRepo userDetailsRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public String createUser(Users users) {
        //encoding
        users.setPwd(bCryptPasswordEncoder.encode(users.getPwd())); //encodedpWD
        userDetailsRepo.save(users);
        return "User is created";
    }
}

