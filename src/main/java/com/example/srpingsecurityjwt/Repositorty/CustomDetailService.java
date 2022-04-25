package com.example.srpingsecurityjwt.Repositorty;

import com.example.srpingsecurityjwt.Entity.RoleEntity;
import com.example.srpingsecurityjwt.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Transactional
public class CustomDetailService implements UserDetails {

    @Autowired
    UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>(userEntity.getRoles().size());
        for(RoleEntity role : userEntity.getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        if(userEntity.getPassword() == null)
            return  null;
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        if(userEntity.getUsername() == null)
            return  null;
        return userEntity.getUsername();
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
