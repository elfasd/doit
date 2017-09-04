package com.asd.modules.service;

import com.asd.modules.pojo.grade.model.TCommonGrade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 岗位操作sercice
 * Created by zlp on 2017/1/10.
 */
public interface TCommonGradeService {

    /**
     * 根据条件查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 岗位集合
     */
    public List<TCommonGrade> getTCommonGradeListByQueryRule(Map param, Map order);

    /**
     *  保存
     * @param grade 岗位实体
     */
    public void save(TCommonGrade grade);

    /**
     * 根据id获取岗位
     * @param g_id 岗位id
     * @return 岗位实体
     */
    public TCommonGrade getByPK(BigDecimal g_id);

    /**
     * 更新实体
     * @param grade 岗位实体
     */
    public void update(TCommonGrade grade);

    /**
     * 删除岗位表
     * 同时删除岗位权限表
     * @param pk 主键
     */
    public void deleteGradeCascade(BigDecimal pk);

}
