/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author sergio
 */
public interface PostByAuthor {
    Long getId();
    String getTitle();
    String getSubtitle();
    @Value("#{target.author.fullName}")
    String getAuthorName();
    Date getDate();
}
