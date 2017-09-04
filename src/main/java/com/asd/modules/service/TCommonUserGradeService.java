package com.asd.modules.service;

import com.asd.modules.pojo.usergrade.model.TCommonUserGrade;

import java.util.List;
import java.util.Map;

/**
 * 用户岗位关联操作sercice
 * Created by zlp on 2017/1/10.
 */
public interface TCommonUserGradeService {

    /**
     * 根据条件查询用户岗位
     * @param param 查询条件
     * @param order 排序
     * @return 用户岗位集合
     */
    public List<TCommonUserGrade> getTCommonUserGradeListByQueryRule(Map param, Map order);

    /**
     * 保存用户岗位关系
     * @param userGrade 用户岗位关系实体
     */
    public void save(TCommonUserGrade userGrade);
}
