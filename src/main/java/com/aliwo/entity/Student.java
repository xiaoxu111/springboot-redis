package com.aliwo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 */
@Data
@NoArgsConstructor
public class Student implements Serializable {
    private Integer id;
    private String studentNo;
    private String username;
    private String password;
    private String realname;
    private String userType;
    private String grade;
    private String classNo;
    private Integer age;
    private String address;
    private String telephone;
    private String email;
    private String avatar;
    private String description;
    private Integer deleted;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Student(Integer id, String studentNo, String username, String password, String realname, Integer age) {
        this.id = id;
        this.studentNo = studentNo;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.age = age;
    }
}
