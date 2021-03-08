package com.herokuapp.crosses.model.persist;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usr")
public class User extends PersistEntity implements UserDetails {
    private String login;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        GrantedAuthority grantedAuthority = () -> "null";

        return List.of((GrantedAuthority) () -> role.toString());
    }

    @Override
    public String getUsername() {
        return login;
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
}