package com.asd.modules.service.impl;


import com.asd.common.constant.SysConst;
import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.task.model.TCommonTask;
import com.asd.modules.service.TCommonTaskService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/6.
 * for menu use
 */
@Service("tCommonTaskService")
public class TCommonTaskServiceImpl extends BaseDaoImpl<TCommonTask> implements TCommonTaskService {


    /**
     * 分页查询
     * @param vo 查询的实体
     * @param pageNo 页数
     * @param pageSize 数量
     * @return 分页结果
     */
    @Override
    public Pagination queryWithPages(TCommonTask vo, int pageNo, int pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentcode",vo.getParentcode());
        map.put("svrcode", SysConst.systemcode);

        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("lx","asc");
        return super.findPaginationOrder(map,orderMap,pageNo,pageSize);
    }

    /**
     * 根据系统编码获取所有资源
     * @param svrCode 系统编码
     * @return 资源集合
     */
    @Override
    public List<TCommonTask> getTCommonTaskListBySvrCode(String svrCode) {

        Map<String,Object> map = new HashMap<>();
        map.put("svrcode",svrCode);

        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("lx","asc");
       return super.findByPropertyOrder(map,orderMap);
    }

    /**
     * 根据父资源CODE查询父资源
     * @param taskCode 资源代码
     * @return 子资源集合
     */
    @Override
    public List<TCommonTask> getTCommonTaskListByTaskCode(String taskCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("taskcode",taskCode);
        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("lx","asc");
        return super.findByPropertyOrder(map,orderMap);
    }

    /**
     * 保存单条数据
     * @param task 资源实体
     * @throws Exception 保存异常
     */
    @Override
    public void saveTCommonTask(TCommonTask task) {
        super.save(task);
    }

    /**
     * 根据parentcode列表查询所有子菜单
     * @param idList
     * @return
     */
    public List<TCommonTask> findSubTaskByIdList(String[] idList){

        String ap = "(";
        for(String s : idList){
            ap+="'";
            ap +=s;
            ap+="',";
        }
        ap+="9999999)";
        String hql = " from TCommonTask where parentcode in "+ap;
        return super.findList(hql);
    }

    /**
     * 根据id集合删除
     * @param idList id集合
     */
    @Override
    public void deleteByIdList(String[] idList) {

        String ap = "(";
        for(String s : idList){
            ap+="'";
            ap +=s;
            ap+="',";
        }
        ap+="'9999999')";

        //同时要删除功岗位功能权限表T_COMMON_GRADETASK
        String sql = "delete FROM T_COMMON_GRADETASK where taskid in " +
                "(select task_id from T_COMMON_TASK where taskcode in"+ap +") ";
        super.executeSql(sql);

        String hql = "delete TCommonTask where taskcode in "+ap;

        super.execute(hql);

    }

    /**
     * 根据主键查询Task
     * @param task_id 资源编号
     * @return 资源实体
     * @throws Exception 123
     */
    @Override
    public TCommonTask getTCommonTaskByPK(BigDecimal task_id) throws Exception {
        return super.getById(task_id);
    }

    /**
     * 查询父资源下所有子资源
     * @param parentCode 父资源code
     * @return 子资源集合
     */
    @Override
    public List<TCommonTask> getTCommonTaskListByParentCode(String parentCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentcode",parentCode);
        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("lx","asc");
        return super.findByPropertyOrder(map,orderMap);
    }

    /**
     * 批量更新数据 数据量大的话还是不要用这个方法
     * @param tlist 要更新的list
     * @throws Exception 123
     */
    @Override
    public void updateAll(List<TCommonTask> tlist) throws Exception {
        super.updateAll(tlist);
    }

    /**
     * 根据list查询所有的资源
     * @param ins 主键的集合
     * @return 资源集合
     */
    @Override
    public List<TCommonTask> getTCommonTaskListByIns(List<String> ins) {
        String ap = "(";
        for(String s : ins){
            ap+="'";
            ap +=s;
            ap+="',";
        }
        ap+="'9999999')";
        String hql = " from TCommonTask where taskcode in "+ap;
        return super.findList(hql);
    }


}
