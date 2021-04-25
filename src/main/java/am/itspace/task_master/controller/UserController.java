package am.itspace.task_master.controller;


import am.itspace.task_master.model.CurrentUser;
import am.itspace.task_master.model.User;
import am.itspace.task_master.model.UserType;
import am.itspace.task_master.service.impl.EmailServiceImpl;
import am.itspace.task_master.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final EmailServiceImpl emailService;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @GetMapping("/")
    public String main(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "index";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getDefaultMessage());
            }
            modelMap.addAttribute("errorMessage", stringBuilder.toString());
            return "register";
        }
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (!byEmail.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            emailService.sendSimpleMessage(user.getEmail(), "Welcome Subject",
                    " hello" + user.getName() + " you have successfully registered" );
            log.debug("user  with {} email was registered", user.getEmail());
            return "redirect:/signIn";
        }
        return "register";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "/signIn";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/signIn";
        }
        User user = currentUser.getUser();
        if (user.getUserType() == UserType.ADMIN) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/allUsers")
    public String allUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.users());
        return "allUsers";
    }

    @GetMapping("/user")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        if (user.getId() != 0) {
            modelMap.addAttribute("user", user);
        }
        return "user";
    }


    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getUserType() == UserType.ADMIN) {
            userService.delete(id);
        }
        return "redirect:/allUsers";
    }

}
