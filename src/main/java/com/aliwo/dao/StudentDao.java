package com.aliwo.dao;

import com.aliwo.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 */
@Mapper
public interface StudentDao {
    int insertStudent(Student student);
    List<Student> findStudentsBelowAge(Integer age);

    Integer countStudent();
}
