package com.aliwo.dao;

import com.aliwo.entity.GradeInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuyayuan
 * @data 2021年06月01日
 */
@Mapper
public interface GradeInfoDao {
    int insertGradeInfo(GradeInfo gradieInfo);
}
