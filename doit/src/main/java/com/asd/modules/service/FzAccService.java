package com.asd.modules.service;

import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fzacc.model.FzAcc;

import java.util.Map;

/**
 * 账单操作service
 * Created by zlp on 2017/1/16.
 */
public interface FzAccService {

    /**
     * 根据查询条件获取fzacc数量
     * @param param 参数
     * @return 数量
     */
    public long getCountByCondition(String append ,Map<String,Object> param);

    /**
     * 根据条件查询分页结果
     * @param vo vo
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @return asd
     */
    public Pagination queryWithPages(FzAcc vo, int pageNo, int pageSize);

    /**
     * 根据主键获取
     * @param accno 主键
     * @return 实体
     */
    public FzAcc getByPK(String accno);


}
