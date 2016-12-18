/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import models.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergio
 */
@Component
public class RoleStringConverter implements Converter<Role, String> {

    @Override
    public String convert(Role role) {
        return role.getId().toString();
    }
    
}
