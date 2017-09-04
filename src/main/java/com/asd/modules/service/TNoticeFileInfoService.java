package com.asd.modules.service;

import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fileinfo.model.TNoticeFileInfo;

import java.util.List;
import java.util.Map;

/**
 * pdf打印相关sercice
 * Created by lenovo on 2017/1/10.
 */
public interface TNoticeFileInfoService {

    /**
     * 根据条件查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 实体
     */
    public List<TNoticeFileInfo> getByQueryRule(Map param,Map order);

    /**
     * 根据条件分页查询数据
     * @param param 查询条件
     * @param order 排序条件
     * @param pageNo 起始页
     * @param pageSize 页数
     * @return 分页数据
     */
    public Pagination getByQueryRulePages(Map param, Map order, int pageNo, int pageSize);
    /**
     * 保存信息
     * @param obj
     */
    public void save(TNoticeFileInfo obj);
}
