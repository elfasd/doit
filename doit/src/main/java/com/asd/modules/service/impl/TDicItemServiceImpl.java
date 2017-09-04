package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.modules.pojo.dic.model.TDicItem;
import com.asd.modules.service.TDicItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * group子表impl
 * Created by lenovo on 2017/2/9.
 */
@Service("tDicItemService")
public class TDicItemServiceImpl extends BaseDaoImpl<TDicItem> implements TDicItemService {

    /**
     * 根据条件查询item数据
     * @param param 查询条件
     * @param order 排序条件
     * @return 结果集
     */
    public List<TDicItem> getTDicItemListByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param, order);
    }

}