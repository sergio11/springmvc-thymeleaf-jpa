/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import models.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sergio
 */
public interface FileImageRepository extends JpaRepository<FileImage, Long>{
    FileImage findByPostId(Long id);
}
