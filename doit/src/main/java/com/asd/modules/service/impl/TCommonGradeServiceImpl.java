package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.modules.pojo.grade.model.TCommonGrade;
import com.asd.modules.service.TCommonGradeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 岗位操作service实现
 * Created by zlp on 2017/1/10.
 */
@Service("tCommonGradeService")
public class TCommonGradeServiceImpl  extends BaseDaoImpl<TCommonGrade> implements TCommonGradeService{

    /**
     * 根据条件查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 岗位集合
     */
    @Override
    public List<TCommonGrade> getTCommonGradeListByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param,order);
    }

    /**
     *  保存
     * @param grade 岗位实体
     */
    @Override
    public void save(TCommonGrade grade) {
        super.save(grade);
    }

    /**
     * 根据id获取岗位
     * @param g_id 岗位id
     * @return 岗位实体
     */
    @Override
    public TCommonGrade getByPK(BigDecimal g_id) {
        return super.getById(g_id);
    }

    /**
     * 更新实体
     * @param grade 岗位实体
     */
    @Override
    public void update(TCommonGrade grade) {
        super.update(grade);
    }

    /**
     * 删除岗位表
     * 同时删除岗位权限表
     * @param pk 主键
     */
    @Override
    public void deleteGradeCascade(BigDecimal pk) {
     String hql = "delete TCommonGradeTask where gradeid = "+pk;
     super.execute(hql);
     //删除岗位表
     super.delete(pk);
    }


}
