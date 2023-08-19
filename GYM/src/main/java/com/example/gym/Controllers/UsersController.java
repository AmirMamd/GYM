package com.example.gym.Controllers;

import com.example.gym.DTOs.UsersDTO;
import com.example.gym.Entities.Users;
import com.example.gym.Services.Implementation.UsersService;
import com.example.gym.Services.Jwt.AuthenticationRequest;
import com.example.gym.Services.Jwt.AuthenticationResponse;
import com.example.gym.Services.Jwt.JwtUtil;
import com.example.gym.Services.Jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/")
    public String createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }
        catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"Manager doesn't exist");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//       final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return "users";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the user's roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Redirect based on roles
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return "redirect:/managers"; // Replace with your manager dashboard URL
        }
        else {
            return "redirect:/"; // Default redirect if role not recognized
        }
    }

    @GetMapping("/authenticate")
    public String login(){
        return "sign_in";
    }


    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users",usersService.getUsers());
        return "users";
    }
    @GetMapping("/coaches")
    public String listCoaches(Model model){
        model.addAttribute("users",usersService.getCoaches());
        return "users";
    }
    @GetMapping("/managers")
    public String listManagers(Model model){
        model.addAttribute("users",usersService.getManagers());
        return "users";
    }
    @GetMapping("/users/new")
    public String createUserForm(Model model) {

        Users user = new Users();
        model.addAttribute("user", user);
        return "create_user";

    }
    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") Users user) {
        usersService.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id,
                                @ModelAttribute("user") Users user) {

        // get user from database by id
        Users existingUser = usersService.getUserById(id);
        existingUser.setId(id);
        existingUser.setFullName(user.getFullName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        // save updated user object
        usersService.updateUser(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersService.deleteUserById(id);
        return "redirect:/users";
    }

}
