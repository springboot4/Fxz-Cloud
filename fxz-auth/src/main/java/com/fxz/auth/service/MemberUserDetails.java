package com.fxz.auth.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * 用户认证信息
 *
 * @author fxz
 */
@Data
public class MemberUserDetails implements UserDetails {

	private Long memberId;

	private String username;

	private Boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		return collection;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
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
		return this.enabled;
	}

}
