package com.jrv.springbootandjavaconcepts.security.auth;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
