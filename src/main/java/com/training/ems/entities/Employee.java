package com.training.ems.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee extends UserCoreObject implements UserDetails {

    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private List<PeripheralRequest> peripheralRequestList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(super.getPermissionList().stream()
                .map(permission -> new SimpleGrantedAuthority(getPermissionList().toString())).toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + super.getRole()));
        return authorities;
    }

    @Override
    public String getUsername(){
        return this.username;
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
