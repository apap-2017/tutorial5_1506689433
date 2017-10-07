package com.example.tutorial5.service;

import com.example.tutorial5.dao.CourseMapper;
import com.example.tutorial5.model.CourseModel;
import com.example.tutorial5.model.StudentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceDatabase implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public CourseModel selectCourse(String id_course) {
        log.info("select course with id_course {}", id_course);
        return courseMapper.selectCourse(id_course);
    }

}

