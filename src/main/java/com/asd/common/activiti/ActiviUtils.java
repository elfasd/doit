package com.asd.common.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流工具类
 * Created by lenovo on 2017/2/6.
 */
public class ActiviUtils {


    /**
     * 根据applicationContext-activiti.xml的配置初始化数据库
     * 会建立25张工作流的表
     */
    public static void initActiviti(){

        /**
         *
         * 流程部署相关表
         act_re_deployement 部署对象表
         act_rep_procdef  流程定义表
         act_ge_bytearray 资源文件表
         act_ge_prperty  主键生成策略表（对于部署对象表的主键ID）

         流程实例相关表
         act_ru_execution 正在执行的执行对象表（包含执行对象ID和流程实例ID，如果有多个线程可能流程实例ID不一样）
         act_hi_procinst 流程实例历史表
         act_hi_actinst 存放历史所有完成的任务

         Task 任务相关表
         act_ru_task 代办任务表 （只对应节点是UserTask的）
         act_hi_taskinst 代办任务历史表 （只对应节点是UserTask的）
         act_hi_actinst  所有节点活动历史表 （对应流程的所有节点的活动历史，从开始节点一直到结束节点中间的所有节点的活动都会被记录）

         流程变量表
         act_ru_variable 正在执行的流程变量表
         act_hi_variable 流程变量历史表
         */

        //初始化数据库方法
        ProcessEngine processEngine = getEngine();
        System.out.println("------processEngine:" + processEngine);

    }


    public static ProcessEngine getEngine(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        // 2.设置数据库连接信息

        // 设置数据库地址
        configuration
                .setJdbcUrl("jdbc:mysql://localhost:3306/activiti?createDatabaseIfNotExist&amp;useUnicode=true&amp;characterEncoding=utf8");
        // 数据库驱动
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        // 用户名
        configuration.setJdbcUsername("root");
        // 密码
        configuration.setJdbcPassword("mysql");

        // 设置数据库建表策略
        /**
         * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
         * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
         * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 3.使用配置对象创建流程引擎实例（检查数据库连接等环境信息是否正确）
        ProcessEngine processEngine = configuration.buildProcessEngine();
        return processEngine;
    }


    public static void deploy(){

// 1.创建Activiti配置对象的实例
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        // 2.设置数据库连接信息

        // 设置数据库地址
        configuration
                .setJdbcUrl("jdbc:mysql://localhost:3306/gqis?createDatabaseIfNotExist&amp;useUnicode=true&amp;characterEncoding=utf8");
        // 数据库驱动
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        // 用户名
        configuration.setJdbcUsername("root");
        // 密码
        configuration.setJdbcPassword("mysql");

        // 设置数据库建表策略
        /**
         * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
         * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
         * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
         */
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 3.使用配置对象创建流程引擎实例（检查数据库连接等环境信息是否正确）
        ProcessEngine processEngine = configuration.buildProcessEngine();



        Deployment deployment = processEngine.getRepositoryService().createDeployment()
                .name("helloactiviti入门1").addClasspathResource("activiti/diagrams/HelloWorld.bpmn")
                .addClasspathResource("activiti/diagrams/HelloWorld.png").deploy();


        System.out.println(deployment.getId());

        System.out.println(deployment.getName());


    }


    public static void startFlow(){
        ProcessEngine processEngine = getEngine();

        Map<String,Object> vs = new HashMap<String,Object>();
        vs.put("userId","admin");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess",vs);
        System.out.println(processInstance.getId());

        System.out.println(processInstance.getProcessDefinitionId());

        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        System.out.println(processDefinition.getId());

        System.out.println(processDefinition.getKey());

    }

    public static void main(String args[]){


        try{

initActiviti();


//            ProcessEngine processEngine = getEngine();
//
//            List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee("zlp").list();
//
//            for(Task task : tasks){
//                System.out.println("---------------- ");
//
//                System.out.println("待办id "+task.getId());
//                System.out.println("任务名称 "+ task.getName());
//                System.out.println("任务创建时间 "+task.getCreateTime());
//                System.out.println("任务办理人 "+task.getAssignee());
//                    System.out.println("流程实例ID "+task.getProcessInstanceId());
//                System.out.println("执行对象ID "+task.getExecutionId());
//                System.out.println("流程定义ID "+task.getProcessDefinitionId());
//
//                Map<String,Object> vs = new HashMap<String,Object>();
//                vs.put("boss","aaas");
//               // processEngine.getTaskService().complete(task.getId(),vs);
//                System.out.println("---------------- ");
//
//            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
