package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import app.model.User;
import app.service.AppService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppService appService;

    @Autowired
    public AdminController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping({"", "list"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        model.addAttribute("allRoles", appService.findAllRoles());
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "admin-page";
    }

    @GetMapping("/{id}/profile")
    public String showUserProfileModal(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("allRoles", appService.findAllRoles());
        model.addAttribute("user", appService.findUser(userId));
        return "fragments/user-form";
    }

    @PostMapping
    public String insertUser(@Valid @ModelAttribute("user") User user) {
        appService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String deleteUser(@ModelAttribute("user") User user) {
        appService.deleteUser(user.getId());
        return "redirect:/admin";
    }
}
