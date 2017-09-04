package com.asd.modules.service;

import com.asd.modules.pojo.gradetask.model.TCommonGradeTask;
import com.asd.modules.pojo.task.model.TCommonTask;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 岗位资源关联操作sercice
 * Created by zlp on 2017/1/10.
 */
public interface TCommonGradeTaskService {

    /**
     * 根据条件查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 岗位资源集合
     */
    public List<TCommonGradeTask> getTCommonGradeTaskListByQueryRule(Map param, Map order);


    /**
     *  更新岗位资源
     * @param g_id 岗位id
     * @param tasks 资源集合
     */
    public void updateGradeTask(BigDecimal g_id, List<TCommonTask> tasks);
}
