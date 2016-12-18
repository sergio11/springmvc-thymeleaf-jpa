/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.RolesRepository;

/**
 *
 * @author sergio
 */
@Component
public class StringRoleConverter implements Converter<String, Role> {
    
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Role convert(String source) {
        return rolesRepository.findOne(Long.parseLong(source));
    }
    
}
