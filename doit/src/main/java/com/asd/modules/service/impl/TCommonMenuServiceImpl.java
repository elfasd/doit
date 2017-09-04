package com.asd.modules.service.impl;

import com.asd.common.hibernate.BaseDaoImpl;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.menu.model.TCommonMenu;
import com.asd.modules.service.TCommonMenuService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/1/6.
 * for menu use
 */
@Service("tCommonMenuService")
public class TCommonMenuServiceImpl extends BaseDaoImpl<TCommonMenu> implements  TCommonMenuService{

    /**
     * 查询父菜单下所有子菜单
     * @param upperID 父亲id
     * @return List<TCommonMenu> 返回菜单集合
     */
    public List<TCommonMenu> getTReinsreMenuByUpperID(BigDecimal upperID){
        Map<String,Object> map = new HashMap<> ();
        map.put("upperid",upperID);


        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("displayno","asc");

        return super.findByPropertyOrder(map,orderMap);
    }


    /**
     * 查询系统下所有菜单
     * @param systemCode 配置文件中设置的系统代码
     * @return List<TCommonMenu> 所有的菜单集合
     */
    public List<TCommonMenu> getTReinsreMenuListBySystemCode(String systemCode){
        Map<String,Object> map = new HashMap<> ();
        map.put("systemcode",systemCode);

        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("displayno","asc");

        return  super.findByPropertyOrder(map,orderMap);

    }

    /**
     * 根据主键查询菜单
     * @param m_id 主键
     * @return 菜单
     */
    @Override
    public TCommonMenu getTCommonMenuById(BigDecimal m_id) {
        return super.getById(m_id);
    }

    /**
     * 分页查询所有菜单
     * @param vo 查询的实体
     * @param pageNo 页数
     * @param pageSize 数量
     * @return 分页的菜单
     */
    @Override
    public Pagination queryWithPages(TCommonMenu vo, int pageNo, int pageSize) {
        Map<String,Object> map = new HashMap<> ();
        map.put("upperid",vo.getUpperid());
        Map<String,Object> orderMap = new HashMap<> ();
        orderMap.put("displayno","asc");
        return super.findPaginationOrder(map,orderMap,pageNo,pageSize);
    }

    /**
     * 保存菜单
     * @param menu 菜单实体
     */
    @Override
    public void saveMenu(TCommonMenu menu) {
        super.save(menu);
    }

    /**
     * 根据id列表查询所有子菜单
     * @param idList 菜单id的集合 以逗号分隔
     * @return 所有子菜单
     */
    @Override
    public List<TCommonMenu> findSubMenuByIdList(String[] idList) {

        String ap = "(";
        for(String s : idList){
            ap +=s;
        }
        ap+="9999999)";

        String hql = " from TCommonMenu where upperid in "+ap;

        return super.findList(hql);
    }

    /**
     * 根据id集合删除
     * @param idList 菜单id的集合 以逗号分隔
     */
    @Override
    public void deleteByIdList(String[] idList) {

        String ap = "(";
        for(String s : idList){
            ap +=s;
            ap+=",";
        }
        ap+="9999999)";

        String hql = "delete TCommonMenu where m_id in "+ap;
        super.execute(hql);


    }

    /**
     *  更新菜单
     * @param menu 菜单实体
     */
    @Override
    public void updateMenu(TCommonMenu menu) {

        super.update(menu);
    }


}
