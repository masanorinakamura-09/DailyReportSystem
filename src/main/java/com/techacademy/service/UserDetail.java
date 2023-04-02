package com.techacademy.service;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techacademy.entity.Employee;

public class UserDetail implements UserDetails{
    private static final long serialVersionUID=1L;

    private final Employee employee;
    private final List<SimpleGrantedAuthority> authorities;

    public UserDetail(Employee employee) {
        this.employee=employee;
        List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(employee.getAuthentication().getRole().toString()));
        this.authorities = authorities;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getUserRoll() {
        return employee.getAuthentication().getRole().name();
    }

    public String getLoginName() {
        return employee.getName();
    }

    public Integer getUserId() {
        return employee.getId();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 自動生成されたメソッド・スタブ
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO 自動生成されたメソッド・スタブ
        return employee.getAuthentication().getPassword();
    }

    @Override
    public String getUsername() {
        // TODO 自動生成されたメソッド・スタブ
        return employee.getAuthentication().getCode();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

}
