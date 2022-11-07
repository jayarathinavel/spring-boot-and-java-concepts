package com.jrv.springbootandjavaconcepts;

import com.jrv.springbootandjavaconcepts.features.students.StudentController;
import com.jrv.springbootandjavaconcepts.security.auth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @Autowired
    StudentController studentController;

    @GetMapping
    public String getIndexView(){
        return "index";
    }

    @GetMapping("login")
    public String getLoginView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && authentication.getPrincipal() != "anonymousUser"
                ? "redirect:/":"login";
    }

    @GetMapping("login-success")
    public String getLoginSuccess(@AuthenticationPrincipal ApplicationUser applicationUser, Model model){
        model.addAttribute("user", applicationUser);
        return "login-success";
    }

    @GetMapping("/views/students")
    public String studentsView(Model model){
        model.addAttribute("studentsList", studentController.getAllStudents());
        return "students";
    }
}
