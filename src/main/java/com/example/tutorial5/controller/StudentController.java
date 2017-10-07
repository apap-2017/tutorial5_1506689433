package com.example.tutorial5.controller;

import java.util.List;

import com.example.tutorial5.model.StudentModel;
import com.example.tutorial5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add() {
        return "form-add";
    }

    //Still using req param since i do not do the previous lab.
    @RequestMapping("/student/add/submit")
    public String addSubmit(
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa) {
        StudentModel student = new StudentModel(npm, name, gpa);
        studentDAO.addStudent(student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view(Model model,
                       @RequestParam(value = "npm", required = false) String npm) {
        StudentModel student = studentDAO.selectStudent(npm);
        return viewHelper(student, npm, model);
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath(Model model,
                           @PathVariable(value = "npm") String npm) {
        StudentModel student = studentDAO.selectStudent(npm);
        return viewHelper(student, npm, model);
    }

    private String viewHelper(StudentModel student, String npm, Model model) {
        if (student != null) {
            model.addAttribute("student", student);
            return "view";
        } else {
            model.addAttribute("npm", npm);
            return "not-found";
        }
    }

    @RequestMapping("/student/viewall")
    public String view(Model model) {
        List<StudentModel> students = studentDAO.selectAllStudents();
        model.addAttribute("students", students);

        return "viewall";
    }

    //Still using req param since i do not do the previous lab.
    @RequestMapping("/student/delete/{npm}")
    public String delete(Model model, @PathVariable(value = "npm") String npm) {
        studentDAO.deleteStudent(npm);

        return "delete";
    }

}
