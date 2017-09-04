package com.asd.modules.controller;

import com.asd.common.datatables.DataTable;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fzacc.model.FzAcc;
import com.asd.modules.pojo.user.model.TCommonUser;
import com.asd.modules.pojo.user.vo.TCommonUserVo;
import com.asd.modules.service.FzAccService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统监控Controller
 * Created by zlp on 2017/1/16.
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private FzAccService fzAccService;

    /**
     * 查询账单状态
     * @return map keylist：all,undeal,sff,settle value:数量
     */
    @RequestMapping("getAccCondition")
    @ResponseBody
    public Map getAccCondition(){

        Map<String,Object> param = new HashMap<String,Object>();
        String all = " where 1=1 ";
        String undeal = " where substr(dealflag,1,1) = '0' ";
        String sff = " where substr(dealflag,1,1)='2' ";
        String settle = " where substr(dealflag,1,1)='1' ";

        long i_all = fzAccService.getCountByCondition(all,param);
        long i_undeal = fzAccService.getCountByCondition(undeal,param);
        long i_sff = fzAccService.getCountByCondition(sff,param);
        long i_settle = fzAccService.getCountByCondition(settle,param);

        Map<String,Long> back = new HashMap<String,Long>();
        back.put("all",i_all);
        back.put("undeal",i_undeal);
        back.put("sff",i_sff);
        back.put("settle",i_settle);

        return back;
    }

    /**
     * 菜单跳转
     * @param typo 账单状态
     * @return 跳转路径
     */
    @RequestMapping(value="showAccDetail/{typo}")
    public ModelAndView  showAccDetail(@PathVariable("typo") String typo){
            return new ModelAndView("fzacc/fzaccShow","typo",typo);
    }

    @RequestMapping(value="getFzAccPages")
    @ResponseBody
    public DataTable getFzAccPages(@RequestParam(value="dealFlag") String dealFlag, @RequestParam(value="draw") String draw,
                                    @RequestParam(value="start") int start, @RequestParam(value="length") int length){

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
        FzAcc vo = new FzAcc();
        vo.setDealFlag(dealFlag);

        Pagination page = fzAccService.queryWithPages(vo,pageNo,pageSize);
        DataTable table = new DataTable();
        table.setDraw(draw);
        table.setRecordsTotal(String.valueOf(page.getRowsCount()));
        table.setRecordsFiltered(String.valueOf(page.getRowsCount()));

        List<FzAcc> accList =  page.getItems();
        table.setData(accList);
        return table;
    }
}
