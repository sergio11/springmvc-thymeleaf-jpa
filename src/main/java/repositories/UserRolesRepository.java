/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.List;
import models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author sergio
 */
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
    @Query("select a.role from UserRole a, User b where b.username=?1 and a.userid=b.id")
    List<String> findRoleByUsername(String username);
}
