package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30, unique = true)
    private String username;
    @Column(length = 60)
    private String passwordHash;
    @Column(length = 100)
    private String fullName;
    @OneToMany(mappedBy = "author")
    private Set<Post> posts = new HashSet<Post>();

    public User() {
        super();
    }

    public User(String username, String passwordHash, String fullName, Set<Post> posts) {
        super();
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", fullName=" + fullName
                + ", posts=" + posts + "]";
    }
}
