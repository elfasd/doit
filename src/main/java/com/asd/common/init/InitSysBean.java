package com.asd.common.init;

import com.asd.modules.pojo.dic.model.TDicGroup;
import com.asd.modules.pojo.dic.model.TDicItem;
import com.asd.modules.service.TDicGroupService;
import com.asd.modules.service.TDicItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 初始化类
 * Created by lenovo on 2017/2/9.
 */
@Component
public class InitSysBean implements InitializingBean {


    @Autowired
    private RedisTemplate stringRedisTemplate;

    @Autowired
    private TDicItemService tDicItemService; //item字典子表注入

    @Autowired
    private TDicGroupService tDicGroupService; //group字典主表注入

    @Resource(name = "stringRedisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valueOps;

    @Resource(name = "stringRedisTemplate")
    private HashOperations<String, String, Object> hashOps;

    @Resource(name = "stringRedisTemplate")
    private SetOperations<String, String> setOps;

    @Resource(name = "stringRedisTemplate")
    private ZSetOperations<String, String> ZSetOps;

    public static final Logger log = LoggerFactory.getLogger(InitSysBean.class);


    /**
     * 当spring的baen都加载完毕后执行
     * @throws Exception 异常
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        //reids初始化
        //redisInit();

    }


    public void redisInit(){
        log.info("系统初始化开始!");

        log.info("redis初始化开始");

        log.info("redis初始化结束");


        List<TDicGroup> groups =  tDicGroupService.getTDicGroupListByQueryRule(new HashMap(),new HashMap());
        List<TDicItem> items =  tDicItemService.getTDicItemListByQueryRule(new HashMap(),new HashMap());

        for(TDicItem item : items){
            Map<String,String> map = new HashMap<String,String>();
            hashOps.put(item.getGroup_code(),item.getItem_code(),item.getItem_name());
        }


        //获取id为 ITEMCODE  中的其中map的key是01的值
        Object o =  hashOps.get("ITEMCODE","01");

        System.out.println(o);
        log.info("系统初始化结束!");


    }

}
