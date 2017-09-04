package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fileinfo.model.TNoticeFileInfo;
import com.asd.modules.service.TNoticeFileInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * pdf service impl
 * Created by lenovo on 2017/1/10.
 */
@Service("tNoticeFileInfoService")
public class TNoticeFileInfoServiceImpl extends BaseDaoImpl<TNoticeFileInfo> implements TNoticeFileInfoService {


    /**
     * 根据条件查询
     * @param param 查询条件
     * @param order 排序条件
     * @return notice集合
     */
    @Override
    public List<TNoticeFileInfo> getByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param,order);
    }

    /**
     * 分页查询
     * @param param 查询条件
     * @param order 排序条件
     * @param pageNo 起始页
     * @param pageSize 页数
     * @return 分页结果集
     */
    @Override
    public Pagination getByQueryRulePages(Map param, Map order, int pageNo, int pageSize) {
        return super.findPaginationOrder(param,order,pageNo,pageSize);
    }

    /**
     * 保存
     * @param obj 实体
     */
    @Override
    public void save(TNoticeFileInfo obj) {
        super.save(obj);
    }
}
