package models;

import constraints.FieldMatch;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@FieldMatch(first = "passwordClear", second = "confirmPassword", message = "{user.pass.not.match}")
public class User implements Serializable, UserDetails {
    
    /* Marker interface for grouping validations to be applied at the time of creating a (new) user. */
    public interface UserCreation{}
    /* Marker interface for grouping validations to be applied at the time of updating a (existing) user. */
    public interface UserUpdate{}
    /* Marker interface for grouping validations to be applied at the time of updating a user status by administrator. */
    public interface UserStatusUpdate{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="{user.username.notnull}", groups={ UserCreation.class, UserUpdate.class })
    @Size(min=5, max=15, message="{user.username.size}", groups={ UserCreation.class, UserUpdate.class })
    @Column(nullable = false, length = 30, unique = true)
    private String username;
    
    @NotBlank(message="{user.pass.notnull}", groups={ UserCreation.class })
    @Size(min=8, max=25, message="{user.pass.size}", groups={ UserCreation.class })
    @Transient
    private String passwordClear;
    
    @NotBlank(message="{user.confirm.pass.notnull}", groups={ UserCreation.class })
    @Transient
    private String confirmPassword;
    
    @Column(length = 60)
    private String password;
    
    @NotBlank(message="{user.email.notnull}", groups={ UserCreation.class, UserUpdate.class })
    @Email(message="{user.email.invalid}", groups={ UserCreation.class, UserUpdate.class })
    @Column(nullable = false, length = 90, unique = true)
    private String email;
    
    @NotNull(message="{user.enabled.notnull}", groups={ UserStatusUpdate.class })
    private Boolean enabled = true;
    
    @NotBlank(message="{user.fullname.notnull}", groups={ UserCreation.class, UserUpdate.class })
    @Size(min=8, max=25, message="{user.fullname.size}", groups={ UserCreation.class, UserUpdate.class })
    @Column(length = 100)
    private String fullName;
    
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> posts = new HashSet();
    
    @Column(nullable = true)
    private Date lastLoginAccess;
    

    @ManyToMany(fetch = FetchType.EAGER, cascade= { CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(
      name="USER_ROLES",
      joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
    private Set<Role> roles = new HashSet();
   

    public User() {}

    public User(String username, String email, String fullName, Date lastLoginAccess) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.lastLoginAccess = lastLoginAccess;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
        return enabled;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", passwordClear=" + passwordClear + ", confirmPassword=" + confirmPassword + ", password=" + password + ", email=" + email + ", enabled=" + enabled + ", fullName=" + fullName + ", posts=" + posts + ", lastLoginAccess=" + lastLoginAccess + ", roles=" + roles + '}';
    }
}
