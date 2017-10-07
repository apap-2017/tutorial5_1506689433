package com.example.tutorial5.controller;

import com.example.tutorial5.model.CourseModel;
import com.example.tutorial5.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
    @Autowired
    CourseService courseDAO;


    @RequestMapping("/course/view")
    public String view(Model model,
                       @RequestParam(value = "id_course", required = false) String id_course) {
        CourseModel course = courseDAO.selectCourse(id_course);
        return viewHelper(course, id_course, model);
    }


    @RequestMapping("/course/view/{id_course}")
    public String viewPath(Model model,
                           @PathVariable(value = "id_course") String id_course) {
        CourseModel course = courseDAO.selectCourse(id_course);
        return viewHelper(course, id_course, model);
    }

    private String viewHelper(CourseModel course, String id_course, Model model) {
        if (course != null) {
            model.addAttribute("course", course);
            return "view-course";
        } else {
            model.addAttribute("id_course", id_course);
            return "course-not-found";
        }
    }


}
