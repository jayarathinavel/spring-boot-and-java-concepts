package com.jrv.springbootandjavaconcepts.security.auth;

import com.jrv.springbootandjavaconcepts.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.jrv.springbootandjavaconcepts.security.ApplicationUserRole.*;

@Repository
public class ApplicationUserDAOService implements ApplicationUserDAO{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        Map<String, ApplicationUserRole> userRoleMap = new HashMap<>();
        userRoleMap.put("STUDENT", STUDENT);
        userRoleMap.put("ADMIN", ADMIN);
        List<ApplicationUser> applicationUserList = new ArrayList<>();
        for(UserEntity user : userRepository.findAll()){
            applicationUserList.add(
                new ApplicationUser(
                    userRoleMap.get(user.getRole()).grantedAuthorities(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getUserName(),
                    true,
                    true,
                    true,
                    true
                )
            );
        }
        return applicationUserList;
    }
}
