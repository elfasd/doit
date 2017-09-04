package com.asd.modules.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流处理
 * Created by lenovo on 2017/2/6.
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {


    private RuntimeService runtimeService;
    private ProcessEngine processEngine;





    @RequestMapping(value="startProcess")
    @ResponseBody
    public Map start(){

        try{
            Map<String,Object> vs = new HashMap<String,Object>();
            vs.put("userId","admin");
            RuntimeService runtimeService = processEngine.getRuntimeService();
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess",vs);

            System.out.println("11111111111111");



            List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee("admin").list();

            for(Task task : tasks){
                System.out.println("---------------- ");

                System.out.println("待办id "+task.getId());
                System.out.println("任务名称 "+ task.getName());
                System.out.println("任务创建时间 "+task.getCreateTime());
                System.out.println("任务办理人 "+task.getAssignee());
                System.out.println("流程实例ID "+task.getProcessInstanceId());
                System.out.println("执行对象ID "+task.getExecutionId());
                System.out.println("流程定义ID "+task.getProcessDefinitionId());


                System.out.println("---------------- ");

            }
        }catch(Exception e){
            e.printStackTrace();
        }


        Map<String,Long> back = new HashMap<String,Long>();
        back.put("all",Long.valueOf("123"));


        return back;
    }



}
