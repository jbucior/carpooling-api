package com.carazem.auth;

import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public Long currentUserId() {
        return new Long(1);
    }
}
