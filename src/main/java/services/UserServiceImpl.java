/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.EmailExistsException;
import exceptions.UsernameExistsException;
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
    public void registerNewUserAccount(User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {   
            throw new EmailExistsException(user.getEmail());
        }
        if (usernameExist(user.getUsername())) {   
            throw new UsernameExistsException(user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPasswordClear()));
        userRepository.save(user);
    }
    
    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    
    private boolean usernameExist(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
