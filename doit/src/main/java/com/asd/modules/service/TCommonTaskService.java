package com.asd.modules.service;

import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.task.model.TCommonTask;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资源管理service
 * Created by zlp on 2017/1/9.
 */
public interface TCommonTaskService {

    /**
     * 分页查询所有菜单
     * @param vo 查询的实体
     * @param pageNo 页数
     * @param pageSize 数量
     * @return 分页的菜单
     */
    public Pagination queryWithPages(TCommonTask vo, int pageNo, int pageSize);

    /**
     * 根据系统编码获取所有资源
     * @param svrCode 系统编码
     * @return 资源集合
     */
    public List<TCommonTask> getTCommonTaskListBySvrCode(String svrCode) ;

    /**
     * 根据父资源CODE查询父资源
     * @param taskCode 资源代码
     * @return 子资源集合
     */
    public List<TCommonTask> getTCommonTaskListByTaskCode(String taskCode);


    /**
     * 保存单条数据
     * @param task 资源实体
     * @throws Exception 保存异常
     */
    public void saveTCommonTask(TCommonTask task);

    /**
     * 根据parentcode列表查询所有子菜单
     * @param idList
     * @return
     */
    public List<TCommonTask> findSubTaskByIdList(String[] idList);

    /**
     * 根据id集合删除
     * @param idList id集合
     */
    public void deleteByIdList(String[] idList);

    /**
     * 根据主键查询Task
     * @param task_id 资源编号
     * @return 资源实体
     * @throws Exception 123
     */
    public TCommonTask getTCommonTaskByPK(BigDecimal task_id) throws Exception;

    /**
     * 查询父资源下所有子资源
     * @param parentCode 父资源code
     * @return 子资源集合
     */
    public List<TCommonTask> getTCommonTaskListByParentCode(String parentCode) ;

    /**
     * 批量更新数据 数据量大的话还是不要用这个方法
     * @param tlist 要更新的list
     * @throws Exception 123
     */
    public void  updateAll(List<TCommonTask> tlist) throws Exception;

    /**
     * 根据list查询所有的资源
     * @param ins 主键的集合
     * @return 资源集合
     */
    public List<TCommonTask> getTCommonTaskListByIns(List<String> ins);

}
