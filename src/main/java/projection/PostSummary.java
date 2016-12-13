/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import org.springframework.beans.factory.annotation.Value;
import java.util.Date;

/**
 *
 * @author sergio
 */
public interface PostSummary {
    Long getId();
    String getTitle();
    String getSubtitle();
    @Value("#{target.author.fullName != null ? target.author.fullName : target.author.username}")
    String getAuthorName();
    Date getDate();
}
