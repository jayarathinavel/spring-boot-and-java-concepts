package com.jrv.springbootandjavaconcepts.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE)
    );

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
        Set<SimpleGrantedAuthority> simpleGrantedAuthoritiesPermissions = getPermissions().stream()
                .map((permission -> new SimpleGrantedAuthority(permission.getPermission())))
                .collect(Collectors.toSet());
        simpleGrantedAuthoritiesPermissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthoritiesPermissions;

    }
}
