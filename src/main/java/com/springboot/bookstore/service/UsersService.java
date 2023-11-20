package com.springboot.bookstore.service;

import com.springboot.bookstore.entity.Users;
import com.springboot.bookstore.repository.BookRepo;
import com.springboot.bookstore.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userDetail = userInfoRepository.findByName(username);

        return userDetail.map(UsersDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    //Admin functions
    //Managing users
    public String addUser(Users userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User Added Successfully";
    }

    public String deleteUser(int id) {
        userInfoRepository.deleteById(id);
        return "User deleted successfully";
    }

    public int getUserIdByUsername(String username){
        Optional<Users> user = userInfoRepository.findByName(username);
        return user.map(Users::getId).orElse(null);
    }

}
