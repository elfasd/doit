package com.asd.common.quartz;

import com.asd.modules.pojo.menu.model.TCommonMenu;
import com.asd.modules.service.TCommonMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * test quartz
 * Created by zlp on 2017/1/22.
 */
@Component
public class TestQuartz {

    /**
     * 菜单表service注入
     */
    @Autowired
    private TCommonMenuService tCommonMenuService;
    public static final Logger log = LoggerFactory.getLogger(TestQuartz.class);

    //@Scheduled(cron ="0/10 * * * * *")
    public void test(){

        TCommonMenu t = tCommonMenuService.getTCommonMenuById(BigDecimal.ZERO);
        try{
            log.info(t.getActionurl()+ "测试quartz  "+t.getMenucname());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
