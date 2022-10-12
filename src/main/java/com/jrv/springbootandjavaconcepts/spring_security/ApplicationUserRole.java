package com.jrv.springbootandjavaconcepts.spring_security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE)
    ),
    ADMIN_TRAINEE(Sets.newHashSet(
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.STUDENT_READ
    )
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
