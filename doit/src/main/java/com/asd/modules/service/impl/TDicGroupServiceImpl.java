package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.modules.pojo.dic.model.TDicGroup;
import com.asd.modules.service.TDicGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * group主表serviceImpl
 * Created by lenovo on 2017/2/9.
 */
@Service("tDicGroupService")
public class TDicGroupServiceImpl extends BaseDaoImpl<TDicGroup> implements TDicGroupService {

    /**
     * 根据条件查询item数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 结果集
     */
    public List<TDicGroup> getTDicGroupListByQueryRule(Map param, Map order){
        return super.findByPropertyOrder(param,order);
    }
}
