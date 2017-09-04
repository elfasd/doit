package com.asd.common.activiti;

import org.activiti.engine.*;

/**
 * alp
 * Created by zlp on 2017/1/23.
 */
public class Test {

    public static void main(String args[]){

        /*
        //初始化数据库方法
        ProcessEngine processEngine =
                ProcessEngineConfiguration.
                        createProcessEngineConfigurationFromResource("activiti/applicationContext-activiti.xml")
                        .buildProcessEngine();
        System.out.println("------processEngine:" + processEngine);
        */

        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti/applicationContext-activiti.xml").buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        repositoryService.createDeployment().addClasspathResource("TestActiviti.bpmn").deploy();
        String processId = runtimeService.startProcessInstanceByKey("asd").getId();

        TaskService taskService = processEngine.getTaskService();




    }

}
