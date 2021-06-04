package com.aliwo.service;

import com.aliwo.entity.Student;

import java.util.List;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 */
public interface StudentService {

    Integer insertStudent(Student student);

    List<Student> findStudentsBelowAge(Integer age);

    Integer countStudent();
}
