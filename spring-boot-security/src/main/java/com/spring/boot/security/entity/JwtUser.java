package com.spring.boot.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@Data
public class JwtUser implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled = true;

    private String tenantId;
    private String companyId;
    private String userId;
    private String name;
    private String avatar;
    private String introduction;
    private List<String> routes;

    public JwtUser() {
    }

    public JwtUser(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

}
