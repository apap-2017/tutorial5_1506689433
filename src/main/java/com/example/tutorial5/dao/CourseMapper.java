package com.example.tutorial5.dao;

import com.example.tutorial5.model.CourseModel;
import com.example.tutorial5.model.StudentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CourseMapper
{
    @Select("select id_course, name, credits from course where id_course = #{id_course}")
    @Results(value = {
            @Result(property="idCourse", column="id_course"),
            @Result(property="name", column="name"),
            @Result(property="credits", column="credits"),
            @Result(property="students", column="students",
                    javaType = List.class,
                    many=@Many(select="selectStudents"))
    })
    CourseModel selectCourse(@Param("id_course") String id_course);

    @Select("select npm, name, gpa " +
            "from studentcourse join student " +
            "on studentcourse.npm = student.npm " +
            "where studentcourse.id_course = #{id_course}")
    List<StudentModel> selectStudents(@Param("id_course") String id_course);


}
