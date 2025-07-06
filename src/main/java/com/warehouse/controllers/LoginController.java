package com.warehouse.controllers;

import com.warehouse.beans.LoginForm;
import com.warehouse.beans.User;
import com.warehouse.service.UserService;

import javax.validation.Valid;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult bindingResult,
            Model model,
            HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.getUserByUsername(loginForm.getUsername());
        if (user != null && userService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard";  // Redirect to dashboard handled by DashboardController
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
