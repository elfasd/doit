package com.asd.modules.controller;

import com.asd.common.constant.SysConst;
import com.asd.common.datatables.DataTable;
import com.asd.common.hibernate.Pagination;
import com.asd.common.ztree.ZTreeNode;
import com.asd.modules.pojo.menu.model.TCommonMenu;
import com.asd.modules.pojo.menu.vo.MenuFirst;
import com.asd.modules.pojo.menu.vo.MenuParams;
import com.asd.modules.pojo.menu.vo.MenuSecond;
import com.asd.modules.pojo.menu.vo.TCommonMenuVo;
import com.asd.modules.service.TCommonMenuService;
import com.sinosoft.bpsdriver.domain.getMenuBySvr.MenuResInfo;
import com.sinosoft.bpsdriver.service.facade.SaaAPIService;
import com.sinosoft.bpsdriver.service.facade.UserMgrAPIService;
import com.sinosoft.bpsdriver.service.spring.SaaAPIServiceImpl;
import com.sinosoft.bpsdriver.service.spring.UserMgrAPIServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.util.*;

/**
 * Created by lenovo on 2017/1/6.
 * for menu use
 */
@Controller
@RequestMapping("/menu")
public class MenuController {


    /**
     * 菜单表service注入
     */
    @Autowired
    private TCommonMenuService tCommonMenuService;
    private List<MenuResInfo> menuResInfos = null;
    private SaaAPIService saa = new SaaAPIServiceImpl();
    private UserMgrAPIService userAPIService = new UserMgrAPIServiceImpl();



    @RequestMapping(value = "initMenu", method = RequestMethod.POST, produces = {"text/json;charset=UTF-8"})
    @ResponseBody
    public String initMenu() {
        String back = "";
       /* Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();

        //组装前台需要的字符串

        try {
            menuResInfos  =  userAPIService.getMenuByUser("reins", username,"1");
            List<TCommonMenu> allMenuList = new ArrayList<TCommonMenu>();
            List<TCommonMenu> firstMenuList =new ArrayList<TCommonMenu>();
            for(MenuResInfo m : menuResInfos){
                TCommonMenu fmenu = new TCommonMenu();
                fmenu.setM_id(new BigDecimal(m.getID()));
                fmenu.setMenucname(m.getMENUCNAME());
                fmenu.setActionurl(m.getACTIONURL());
                fmenu.setUpperid(new BigDecimal(m.getUPPERID()));
                fmenu.setDisplayno(new BigDecimal(m.getDISPLAYNO()));
                allMenuList.add(fmenu);
            }
            //获取所有的一级菜单
            for (TCommonMenu menu : allMenuList) {
                if (BigDecimal.ZERO.equals(menu.getUpperid())) {
                    firstMenuList.add(menu);
                }
            }
            Map<BigDecimal, List<TCommonMenu>> firstMenuMap = new HashMap<BigDecimal, List<TCommonMenu>>();
            for (TCommonMenu fmenu : firstMenuList) {
                firstMenuMap.put(fmenu.getM_id(), new ArrayList<TCommonMenu>());
            }
            for (TCommonMenu amenu : allMenuList) {
                List<TCommonMenu> tlist = firstMenuMap.get(amenu.getUpperid());
                if (tlist != null) {
                    tlist.add(amenu);
                }
            }
            for (TCommonMenu smenu : firstMenuList) {
                MenuParams m1 = new MenuParams();
                m1.setHref("#");
                m1.setIcon("fa fa-files-o");
                m1.setName(smenu.getMenucname());
                m1.setMenu_id(smenu.getM_id().toString());

                List<TCommonMenu> m11List = firstMenuMap.get(smenu.getM_id());
                List<MenuParams> lm1 = new ArrayList<MenuParams>();
                if (m11List != null) {
                    for (TCommonMenu tm : m11List) {
                        MenuParams m11 = new MenuParams();
                        m11.setName(tm.getMenucname());
                        m11.setIcon("fa fa-circle-o");
                        m11.setHref(tm.getActionurl());
                        m11.setMenu_id(tm.getM_id().toString());
                        lm1.add(m11);
                    }

                }
                MenuSecond f11 = new MenuSecond(lm                MenuFirst f1 = new MenuFirst(m1);
1);
                f1.setSecondMenu(f11);
                back += f1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


        //权限控制
        Subject subject = SecurityUtils.getSubject();
        //根据系统编号查询所有账单 这里应该根据权限来判断
        //查询出所有的菜单
        List<TCommonMenu> menuList = tCommonMenuService.getTReinsreMenuListBySystemCode(SysConst.systemcode);
        //查询出所有一级菜单
        List<TCommonMenu> firstMenuList = tCommonMenuService.getTReinsreMenuByUpperID(new BigDecimal(0));
        //这里偷懒了就在源数据进行摘除了
        List<TCommonMenu> menuListTemp = new ArrayList<TCommonMenu>();
        Iterator<TCommonMenu> ite = menuList.iterator();
        while (ite.hasNext()) {
            TCommonMenu all = ite.next();
            if (subject.isPermitted(all.getTaskcode())) {
                menuListTemp.add(all);
            }

        }
        menuList = menuListTemp;

        List<TCommonMenu> firstMenuListTemp = new ArrayList<TCommonMenu>();
        Iterator<TCommonMenu> ite1 = firstMenuList.iterator();
        while (ite1.hasNext()) {
            TCommonMenu first = ite1.next();
            if (subject.isPermitted(first.getTaskcode())) {
                //处理
                firstMenuListTemp.add(first);
            }

        }
        firstMenuList = firstMenuListTemp;

        Map<BigDecimal, List<TCommonMenu>> firstMenuMap = new HashMap<BigDecimal, List<TCommonMenu>>();
        for (TCommonMenu fmenu : firstMenuList) {
            firstMenuMap.put(fmenu.getM_id(), new ArrayList<TCommonMenu>());
        }
        for (TCommonMenu amenu : menuList) {
            List<TCommonMenu> tlist = firstMenuMap.get(amenu.getUpperid());
            if (tlist != null) {
                tlist.add(amenu);
            }
        }


        for (TCommonMenu smenu : firstMenuList) {
            MenuParams m1 = new MenuParams();
            m1.setHref("#");
            m1.setIcon("fa fa-files-o");
            m1.setName(smenu.getMenucname());
            m1.setMenu_id(smenu.getM_id().toString());

            List<TCommonMenu> m11List = firstMenuMap.get(smenu.getM_id());
            List<MenuParams> lm1 = new ArrayList<MenuParams>();
            if (m11List != null) {
                for (TCommonMenu tm : m11List) {
                    MenuParams m11 = new MenuParams();
                    m11.setName(tm.getMenucname());
                    m11.setIcon("fa fa-circle-o");
                    m11.setHref(tm.getActionurl());
                    m11.setMenu_id(tm.getM_id().toString());
                    lm1.add(m11);
                }

            }
            MenuFirst f1 = new MenuFirst(m1);
            MenuSecond f11 = new MenuSecond(lm1);
            f1.setSecondMenu(f11);
            back += f1.toString();
        }
        return back;

    }


    /**
     * 点击菜单后跳转
     *
     * @return 跳转到具体界面
     */
    @RequestMapping(value = "menuManage")
    public String menuManage() {
        return "menu/menuManage";
    }

    /**
     * 满足ztree格式的json
     * @return 满足ztree格式的json
     */
    @RequestMapping(value = "getMenuJson", method = RequestMethod.POST)
    @ResponseBody
    public List<ZTreeNode> getMenuJson() {

        List<TCommonMenu> menuList = tCommonMenuService.getTReinsreMenuListBySystemCode(SysConst.systemcode);

        List<ZTreeNode> nodes = new ArrayList<>();
        for (TCommonMenu menu : menuList) {
            ZTreeNode node = new ZTreeNode(menu.getM_id().intValue(), menu.getUpperid().intValue(), menu.getMenucname(), false, true, menu.getMenulevel().intValue());
            nodes.add(node);
        }
        return nodes;
    }


    /**
     * 根据主键查询菜单
     * @param pk 菜单主键
     * @return 菜单
     */
    @RequestMapping(value="getSelectedMenu")
    @ResponseBody
    public TCommonMenu getSelectedMenu(@RequestParam(value="pk") String pk){
        TCommonMenu  menu = tCommonMenuService.getTCommonMenuById(new BigDecimal(pk));
        return menu;
    }

    @RequestMapping(value="queryMenu")
    @ResponseBody
    public DataTable queryMenu(@RequestParam(value="draw") String draw,@RequestParam(value="start") int start,@RequestParam(value="length") int length,
                            @RequestParam(value="menuId") String menuId){

        int pageNo,pageSize=0;

        //获取分页信息
        pageNo = start / length + 1;
        if (pageNo == 0) {
            pageNo = 1;
        }
        pageSize = length;
        if (pageSize == 0) {
            pageSize = 20;
        }

        TCommonMenu t = new TCommonMenu();
        t.setUpperid(new BigDecimal(menuId));

        Pagination page = tCommonMenuService.queryWithPages(t,pageNo,pageSize);

        DataTable table = new DataTable();
        table.setDraw(draw);
        table.setRecordsTotal(String.valueOf(page.getPagesCount()));
        table.setRecordsFiltered(String.valueOf(page.getPagesCount()));
        List voList = new ArrayList();

        List<TCommonMenu> accList =  page.getItems();

        for(TCommonMenu menu : accList){

            TCommonMenuVo vo = new TCommonMenuVo();
            vo.setM_id(menu.getM_id());
            vo.setTaskcode(menu.getTaskcode());
            vo.setUpperid(menu.getUpperid());
            vo.setMenucname(menu.getMenucname());
            vo.setActionurl(menu.getActionurl());
            vo.setMenuename(menu.getMenuename());
            vo.setValidind(menu.getValidind());
            vo.setDisplayno(menu.getDisplayno());
            voList.add(vo);
        }
        table.setData(voList);

     return table;

    }

    /**
     *  删除菜单
     * @param menuAddVo 删除TO_DO
     */
    @RequestMapping(value="addMenu")
    @ResponseBody
    public String addMenu(TCommonMenu menuAddVo){
        //首先查出父菜单
        BigDecimal pId = menuAddVo.getUpperid();
        TCommonMenu fmenu = tCommonMenuService.getTCommonMenuById(pId);
        if(fmenu!=null){
            try{
                //后台自动封装menuAddVo
                TCommonMenu newMenu = new TCommonMenu();
                newMenu.setUpperid(menuAddVo.getUpperid());
                newMenu.setActionurl(menuAddVo.getActionurl());
                if(menuAddVo.getDisplayno()==null||"".equals(menuAddVo.getDisplayno())){
                    newMenu.setDisplayno(new BigDecimal(0));
                }else{
                    newMenu.setDisplayno(menuAddVo.getDisplayno());
                }
                newMenu.setMenucname(menuAddVo.getMenucname());
                newMenu.setMenuename(menuAddVo.getMenuename());
                newMenu.setTaskcode(menuAddVo.getTaskcode());
                newMenu.setSystemcode(SysConst.systemcode);
                newMenu.setValidind("1");
                //级别+1
                newMenu.setMenulevel(fmenu.getMenulevel().add(new BigDecimal(1)));
                tCommonMenuService.saveMenu(newMenu);

                return "SUCCESS";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "ERROR";
    }


    @RequestMapping("deleteMenu")
    @ResponseBody
    public Boolean deleteMenu(@RequestParam(value="idList") String idList){

        Boolean hasSubNode = false;

        //获取父菜单的id
        String[] idListSP = idList.split(",");
        //首先判断有没有子菜单
        List<TCommonMenu> subList = tCommonMenuService.findSubMenuByIdList(idListSP);
        if(subList!=null && subList.size()>0){
            hasSubNode = true;
        }else{
            tCommonMenuService.deleteByIdList(idListSP);
        }

        return hasSubNode;

    }

    @RequestMapping("updateMenu")
    @ResponseBody
    public Boolean updateMenu(TCommonMenu menuUpdateVo){
        boolean updateOk=true;
        try{
            BigDecimal pk = menuUpdateVo.getM_id();
            TCommonMenu menu = tCommonMenuService.getTCommonMenuById(pk);
            menu.setActionurl(menuUpdateVo.getActionurl());
            menu.setMenucname(menuUpdateVo.getMenucname());
            menu.setMenuename(menuUpdateVo.getMenuename());
            //menu.setDisplayno(menuUpdateVo.getDisplayno());
            if(menuUpdateVo.getDisplayno()==null||"".equals(menuUpdateVo.getDisplayno())){
                menu.setDisplayno(new BigDecimal(0));
            }else{
                menu.setDisplayno(menuUpdateVo.getDisplayno());
            }
            menu.setTaskcode(menuUpdateVo.getTaskcode());
            tCommonMenuService.updateMenu(menu);
        }catch(Exception e){
            updateOk = false;
            e.printStackTrace();
        }

        return updateOk;
    }


}
