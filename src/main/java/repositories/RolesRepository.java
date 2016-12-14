/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sergio
 */
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
