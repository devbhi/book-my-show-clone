package com.acciojob.bookmyshowapplications.Service;

import com.acciojob.bookmyshowapplications.Models.User;
import com.acciojob.bookmyshowapplications.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addUser(User user){

//        userRepository.save(user);
       user = userRepository.save(user);


        SimpleMailMessage message = new SimpleMailMessage();

       message.setSubject("Welcome to Book Your Show Application");
       message.setFrom("springacciojob@gmail.com");
       message.setTo(user.getEmailId());

       String body = "Hi "+user.getName()+"! "+"\n"+
                        "Welcome to Book your show Application !! , Feel free " +
               "to browse the movies and use Coupon START100 for an instant discount";

       message.setText(body);

       javaMailSender.send(message);


       return "The user has been saved to the DB with userId"+user.getUserId();
    }


}
