package com.example.javaapi.service;

import com.example.javaapi.dto.UserDto;
import com.example.javaapi.entity.Users;
import com.example.javaapi.repository.UserRepository;
import com.example.javaapi.response.EmailData;
import com.example.javaapi.until.EmailProviderService;
import com.example.javaapi.until.JwtGenerator;
import com.example.javaapi.until.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserServiceImpl  implements UserServices {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder encryption;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtGenerator generate;
//    @Autowired
//    private EmailProviderService em;
    @Autowired
    private EmailData emailData;
    private Users users = new Users();
    @Override
    public Users login(UserDto information) {
        Users user = repository.findByEmail(information.getEmail());
        if (user != null) {
//            String userRole = information.getRole();
//            String fetchRole = user.getRole();
            if ((user.isVerified() == true)) {
                if (encryption.matches(information.getPassword(), user.getPassword())) {
                    System.out.println(generate.jwtToken(user.getUserId()));
                    return user;
                } else {
                    throw new UserException("Invalid password");
                }
            } else {
                throw new UserException("Invalid verified");
            }
        }
        else {
                throw new UserException("User Not present enter valid your email id");
        }

    }

    @Override
    public boolean register(UserDto information) {
        Users user = repository.findByEmail(information.getEmail());
        if (user == null) {
            users = modelMapper.map(information, Users.class);
            users.setCreatedDate(LocalDateTime.now());
            String epassword = encryption.encode(information.getPassword());
            // setting the some extra information and encrypting the password
            users.setPassword(epassword);
            System.out.println("password is" + epassword);
            users.setVerified(true);
            // calling the save method
            users.setRole("admin");
            users = repository.save(users);
//            String mailResponse =
//                    "http://localhost:8080/user/verify/"+
//                            generate.jwtToken(users.getUserId());
//            emailData.setEmail(users.getEmail());
//            emailData.setSubject("your Registration is successful");
//            emailData.setBody(mailResponse);
//            System.out.println(mailResponse);
            return true;
        } else {
            throw new UserException("user already exist with the same mail id");
        }
    }
}
