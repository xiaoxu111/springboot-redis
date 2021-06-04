package com.aliwo.service.impl;

import com.aliwo.dao.GradeInfoDao;
import com.aliwo.entity.GradeInfo;
import com.aliwo.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuyayuan
 * @data 2021年06月02日
 */
@Service
public class GradeInfoServiceImpl implements GradeInfoService {
    @Autowired
    private GradeInfoDao gradeInfoDao;
    @Override
    public int insertGradeInfo(GradeInfo gradingInfo) {
        return gradeInfoDao.insertGradeInfo(gradingInfo);
    }
}
