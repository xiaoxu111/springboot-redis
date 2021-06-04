package com.aliwo.controller;

import com.aliwo.entity.GradeInfo;
import com.aliwo.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xuyayuan
 * @data 2021年06月01日
 */
@Controller
public class GradeInfoController {

    @Autowired
    private GradeInfoService gradeInfoService;

    /**
     * @param gradeInfo
     * @param model
     * @return java.lang.String
     * springBoot事务中的支持，若工程直接或者间接依赖于spring-tx，则框架会自动注入DataSourceTransactionManager事务管理器，
     * 若依赖于spring-boot-data-jpa，则会自动注入JpaTransactionManager事务管理器
     * spring中使用事务，默认spring中发生受检异常时提交，发生运行时异常时回滚
     * 受检异常与非受检异常的区别？
     * 受检异常：发生在编译器，主要包括
     * Java.lang.ClassNotFoundException
     * 　　Java.lang.CloneNotSupportedException
     * 　　Java.lang.IllegalAccessException
     * 　　Java.lang.InterruptedException
     * 　　Java.lang.NoSuchFieldException
     * 　　Java.lang.NoSuchMetodException
     * 非受检异常(运行时异常)，主要包括
     * Java.lang.ArithmeticException
     * 　　Java.lang.ArrayStoreExcetpion
     * 　　Java.lang.ClassCastException
     * 　　Java.lang.EnumConstantNotPresentException
     * 　　Java.lang.IllegalArgumentException
     * 　　Java.lang.IllegalThreadStateException
     * 　　Java.lang.NumberFormatException
     * 　　Java.lang.IllegalMonitorStateException
     * 　　Java.lang.IllegalStateException
     * 　　Java.lang.IndexOutOfBoundsException
     * 　　Java.lang.ArrayIndexOutOfBoundsException
     * 　　Java.lang.StringIndexOutOfBoundsException
     * 　　Java.lang.NegativeArraySizeException’
     * 　　Java.lang.NullPointerException
     * 　　Java.lang.SecurityException
     * 　　Java.lang.TypeNotPresentException
     * 　　Java.lang.UnsupprotedOperationException
     * @author xuyayuan
     * @date 2021/6/2 14:06
     */
    @Transactional
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertGradeInfo(GradeInfo gradeInfo, Model model) throws Exception {
        gradeInfo.setGradeName(gradeInfo.getGradeName());
        gradeInfo.setGradeNo(gradeInfo.getGradeNo());
        model.addAttribute("gradeInfo");
        gradeInfoService.insertGradeInfo(gradeInfo);
        // spring中使用事务，默认spring中发生受检异常时提交
        //if (true) {
        //    throw new Exception("发生受检异常了");
        //}
        // spring中使用事务，发生运行时异常时回滚
        //if (true) {
        //    throw new RuntimeException("发生非受检异常了(运行时异常)");
        //}
        return "jsp/welcome";
    }

}
