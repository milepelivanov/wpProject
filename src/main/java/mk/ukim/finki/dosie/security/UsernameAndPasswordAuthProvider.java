package mk.ukim.finki.dosie.security;

import mk.ukim.finki.dosie.model.Professor;
import mk.ukim.finki.dosie.model.exceptions.ProfessorNotFoundException;
import mk.ukim.finki.dosie.model.usersecurity.UserPrincipal;
import mk.ukim.finki.dosie.service.ProfessorService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.naming.AuthenticationException;
import java.util.ArrayList;


@Controller
public class UsernameAndPasswordAuthProvider implements AuthenticationProvider {
    private final ProfessorService userService;
    private final PasswordEncoder passwordEncoder;


    public UsernameAndPasswordAuthProvider(ProfessorService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)  {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Professor professor = new Professor("","","","",new ArrayList<>(),new ArrayList<>());
        try{
            professor = this.userService.findProfessorById(username);
        }catch (ProfessorNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        UserDetails userDetails = new UserPrincipal(professor);
        System.out.println("userDetails: " + userDetails.getUsername() + userDetails.getPassword());
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Password is incorrect!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
