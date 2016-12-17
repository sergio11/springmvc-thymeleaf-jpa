/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.UserAlredyExistsException;
import models.User;
import java.util.List;
import java.util.Date;

/**
 *
 * @author sergio
 */
public interface UserService {
    void create(User user) throws UserAlredyExistsException;
    void update(User user) throws UserAlredyExistsException;
    List<User> getAllUsers();
    void updateLastLoginAccess(String username, Date lastLoginAccess);
    User findUserByUsername(String username);
}
