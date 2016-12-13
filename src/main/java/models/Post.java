package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="{post.title.notnull}")
    @Size(min=5, max=60, message="{post.title.size}")
    @Column(nullable = false, length = 300)
    private String title;
    
    @NotBlank(message="{post.subtitle.notnull}")
    @Size(min=5, max=40, message="{post.subtitle.size}")
    @Column(nullable = false, length = 300)
    private String subtitle;

    @NotBlank(message="{post.body.notnull}")
    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User author;
    
    @NotNull
    @Column(nullable = false)
    private Date date = new Date();

    public Post() {}

    public Post(String title, String subtitle, String body, User author, Date date) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
        this.author = author;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addPost(this);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", body=" + body + ", author=" + author + ", date=" + date + '}';
    }
    
}
