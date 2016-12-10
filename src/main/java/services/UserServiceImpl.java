/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.UserAlredyExistsException;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

/**
 *
 * @author sergio
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    @Override
    public void registerNewUserAccount(User user) throws UserAlredyExistsException {
        logger.debug("Registrando nuevo usuario: " + user.getUsername());
        if (userRepository.existsUserWithEmailOrUsername(user.getEmail(), user.getUsername()) > 0){   
            throw new UserAlredyExistsException(user.getEmail(), user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPasswordClear()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(new Sort(Sort.Direction.DESC, "lastLoginAccess", "fullName"));
    }

    @Override
    public void updateLastLoginAccess(String username, Date lastLoginAccess) {
        userRepository.updateLastLoginAccess(username, lastLoginAccess);
    }
}
