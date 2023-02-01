package com.jrv.springbootandjavaconcepts.security;

import com.jrv.springbootandjavaconcepts.Constants;
import com.jrv.springbootandjavaconcepts.utils.MessageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        ResponseDTO response = new ResponseDTO();
        MessageDTO messageDTO;
        try {
            if (!userRepository.checkIfUserExists(user.getUsername())) {
                throw new UsernameNotFoundException("Username not found");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            String sessionId = sessionRegistry.registerSession(user.getUsername());
            response.setSessionId(sessionId);
            messageDTO = new MessageDTO(Constants.SUCCESS, Constants.LOGGED_IN_SUCCESSFULLY);
        } catch (Exception e) {
            messageDTO = new MessageDTO(Constants.ERROR, e.getMessage());
        }
        response.setMessage(messageDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@RequestBody UserDTO user) {
        MessageDTO messageDTO;
        try {
            if (userRepository.checkIfUserExists(user.getUsername())) {
                throw new UsernameNotFoundException("Username already exists");
            }
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userEntity);
            messageDTO = new MessageDTO(Constants.SUCCESS, Constants.REGISTERED_SUCCESSFULLY);
        } catch (Exception e) {
            messageDTO = new MessageDTO(Constants.ERROR, e.getMessage());
        }
        return ResponseEntity.ok(messageDTO);
    }
}
