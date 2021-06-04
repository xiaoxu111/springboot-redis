package com.aliwo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xuyayuan
 * @data 2021年06月04日
 */
@Data
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
}
