package com.asd.modules.service;

import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.user.model.TCommonUser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户操作sercice
 * Created by zlp on 2017/1/10.
 */
public interface TCommonUserService {

    /**
     * 根据条件查询用户
     * @param param 查询条件
     * @param order 排序条件
     * @return 用户集合
     */
    public List<TCommonUser> getTCommonUserListByQueryRule(Map param,Map order);


    /**
     * 根据id集合查询用户 分页
     * @param idList id集合
     * @param pageNo 起始页码
     * @param pageSize 每页数量
     * @return 分页集合
     */
    public Pagination queryWithPages(List<BigDecimal> idList, int pageNo, int pageSize);

    /**
     * 保存用户
     * @param user 用户
     */
    public void save(TCommonUser user);

    /**
     * 根据主键查询
     * @param u_id 主键
     * @return 用户实体
     */
    public TCommonUser getByPK(BigDecimal u_id);

    /**
     * 更新数据
     * @param user 用户实体
     */
    public void updateUser(TCommonUser user);

    /**
     * 删除用户
     * @param idList 用户列表
     */
    public void deleteByIdList(String[] idList);
}
