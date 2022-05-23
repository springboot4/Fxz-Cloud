package com.fxz.common.security.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-27 16:23
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class FxzAuthUser extends User implements Serializable {

	private static final long serialVersionUID = -6819532868284272852L;

	private Long userId;

	private String avatar;

	private String email;

	private String mobile;

	private String sex;

	private Long deptId;

	private String deptName;

	private String roleId;

	private String roleName;

	private Date lastLoginTime;

	private String description;

	private String status;

	public FxzAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public FxzAuthUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
