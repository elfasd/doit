package com.asd.modules.controller;

import com.asd.common.constant.SysConst;
import com.asd.common.datatables.DataTable;
import com.asd.common.hibernate.Pagination;
import com.asd.common.ztree.ZTreeNodeTask;
import com.asd.modules.pojo.task.model.TCommonTask;
import com.asd.modules.pojo.task.vo.TCommonTaskVo;
import com.asd.modules.service.TCommonTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    /** 资源service注入 */
    @Autowired
    private TCommonTaskService tCommonTaskService;

    /**
     * 点击菜单后跳转
     *
     * @return 跳转到具体界面
     */
    @RequestMapping(value = "taskManage")
    public String menuManage() {

        System.out.println("2bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        return "task/taskManage";
    }


    /**
     * 查询所有资源
     */
    @RequestMapping(value="queryTask")
    @ResponseBody
    public DataTable queryTask(@RequestParam(value="draw") String draw, @RequestParam(value="start") int start,
                               @RequestParam(value="length") int length,@RequestParam(value="pcode") String pcode){

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

        TCommonTask t = new TCommonTask();
        t.setParentcode(pcode);
        Pagination page = tCommonTaskService.queryWithPages(t, pageNo, pageSize);

        DataTable table = new DataTable();
        table.setDraw(draw);
        table.setRecordsTotal(String.valueOf(page.getPagesCount()));
        table.setRecordsFiltered(String.valueOf(page.getPagesCount()));
        List voList = new ArrayList();

        List<TCommonTask> accList =  page.getItems();
        for(TCommonTask task : accList){
            TCommonTaskVo vo = new TCommonTaskVo();
            vo.setTask_id(task.getTask_id());
            vo.setLx(task.getLx());
            vo.setTaskcode(task.getTaskcode());
            vo.setParentcode(task.getParentcode());
            vo.setTaskcname(task.getTaskcname());
            voList.add(task);
        }
        table.setData(voList);
        return table;
    }


    /**
     *  获取菜单的json格式传送给前台
     */
    @RequestMapping(value="getTaskJson")
    @ResponseBody
    public  List<ZTreeNodeTask> getTaskJson(){

        List<TCommonTask> allTaskList = tCommonTaskService.getTCommonTaskListBySvrCode(SysConst.systemcode);

        List<ZTreeNodeTask> nodes = new ArrayList<ZTreeNodeTask>();
        for(TCommonTask task : allTaskList){
            boolean isopen =true;
            //只展开1级菜单 后续决定是否展开二级菜单
            if(task.getLx().compareTo(new BigDecimal(0))>0){
                isopen=false;
            }

            ZTreeNodeTask node = new ZTreeNodeTask(task.getTaskcode(),task.getParentcode(),task.getTaskcname(),
                    false,isopen,task.getLx().intValue());
            nodes.add(node);
        }

        return nodes;

    }

    /**
     * 增加资源
     */
    @RequestMapping(value="addTask")
    @ResponseBody
    public String addTask(TCommonTask taskAddVo){

        //back参数 0:保存成功 1:功能代码重复
        String back = "0";

        //首先查出父菜单
        String pcode= taskAddVo.getParentcode();

        //功能代码不能重复
        List<TCommonTask> oldList = tCommonTaskService.getTCommonTaskListByTaskCode(taskAddVo.getTaskcode());
        if(oldList!=null && oldList.size()>0){
            //功能代码重复
            back = "1";

        }else{
            //注意 这里的pcode是父资源的taskcode
            List<TCommonTask> pList = tCommonTaskService.getTCommonTaskListByTaskCode(pcode);
            if(pList!=null && pList.size()>0){
                TCommonTask parent = pList.get(0);

                try{
                    TCommonTask newTask = new TCommonTask();
                    newTask.setSvrcode(SysConst.systemcode);
                    newTask.setTaskcname(taskAddVo.getTaskcname());
                    newTask.setTaskcode(taskAddVo.getTaskcode());
                    newTask.setParentcode(taskAddVo.getParentcode());
                    newTask.setLx(parent.getLx().add(new BigDecimal(1)));

                    tCommonTaskService.saveTCommonTask(newTask);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
       return back;

    }

    /**
     * 删除菜单
     */
    @RequestMapping(value="deleteTask")
    @ResponseBody
    public Boolean deleteTask(@RequestParam(value="idList") String idList){

        Boolean hasSubNode = false;

        //获取父菜单的id
        String[] idListSP = idList.split(",");
        //首先判断有没有子菜单
        List<TCommonTask> subList = tCommonTaskService.findSubTaskByIdList(idListSP);
        if(subList!=null && subList.size()>0){
            hasSubNode = true;
        }else{
            tCommonTaskService.deleteByIdList(idListSP);
        }


        return hasSubNode;

    }


    /**
     * 根据任务code获取资源 理论上没有重复的 等同于id
     * @param taskcode 资源任务
     * @return 资源实体json
     */
    @RequestMapping(value="getSelectedTask")
    @ResponseBody
    public TCommonTask getSelectedTask(@RequestParam(value="taskcode") String taskcode){
        //获取父菜单的id
        List<TCommonTask>  taskList = tCommonTaskService.getTCommonTaskListByTaskCode(taskcode);

        if(taskList!=null && taskList.size()>0){
            TCommonTask task = taskList.get(0);
            return task;
        }
        return null;
    }

    @RequestMapping(value="updateTask")
    @ResponseBody
    public String updateTask(TCommonTaskVo taskUpdateVo){

        //获取前台传送的主键
        BigDecimal pk = taskUpdateVo.getTask_id();

        //反馈标志 0:修改成功 1:功能代码重复
        String back = "0";

        //需要更新的数据
        List<TCommonTask> toUpdate = new ArrayList<TCommonTask>();

        //查询出修改前的数据 并记录修改前的taskcode
        TCommonTask task = null;
        try {
            task = tCommonTaskService.getTCommonTaskByPK(pk);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String oldTaskCode = task.getTaskcode();


        //根据修改后的taskcode查询数据
        List<TCommonTask> newList = tCommonTaskService.getTCommonTaskListByTaskCode(taskUpdateVo.getTaskcode());

        if(newList!=null && newList.size()>0){
            //如果有数据并且与原始的taskcode相同 则只更新一条数据
            if(oldTaskCode.equals(taskUpdateVo.getTaskcode())){
                task.setTaskcname(taskUpdateVo.getTaskcname());
                toUpdate.add(task);
            }else{
                //如果有数据 并且与原来的taskcode不同 说明功能代码重复
                back = "1";
            }
        }else{
            //如果没有数据 则证明修改了taskcode 这个时候不仅要修改当前记录,还要修改其子task的parentcode为新的taskcode
            task.setTaskcode(taskUpdateVo.getTaskcode());
            task.setTaskcname(taskUpdateVo.getTaskcname());
            toUpdate.add(task);
            List<TCommonTask> subList = tCommonTaskService.getTCommonTaskListByParentCode(oldTaskCode);
            for(TCommonTask subTask : subList){
                subTask.setParentcode(taskUpdateVo.getTaskcode());
                toUpdate.add(subTask);
            }

        }

        try {
            tCommonTaskService.updateAll(toUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return back;

    }



}
