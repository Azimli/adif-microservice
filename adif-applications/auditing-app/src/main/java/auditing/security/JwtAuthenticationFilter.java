package auditing.security;

import auditing.model.UsersLogin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import static auditing.security.JwtProperties.JWT_TOKEN_EXPIRATION_TIME;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UsersLogin loginDetails = objectMapper
                    .readValue(req.getInputStream(), UsersLogin.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDetails.getUsername(),
                            loginDetails.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME * 1000))
                .sign(Algorithm.HMAC512(JwtProperties.JWT_SECRET.getBytes()));

        // burda token elde etmis oluruq
        String content = objectMapper.writeValueAsString(new LoginJwtTokenResponse(token));

        // bu hissede response body olaraq tokeni gostermis oluruq
        PrintWriter writer = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        writer.write(content);
        writer.flush();
    }

}