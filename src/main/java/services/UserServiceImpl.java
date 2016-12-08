/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.UserAlredyExistsException;
import javax.transaction.Transactional;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

/**
 *
 * @author sergio
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    @Override
    public void registerNewUserAccount(User user) throws UserAlredyExistsException {
        
        if (userRepository.existsUserWithEmailOrUsername(user.getEmail(), user.getUsername()) > 0){   
            throw new UserAlredyExistsException(user.getEmail(), user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPasswordClear()));
        userRepository.save(user);
    }
}
