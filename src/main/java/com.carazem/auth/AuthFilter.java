package com.carazem.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AuthFilter extends OncePerRequestFilter {

    private static final String AUTH_TOKEN = "X-AUTH-TOKEN";
    private FirebaseAuth firebaseAuth;

    public AuthFilter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String firebaseToken = httpServletRequest.getHeader(AUTH_TOKEN);

        if (!StringUtils.isEmpty(firebaseToken)) {
            try {
                Task<FirebaseToken> task = firebaseAuth.verifyIdToken(firebaseToken);
                FirebaseToken result = Tasks.await(task);
                Auth auth = new Auth(result.getEmail());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}


