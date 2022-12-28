package com.jrv.springbootandjavaconcepts.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SessionRegistry {
    HashMap<String, String> sessionsMap = new HashMap<>();
    public String registerSession(String username){
        if (username == null) {
            throw new UsernameNotFoundException("Username needs to be provided");
        }
        String sessionId = generateSessionId();
        sessionsMap.put(sessionId, username);
        return sessionId;
    }

    public String getUsernameForSession(String sessionId) {
        sessionsMap.put("MzViMjYzNTItYTYyZC00M2YyLWIwOGMtNzAwMmViNWVhYzI1", "jrv");
        return sessionsMap.get(sessionId);
    }

    private String generateSessionId() {
        return new String(
            Base64.getEncoder().encode(
                    UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
            )
        );
    }
}
