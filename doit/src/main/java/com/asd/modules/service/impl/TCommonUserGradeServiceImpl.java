package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.modules.pojo.usergrade.model.TCommonUserGrade;
import com.asd.modules.service.TCommonUserGradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户岗位关联操作service实现
 * Created by zlp on 2017/1/10.
 */
@Service("tCommonUserGradeService")
public class TCommonUserGradeServiceImpl extends BaseDaoImpl<TCommonUserGrade> implements TCommonUserGradeService {

    /**
     * 根据条件查询用户岗位
     * @param param 查询条件
     * @param order 排序
     * @return 用户岗位集合
     */
    @Override
    public List<TCommonUserGrade> getTCommonUserGradeListByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param,order);
    }

    /**
     * 保存用户岗位关系
     * @param userGrade 用户岗位关系实体
     */
    @Override
    public void save(TCommonUserGrade userGrade) {
        super.save(userGrade);
    }
}
