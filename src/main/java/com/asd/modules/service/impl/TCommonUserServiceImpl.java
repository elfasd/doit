package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.user.model.TCommonUser;
import com.asd.modules.service.TCommonUserService;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户操作service实现
 * Created by zlp on 2017/1/10.
 */
@Service("tCommonUserService")
public class TCommonUserServiceImpl extends BaseDaoImpl<TCommonUser> implements TCommonUserService {

    /**
     * 根据条件查询用户
     * @param param 查询条件
     * @param order 排序条件
     * @return 用户集合
     */
    @Override
    public List<TCommonUser> getTCommonUserListByQueryRule(Map param, Map order) {
        return super.findByPropertyOrder(param,order);
    }

    /**
     * 根据id集合查询用户 分页
     * @param idList id集合
     * @param pageNo 起始页码
     * @param pageSize 每页数量
     * @return 分页集合
     */
    @Override
    public Pagination queryWithPages(List<BigDecimal> idList, int pageNo, int pageSize) {
        String ap = "(";
        for(BigDecimal s : idList){
            ap +=s;
            ap +=",";
        }
        ap+="9999999)";
        String hql = " from TCommonUser where u_id in "+ap;
        return super.findPagination(hql,pageNo,pageSize);

    }

    /**
     * 保存用户
     * @param user 用户
     */
    @Override
    public void save(TCommonUser user) {
        super.save(user);
    }

    /**
     * 根据主键查询
     * @param u_id 主键
     * @return 用户实体
     */
    @Override
    public TCommonUser getByPK(BigDecimal u_id) {
        return super.getById(u_id);
    }

    /**
     * 更新数据
     * @param user 用户实体
     */
    @Override
    public void updateUser(TCommonUser user) {
        super.update(user);
    }

    /**
     * 删除用户
     * @param idList 用户列表
     */
    @Override
    public void deleteByIdList(String[] idList) {
        String ap = "(";
        for(String s : idList){
            ap +=s;
            ap+=",";
        }
        ap+="9999999)";

        //同时要删除功岗位功能权限表T_COMMON_GRADETASK
        String hql1 = "delete  TCommonUser where u_id in"+ap;
        super.execute(hql1);
        String hql = "delete TCommonUserGrade where u_id in "+ap;
        super.execute(hql);

    }
}
