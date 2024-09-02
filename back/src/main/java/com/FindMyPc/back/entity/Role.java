package com.FindMyPc.back.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.FindMyPc.back.user.Permission;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.FindMyPc.back.user.Permission.ADMIN_CREATE;
import static com.FindMyPc.back.user.Permission.ADMIN_DELETE;
import static com.FindMyPc.back.user.Permission.ADMIN_READ;
import static com.FindMyPc.back.user.Permission.ADMIN_UPDATE;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE
    ));

	  @Getter
	    private final Set<Permission> permissions;

	    public List<SimpleGrantedAuthority> getAuthorities() {
	        var authorities = permissions.stream()
	                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	                .collect(Collectors.toList());
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	        return authorities;
	    }
	}