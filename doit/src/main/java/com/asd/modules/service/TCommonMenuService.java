package com.asd.modules.service;

import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.menu.model.TCommonMenu;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhangluping on 2017/1/6.
 * for menu use
 */
public interface TCommonMenuService {


    /**
     * 查询父菜单下所有子菜单
     * @param upperID 父亲id
     * @return List<TCommonMenu> 返回菜单集合
     */
    public List<TCommonMenu> getTReinsreMenuByUpperID(BigDecimal upperID);

    /**
     * 查询系统下所有菜单
     * @param systemCode 配置文件中设置的系统代码
     * @return List<TCommonMenu> 所有的菜单集合
     */
    public List<TCommonMenu> getTReinsreMenuListBySystemCode(String systemCode);

    /**
     * 根据主键查询菜单
     * @param m_id 主键
     * @return 菜单
     */
    public TCommonMenu getTCommonMenuById(BigDecimal m_id);

    /**
     * 分页查询所有菜单
     * @param vo 查询的实体
     * @param pageNo 页数
     * @param pageSize 数量
     * @return 分页的菜单
     */
    public Pagination queryWithPages(TCommonMenu vo, int pageNo, int pageSize);

    /**
     * 保存菜单
     * @param menu 菜单实体
     */
    public void saveMenu(TCommonMenu menu);

    /**
     * 根据id列表查询所有子菜单
     * @param idList 菜单id的集合 以逗号分隔
     * @return 所有子菜单
     */
    public List<TCommonMenu> findSubMenuByIdList(String[] idList);

    /**
     *  根据id集合删除
     * @param idList 菜单id的集合 以逗号分隔
     */
    public void deleteByIdList(String[] idList);


    /**
     * 修改菜单
     * @param menu
     */
    public void updateMenu(TCommonMenu menu);
}
