/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author sergio
 */
public interface PostInfo {
    Long getId();
    String getTitle();
    String getSubtitle();
    @Value("#{target.author.fullName != null ? target.author.fullName : target.author.username}")
    String getAuthorName();
    @Value("#{dates.format(target.date, 'dd-MMM-yyyy')}")
    String getPublication();
}
