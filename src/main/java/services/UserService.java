/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import exceptions.UserAlredyExistsException;
import models.User;

/**
 *
 * @author sergio
 */
public interface UserService {
    void registerNewUserAccount(User user) throws UserAlredyExistsException;
}
