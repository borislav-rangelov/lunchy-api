package com.brangelov.lunchy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

public class JsonAuthenticationFilter<T> extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private final Class<T> loginType;
    private final Function<T, String> usernameProvider;
    private final Function<T, String> passwordProvider;

    protected JsonAuthenticationFilter(
            ObjectMapper objectMapper, Class<T> loginType, Function<T, String> usernameProvider,
            Function<T, String> passwordProvider) {
        super(new AntPathRequestMatcher("/login", "POST"));
        this.objectMapper = objectMapper;
        this.loginType = loginType;
        this.usernameProvider = usernameProvider;
        this.passwordProvider = passwordProvider;
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        T object;
        try {
            object = objectMapper.readValue(request.getReader(), loginType);
        } catch (Exception e) {

            throw new AuthenticationServiceException("Invalid request body.");
        }

        String username = usernameProvider.apply(object);
        String password = passwordProvider.apply(object);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    public static JsonAuthenticationFilter<EmailPasswordBody> byEmail(ObjectMapper mapper) {
        return new JsonAuthenticationFilter<>(mapper, EmailPasswordBody.class,
                EmailPasswordBody::getEmail, EmailPasswordBody::getPassword);
    }

    public static JsonAuthenticationFilter<UsernamePasswordBody> byUsername(ObjectMapper mapper) {
        return new JsonAuthenticationFilter<>(mapper, UsernamePasswordBody.class,
                UsernamePasswordBody::getUsername, UsernamePasswordBody::getPassword);
    }

    public static class EmailPasswordBody {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class UsernamePasswordBody {
        private String username;

        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
