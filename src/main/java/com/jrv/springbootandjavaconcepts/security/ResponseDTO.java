package com.jrv.springbootandjavaconcepts.security;

import com.jrv.springbootandjavaconcepts.utils.MessageDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String sessionId;
    private MessageDTO message;
}
