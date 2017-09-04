package com.asd.modules.controller;

import com.asd.common.constant.SysConst;
import com.asd.common.datatables.DataTable;
import com.asd.common.hibernate.Pagination;
import com.asd.common.ztree.ZTreeNodeUserGrade;
import com.asd.modules.pojo.grade.model.TCommonGrade;
import com.asd.modules.pojo.user.model.TCommonUser;
import com.asd.modules.pojo.user.vo.TCommonUserVo;
import com.asd.modules.pojo.usergrade.model.TCommonUserGrade;
import com.asd.modules.service.TCommonGradeService;
import com.asd.modules.service.TCommonUserGradeService;
import com.asd.modules.service.TCommonUserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 用户操作controller
 * Created by zlp on 2017/1/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**  岗位表service注入	 */
    @Autowired
    private TCommonGradeService tCommonGradeService;
    /**  用户表service注入	 */
    @Autowired
    private TCommonUserService tCommonUserService;
    /**  用户岗位表service注入	 */
    @Autowired
    private TCommonUserGradeService tCommonUserGradeService;

    /**
     * 菜单跳转
     * @return 跳转路径
     */
    @RequestMapping(value="userManage")
    public String taskManage(){
        return "user/userManage";
    }

    /**
     *  获取菜单的json格式传送给前台
     */
    @RequestMapping(value="getGradeUserJson")
    @ResponseBody
    public List<ZTreeNodeUserGrade> getGradeUserJson(){
        try {
            //查询出所有的岗位
            List<TCommonGrade> gradeList = tCommonGradeService.getTCommonGradeListByQueryRule(new HashMap(),new HashMap());
            //查询出所有用户岗位关系
            List<TCommonUserGrade> userGradeList = tCommonUserGradeService.getTCommonUserGradeListByQueryRule(new HashMap(),new HashMap());
            //查询出所有用户
            List<TCommonUser> userList = tCommonUserService.getTCommonUserListByQueryRule(new HashMap(),new HashMap());
            Map<BigDecimal,String> userMap = new HashMap<BigDecimal,String>();
            for(TCommonUser user : userList){
                userMap.put(user.getU_id(), user.getUsername());
            }


            //组装岗位用户关系
            Map<BigDecimal,List<BigDecimal>> userGradeRel = new HashMap<BigDecimal,List<BigDecimal>>();
            for(TCommonUserGrade usergrade : userGradeList){
                List<BigDecimal> temp ;
                if(userGradeRel.get(usergrade.getGradeid())!=null){
                    temp =  userGradeRel.get(usergrade.getGradeid());
                }else{
                    temp = new ArrayList<BigDecimal>();
                }

                temp.add(usergrade.getU_id());
                userGradeRel.put(usergrade.getGradeid(),temp );
            }

            List<ZTreeNodeUserGrade> nodes = new ArrayList<ZTreeNodeUserGrade>();
            ZTreeNodeUserGrade root = new ZTreeNodeUserGrade(BigDecimal.ZERO,new BigDecimal(999999), SysConst.systemCName,true,true,1);
            nodes.add(root);
            for(TCommonGrade grade : gradeList){
                ZTreeNodeUserGrade node = new ZTreeNodeUserGrade(grade.getG_id(),BigDecimal.ZERO,grade.getGradecname(),false,false,2);
                nodes.add(node);
                List<BigDecimal> sub = userGradeRel.get(grade.getG_id());
                if(sub!=null){
                    for(BigDecimal b : sub){
                        ZTreeNodeUserGrade subnode = new ZTreeNodeUserGrade(b,grade.getG_id(),userMap.get(b),false,false,3);
                        nodes.add(subnode);
                    }
                }

            }

            return  nodes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 查询所有资源
     */
    @RequestMapping(value="queryUser")
    @ResponseBody
    public DataTable queryUser(@RequestParam(value="draw") String draw,
                               @RequestParam(value="start") int start,
                               @RequestParam(value="length") int length,
                               @RequestParam(value="gid") String gid){

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

        DataTable table = new DataTable();
        if(gid!=null && !"".equals(gid)){
            //查询岗位下的用户ID
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("gradeid", new BigDecimal(gid));
            Map<String,Object> order = new HashMap<String,Object>();
            List<TCommonUserGrade> ugList = tCommonUserGradeService.getTCommonUserGradeListByQueryRule(param,order);
            List<BigDecimal> userIdList = new ArrayList<BigDecimal>();
            for(TCommonUserGrade ug : ugList){
                userIdList.add(ug.getU_id());
            }

            Map<String,Object> userRule = new HashMap<String,Object>();
            if(userIdList.size()==0){
                userIdList.add(new BigDecimal(999999));
            }
            Pagination page = tCommonUserService.queryWithPages(userIdList, pageNo, pageSize);
            table.setDraw(draw);
            table.setRecordsTotal(String.valueOf(page.getPagesCount()));
            table.setRecordsFiltered(String.valueOf(page.getPagesCount()));
            List voList = new ArrayList();
            List<TCommonUser> userList =  page.getItems();
            for(TCommonUser user : userList){
                TCommonUserVo vo = new TCommonUserVo();
                vo.setUsercode(user.getUsercode());
                vo.setUsername(user.getUsername());
                vo.setU_id(user.getU_id());
                voList.add(vo);
            }
            table.setData(voList);
        }else{
            //返回一个空datatable
            table.setDraw(draw);
            table.setRecordsTotal(String.valueOf(0));
            table.setRecordsFiltered(String.valueOf(0));
            table.setData(new ArrayList<TCommonUser>());
        }

        return table;

    }

    /**
     * 增加用户
     */
    @RequestMapping(value="addUser")
    @ResponseBody
    public String addUser(TCommonUserVo userAddVo){

        //back参数 0:保存成功 1:功能代码重复
        String back = "0";
        //首先查出父菜单
        BigDecimal gid= userAddVo.getGradeid();
        //用户代码不能重复

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("usercode", userAddVo.getUsercode());
        Map<String,Object> order = new HashMap<String,Object>();
        List<TCommonUser> oldList = tCommonUserService.getTCommonUserListByQueryRule(param,order);
        if(oldList!=null && oldList.size()>0){
            //功能代码重复
            back = "1";
        }else{

            //这里的两个保存应该放到 **ServiceImpl中 保证事物 这里偷懒了
            TCommonUser newUser = new TCommonUser();
            newUser.setUsercode(userAddVo.getUsercode());
            newUser.setUsername(userAddVo.getUsername());
            //这里采用MD5摘要存储 不加盐
            try {
                newUser.setUserpwd(new Md5Hash(userAddVo.getUserpwd()).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            tCommonUserService.save(newUser);

            param.clear();
            param.put("usercode",userAddVo.getUsercode());
            param.put("username",userAddVo.getUsername());

            List<TCommonUser> newList = tCommonUserService.getTCommonUserListByQueryRule(param,order);
            BigDecimal u_id = newList.get(0).getU_id();
            TCommonUserGrade newUserGrade = new TCommonUserGrade();
            newUserGrade.setGradeid(gid);
            newUserGrade.setU_id(u_id);
            //同时生成用户表 与 用户岗位对应表
            tCommonUserGradeService.save(newUserGrade);
        }
        return back;

    }

    /**
     * 根据id查询用户
     * @param u_id 主键
     * @return datatable用json
     */
    @RequestMapping(value="getSelectedUser")
    @ResponseBody
    public TCommonUser getSelectedUser(@RequestParam(value="u_id") String u_id){
        TCommonUser oldUser = tCommonUserService.getByPK(new BigDecimal(u_id));
        return oldUser;
    }

    /**
     * 修改用户
     */
    @RequestMapping(value="updateUser")
    @ResponseBody
    public String updateUser(TCommonUser userUpdateVo){
        //获取前台传送的主键
        BigDecimal pk = userUpdateVo.getU_id();
        //反馈标志 0:修改成功 1:功能代码重复
        String back = "0";
        //需要更新的数据
        List<TCommonUser> toUpdate = new ArrayList<TCommonUser>();
        //查询出修改前的数据 并记录修改前的taskcode
        TCommonUser user = null;
        try {
            user = tCommonUserService.getByPK(pk);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String oldusercode = user.getUsercode();

        //根据修改后的taskcode查询数据
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("usercode", userUpdateVo.getUsercode());
        Map<String,Object> order = new HashMap<String,Object>();
        List<TCommonUser> newList = tCommonUserService.getTCommonUserListByQueryRule(param,order);
        if(newList!=null && newList.size()>0){
            //如果有数据并且与原始的gradecname相同 则不更新
            if(oldusercode.equals(userUpdateVo.getUsercode())){
                user.setUsername(userUpdateVo.getUsername());
                try {
                    user.setUserpwd(new Md5Hash(userUpdateVo.getUserpwd()).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tCommonUserService.updateUser(user);
            }else{
                //如果有数据 并且与原来的gradecname不同 说明功能代码重复
                back = "1";
            }
        }else{
            user.setUsercode(userUpdateVo.getUsercode());
            user.setUsername(userUpdateVo.getUsername());

            try {
                user.setUserpwd(new Md5Hash(userUpdateVo.getUserpwd()).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            tCommonUserService.updateUser(user);
        }

        return back;

    }


    /**
     * 删除用户
     * @param idList 用户id列表
     */
    @RequestMapping(value="deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(value="idList") String idList){

        String idStrings[] = idList.split(",");
        tCommonUserService.deleteByIdList(idStrings);
        return "";
    }


    /**
     *  清除缓存
     */
    @RequestMapping(value="clearCache")
    @ResponseBody
    public String clearCache(){
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();

        AuthorizingRealm userRealm = (AuthorizingRealm)securityManager.getRealms().iterator().next();

        userRealm.getAuthorizationCache().clear();
        return "";
    }

}
