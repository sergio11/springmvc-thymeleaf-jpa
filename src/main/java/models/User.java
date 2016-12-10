package models;

import constraints.FieldMatch;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "users")
@FieldMatch(first = "passwordClear", second = "confirmPassword", message = "{user.pass.not.match}")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="{user.username.notnull}")
    @Size(min=5, max=15, message="{user.username.size}")
    @Column(nullable = false, length = 30, unique = true)
    private String username;
    
    @NotBlank(message="{user.pass.notnull}")
    @Size(min=8, max=25, message="{user.pass.size}")
    @Transient
    private String passwordClear;
    
    @NotBlank(message="{user.confirm.pass.notnull}")
    @Transient
    private String confirmPassword;
    
    @Column(length = 60)
    private String password;
    
    @NotBlank(message="{user.email.notnull}")
    @Email(message="{user.email.invalid}")
    @Column(nullable = false, length = 90, unique = true)
    private String email;
    
    private Boolean enabled = true;
    
    @NotBlank(message="{user.fullname.notnull}")
    @Size(min=8, max=25, message="{user.fullname.size}")
    @Column(length = 100)
    private String fullName;
    
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> posts = new HashSet<Post>();
    
    @Column(nullable = true)
    private Date lastLoginAccess;
    
    @Transient
    private List<String> userRoles;

    public User() {}

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.passwordClear = user.passwordClear;
        this.confirmPassword = user.confirmPassword;
        this.password = user.password;
        this.email = user.email;
        this.fullName = user.fullName;
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

    public String getPasswordClear() {
        return passwordClear;
    }

    public void setPasswordClear(String passwordClear) {
        this.passwordClear = passwordClear;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
    
    public void addPost(Post post){
        this.posts.add(post);
    }

    public Date getLastLoginAccess() {
        return lastLoginAccess;
    }

    public void setLastLoginAccess(Date lastLoginAccess) {
        this.lastLoginAccess = lastLoginAccess;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles= StringUtils.collectionToCommaDelimitedString(userRoles);   
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", passwordClear=" + passwordClear + ", confirmPassword=" + confirmPassword + ", password=" + password + ", email=" + email + ", enabled=" + enabled + ", fullName=" + fullName + ", posts=" + posts + '}';
    }
}
