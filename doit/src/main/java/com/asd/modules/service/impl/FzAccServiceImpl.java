package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fzacc.model.FzAcc;
import com.asd.modules.service.FzAccService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 账单操作impl实现
 * Created by zlp on 2017/1/16.
 */
@Service("fzAccService")
public class FzAccServiceImpl extends BaseDaoImpl<FzAcc> implements FzAccService{

    /**
     * 根据查询条件获取fzacc数量
     * @param param 参数
     * @return 数量
     */
    public long  getCountByCondition(String append ,Map<String,Object> param){
          return   super.get("select count(*) from FzAcc "+append,param);
    }

    /**
     * 分页查询
     * @param vo 查询条件
     * @param pageNo 起始页
     * @param pageSize 结束页
     * @return 分页结果
     */
    @Override
    public Pagination queryWithPages(FzAcc vo, int pageNo, int pageSize) {
        //需要对这个方法进行扩展 增加排序功能
        String hql = " from FzAcc where substr(dealflag,1,1) = '"+vo.getDealFlag()+"'  ";
        return super.findPagination(hql,pageNo,pageSize);

    }

    /**
     * 根据主键获取
     * @param accno 主键
     * @return 实体
     */
    @Override
    public FzAcc getByPK(String accno) {
        return super.getById(accno);
    }


}
