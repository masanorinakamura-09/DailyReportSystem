package com.techacademy.service;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techacademy.entity.Employee;

public class UserDetail implements UserDetails{
    private static final long serialVersionUID=1L;

    private final Employee employee;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetail(Employee employee) {
        this.employee=employee;
        this.authorities=new ArrayList<GrantedAuthority>();
    }

    public Employee getEmployee() {
        return employee;
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
