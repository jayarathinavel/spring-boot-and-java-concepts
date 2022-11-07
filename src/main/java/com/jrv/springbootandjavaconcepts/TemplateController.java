package com.jrv.springbootandjavaconcepts;

import com.jrv.springbootandjavaconcepts.features.students.Student;
import com.jrv.springbootandjavaconcepts.features.students.StudentController;
import com.jrv.springbootandjavaconcepts.security.auth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public String studentsView(Model model, @AuthenticationPrincipal ApplicationUser applicationUser){
        if (applicationUser.getAuthorities().toString().contains("ROLE_ADMIN")){
            model.addAttribute("studentsList", studentController.getAllStudents());
        }
        else if(applicationUser.getAuthorities().toString().contains("ROLE_STUDENT")){
            model.addAttribute("student", applicationUser.getUsername());
        }
        return "students";
    }

    @GetMapping("/views/addStudent")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "add-student";
    }

    @GetMapping("/views/editStudent/{id}")
    public String editStudent(@PathVariable("id") int id, Model model){
        Optional<Student> student = studentController.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/forms/addStudent")
    public String addStudent(@ModelAttribute("student") Student student){
        studentController.registerNewStudent(student);
        return "redirect:/views/students";
    }

    @PostMapping("/forms/editStudent")
    public String editStudent(@ModelAttribute("student") Student student){
        studentController.updateStudent(student);
        return "redirect:/views/students";
    }

    @GetMapping("/actions/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        studentController.deleteStudent(id);
        return "redirect:/views/students";
    }
}
