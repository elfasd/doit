package com.asd.common.activiti.listener.test;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

/**
 * 测试activiti工作流监听器
 * Created by lenovo on 2017/2/6.
 */
@Service("testactiviti")
public class TestListener implements ExecutionListener {


    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        String eventName = delegateExecution.getEventName();
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        }else if ("end".equals(eventName)) {
            System.out.println("end=========");
        }
    }
}
