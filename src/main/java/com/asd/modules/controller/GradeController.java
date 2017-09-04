package com.asd.modules.controller;

import com.asd.common.constant.SysConst;
import com.asd.common.ztree.ZTreeNodeGrade;
import com.asd.common.ztree.ZTreeNodeTask;
import com.asd.modules.pojo.grade.model.TCommonGrade;
import com.asd.modules.pojo.gradetask.model.TCommonGradeTask;
import com.asd.modules.pojo.task.model.TCommonTask;
import com.asd.modules.pojo.usergrade.model.TCommonUserGrade;
import com.asd.modules.service.*;
import org.apache.commons.collections.map.HashedMap;
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

/**
 * 岗位controller
 * Created by zlp on 2017/1/10.
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    /**  岗位表service注入	 */
    @Autowired
    private TCommonGradeService tCommonGradeService;
    /** 资源service注入 */
    @Autowired
    private TCommonTaskService tCommonTaskService;
    /** 用户	service注入 */
    @Autowired
    private TCommonUserService tCommonUserService;
    /** 用户岗位关联	service注入 */
    @Autowired
    private TCommonUserGradeService tCommonUserGradeService;
    /**  岗位权限表service注入	 */
    @Autowired
    private TCommonGradeTaskService tCommonGradeTaskService;

    /**
     * 菜单跳转
     * @return 跳转的路径
     */
    @RequestMapping(value="gradeManage")
    public String gradeManage(){
        return "grade/gradeManage";
    }


    /**
     *  获取菜单的json格式传送给前台
     */
    @RequestMapping(value="getGradeJson")
    @ResponseBody
    public List<ZTreeNodeGrade> getGradeJson(){
        try {
            //查询出所有的岗位
            List<TCommonGrade> gradeList = tCommonGradeService.getTCommonGradeListByQueryRule(new HashMap(),new HashMap());

            //岗位树暂时只支持2级
            List<ZTreeNodeGrade> nodes = new ArrayList<ZTreeNodeGrade>();
//			ZTreeNodeGrade root = new ZTreeNodeGrade(new BigDecimal(123),9999,SysConst.systemCName,true,true,0);
//			nodes.add(root);
            for(TCommonGrade grade : gradeList){
                ZTreeNodeGrade node = new ZTreeNodeGrade(grade.getG_id(),0,grade.getGradecname(),false,true,1);
                nodes.add(node);
            }
            return nodes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增加岗位
     */
    @RequestMapping(value="addGrade")
    @ResponseBody
    public String addGrade(TCommonGrade gradeAddVo){

        //back参数 0:保存成功 1:功能代码重复
        String back = "0";
        //岗位名称不能重复
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("gradecname",gradeAddVo.getGradecname());
        Map<String,Object> order = new HashMap<String,Object>();

        List<TCommonGrade> oldList = tCommonGradeService.getTCommonGradeListByQueryRule(param,order);
        if(oldList!=null && oldList.size()>0){
            //功能代码重复
            back = "1";
        }else{
            TCommonGrade grade = new TCommonGrade();
            grade.setGradecname(gradeAddVo.getGradecname());

            try{
                tCommonGradeService.save(grade);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return back;

    }

    /**
     * 点击岗位树节点后显示datatable
     * @param g_id 岗位id
     * @return 岗位的json串
     */
    @RequestMapping(value="getSelectedGrade")
    @ResponseBody
    public TCommonGrade getSelectedGrade(@RequestParam(value="g_id") String g_id){
        TCommonGrade  grade = tCommonGradeService.getByPK(new BigDecimal(g_id));
        return  grade;
    }

    /**
     *  更新岗位
     * @param gradeUpdateVo 岗位实体
     * @return 前台json
     */
    @RequestMapping(value="updateGrade")
    @ResponseBody
    public String updateGrade(TCommonGrade gradeUpdateVo){
        //获取前台传送的主键
        BigDecimal pk = gradeUpdateVo.getG_id();
        //反馈标志 0:修改成功 1:功能代码重复
        String back = "0";
        //需要更新的数据
        List<TCommonGrade> toUpdate = new ArrayList<TCommonGrade>();
        //查询出修改前的数据 并记录修改前的taskcode
        TCommonGrade grade = null;
        try {
            grade = tCommonGradeService.getByPK(pk);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String oldGradeName = grade.getGradecname();

        //根据修改后的taskcode查询数据
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("gradecname",gradeUpdateVo.getGradecname());
        Map<String,Object> order = new HashMap<String,Object>();
        List<TCommonGrade> newList = tCommonGradeService.getTCommonGradeListByQueryRule(param,order);

        if(newList!=null && newList.size()>0){
            //如果有数据并且与原始的gradecname相同 则不更新
            if(oldGradeName.equals(gradeUpdateVo.getGradecname())){
            }else{
                //如果有数据 并且与原来的gradecname不同 说明功能代码重复
                back = "1";
            }
        }else{
            grade.setGradecname(gradeUpdateVo.getGradecname());
            tCommonGradeService.update(grade);
        }
        return back;
    }

    /**
     * 获取岗位资源的json集合
     * @param g_id 岗位id
     * @param gradeUpdateVo 岗位实体
     * @return json
     */
    @RequestMapping(value="getGradeTaskJson")
    @ResponseBody
    public List<ZTreeNodeTask> getGradeTaskJson(@RequestParam(value="g_id") String g_id,TCommonGrade gradeUpdateVo){

        //获取父菜单的id
        //首先获取选中岗位所拥有的所有资源权限
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("gradeid",new BigDecimal(g_id));
        Map<String,Object> order = new HashMap<String,Object>();
        Map<BigDecimal,BigDecimal> roleMap = new HashMap<BigDecimal,BigDecimal>();
        List<TCommonGradeTask> gtList = tCommonGradeTaskService.getTCommonGradeTaskListByQueryRule(param,order);
        for(TCommonGradeTask gt : gtList){
            roleMap.put(gt.getTaskid(),gt.getGradeid());
        }

        List<TCommonTask> allTaskList = tCommonTaskService.getTCommonTaskListBySvrCode(SysConst.systemcode);
        List<ZTreeNodeTask> nodes = new ArrayList<ZTreeNodeTask>();
        for(TCommonTask task : allTaskList){
            boolean isopen =true;
            boolean ischecked = false;
            if(roleMap.get(task.getTask_id())!=null){
                ischecked = true;
            }
            //只展开1级菜单 后续决定是否展开二级菜单
            if(task.getLx().compareTo(new BigDecimal(0))>0){
                isopen=false;
            }
            ZTreeNodeTask node = new ZTreeNodeTask(task.getTaskcode(),task.getParentcode(),task.getTaskcname(),ischecked,isopen,task.getLx().intValue());
            nodes.add(node);
        }
        return nodes;

    }

    /**
     * 绑定岗位与资源
     */
    @RequestMapping(value="saveGradeTaskBind")
    @ResponseBody
    public String saveGradeTaskBind(@RequestParam(value="g_id") String g_id,@RequestParam(value="taskCodes") String taskCodes
                                    ) throws Exception{

        String[] codeStrings = taskCodes.split(",");
        List<String> codeList = new ArrayList<String>();
        for(String s:codeStrings){
            codeList.add(s);
        }

        //根据code获取id
        List<TCommonTask> taskList = tCommonTaskService.getTCommonTaskListByIns(codeList);
        tCommonGradeTaskService.updateGradeTask(new BigDecimal(g_id), taskList);

        return "";
    }

    /**
     * 删除岗位
     * 删除岗位的同时 应该删除岗位权限表
     * 并判断该岗位下是否有用户 如果有 则提示先解除该岗位下绑定的用户
     */
    @RequestMapping(value="deleteGrade")
    @ResponseBody
    public String deleteGrade(@RequestParam(value="g_id") String g_id){
        //0:删除成功 1:岗位下有绑定的用户
        String back="0";

        //首先判断有没有绑定的用户
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("gradeid",new BigDecimal(g_id));
        Map<String,Object> order = new HashMap<String,Object>();

        List<TCommonUserGrade>  ugList = tCommonUserGradeService.getTCommonUserGradeListByQueryRule(param,order);

        if(ugList!=null && ugList.size()>0){
            back="1";
        }else{
            //删除数据
            tCommonGradeService.deleteGradeCascade(new BigDecimal(g_id));
        }


        return back;


    }



}
