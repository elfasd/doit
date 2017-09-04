package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.modules.pojo.gradetask.model.TCommonGradeTask;
import com.asd.modules.pojo.task.model.TCommonTask;
import com.asd.modules.service.TCommonGradeTaskService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 岗位资源操作service实现
 * Created by zlp on 2017/1/10.
 */
@Service("tCommonGradeTaskService")
public class TCommonGradeTaskServiceImpl extends BaseDaoImpl<TCommonGradeTask> implements TCommonGradeTaskService {

    /**
     * 根据条件查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 岗位资源集合
     */
    @Override
    public List<TCommonGradeTask> getTCommonGradeTaskListByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param,order);
    }

    /**
     *  更新岗位资源
     * @param g_id 岗位id
     * @param tasks 资源集合
     */
    @Override
    public void updateGradeTask(BigDecimal g_id, List<TCommonTask> tasks) {
        String hql = "delete TCommonGradeTask where gradeid="+g_id;
        super.execute(hql);
        //保证删除先执行成功 不使用hibernate缓存
        this.getSession().flush();
        //然后再插入
        List<TCommonGradeTask> toSave = new ArrayList<TCommonGradeTask>();
        for(TCommonTask task :tasks){
            TCommonGradeTask gt = new TCommonGradeTask();
            gt.setGradeid(g_id);
            gt.setTaskid(task.getTask_id());
            toSave.add(gt);
        }
        super.saveAll(toSave);
    }
}
