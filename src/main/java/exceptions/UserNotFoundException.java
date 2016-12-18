/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author sergio
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")
public class UserNotFoundException extends RuntimeException {}
