package com.aliwo.controller;

import com.aliwo.entity.Student;
import com.aliwo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/insertStudent", method = RequestMethod.POST)
    @ResponseBody
    public Integer insetStudent(Student student) {
        return studentService.insertStudent(student);
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> findStudentsBelowAge(Integer age) {
        return studentService.findStudentsBelowAge(age);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public Integer countStudent() {
        return studentService.countStudent();
    }
}
