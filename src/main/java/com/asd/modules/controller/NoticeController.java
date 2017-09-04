package com.asd.modules.controller;

import com.asd.common.datatables.DataTable;
import com.asd.common.hibernate.Pagination;
import com.asd.modules.pojo.fileinfo.model.TNoticeFileInfo;
import com.asd.modules.pojo.fileinfo.vo.TNoticeFileInfoVo;
import com.asd.modules.service.TNoticeFileInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * pdf controller
 * Created by lenovo on 2017/1/10.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    /**  注入文件操作service	 */
    private final TNoticeFileInfoService tNoticeFileInfoService;
    @Autowired
    public NoticeController(TNoticeFileInfoService tNoticeFileInfoService) {
        this.tNoticeFileInfoService = tNoticeFileInfoService;
    }





    /**
     * 菜单跳转
     * @return 跳转路径
     */
    @RequestMapping(value="loadPdfManage")
    public String loadPdfManage(){
        return "notice/upload/loadPdfManage";
    }

    /**
     * 文件上传
     */
    @RequestMapping(value="fileUpload")
    @ResponseBody
    public String fileUpload(HttpServletRequest request) throws Exception {

        ResourceBundle bundle = ResourceBundle.getBundle("upload.filepath", Locale.getDefault());
        String suffixPath = bundle.getString("noticePDF");

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //现在获取的是tomcat部署的目录
        //部署后没有问题 现在本地部署方式下不太适用
        String root = request.getSession().getServletContext().getRealPath("");


        //这里暂时是单文件上传
        String pdfUploadFileName ="";
        String pdfUploadContentType = "";
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = root + suffixPath + File.separator+file.getOriginalFilename();
                    pdfUploadFileName = file.getOriginalFilename();
                    pdfUploadContentType = file.getContentType();
                    //上传
                    File f = new File(path);
                    if(!f.getParentFile().exists()){
                        if(!f.getParentFile().mkdirs()) {
                            System.out.println("创建目标文件所在目录失败！");
                        }
                    }
                    file.transferTo(f);
                }

            }


            Map<String, Object> param = new HashMap<>();
            param.put("fileName", pdfUploadFileName);
            Map<String, Object> order = new HashMap<>();

            List<TNoticeFileInfo> list = tNoticeFileInfoService.getByQueryRule(param, order);
            if (list != null && list.size() > 0) {
                //如果数据库中有数据 什么都不做
                //因为这张表设计的就是单独为上传通知使用的
                System.out.println("do nothing!");
            } else {
                TNoticeFileInfo info = new TNoticeFileInfo();
                info.setFileName(pdfUploadFileName);
                info.setFilePath(root + suffixPath);
                info.setFileType(pdfUploadContentType);
                info.setOperateTime(new Date());

                //从缓存获取登录的code
                Subject subject = SecurityUtils.getSubject();
                info.setOperator((String) subject.getSession().getAttribute("logincode"));
                try {
                    tNoticeFileInfoService.save(info);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "ok";
    }

    /**
     * 获取所有文件信息的列表
     */
    @RequestMapping(value="getAllFileInfoJson")
    @ResponseBody
    public DataTable getAllFileInfoJson(@RequestParam(value="start") int start,
                                        @RequestParam(value="length") int length,
                                        @RequestParam(value="draw") String draw){

        int pageNo,pageSize;
        //获取分页信息
        pageNo = start / length + 1;
        if (pageNo == 0) {
            pageNo = 1;
        }
        pageSize = length;
        if (pageSize == 0) {
            pageSize = 20;
        }
        Pagination page=null;
        //查询出所有通知的文件信息
        try{
            Map<String,Object> param = new HashMap<>();
            Map<String,Object> order = new HashMap<>();
            page = tNoticeFileInfoService.getByQueryRulePages(param,order,pageNo,pageSize);
        }catch(Exception e){
            e.printStackTrace();
        }
        DataTable table = new DataTable();
        table.setDraw(draw);
        table.setRecordsTotal(String.valueOf(page.getPagesCount()));
        table.setRecordsFiltered(String.valueOf(page.getPagesCount()));
        List voList = new ArrayList();
        List<TNoticeFileInfo> fileList =  page.getItems();

        for(TNoticeFileInfo file : fileList){
            TNoticeFileInfoVo vo = new TNoticeFileInfoVo();
            vo.setF_id(file.getF_id());
            vo.setFileName(file.getFileName());
            vo.setFilePath(file.getFilePath());
            vo.setFileType(file.getFileType());
            vo.setFlag(file.getFlag());
            vo.setOperateTime(file.getOperateTime());
            vo.setOperator(file.getOperator());
            voList.add(vo);

        }
        table.setData(voList);
        return table;

    }

}
