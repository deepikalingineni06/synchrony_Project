package com.example.imageUpDown.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUser implements UserDetails {

    public CustomUser(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
	}

	UserEntity userEntity;

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> hashSet = new HashSet<>();
        hashSet.add(new SimpleGrantedAuthority(this.userEntity.getRoles()));
        return hashSet;
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


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userEntity.getPassword();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getUserName();
	}
}
