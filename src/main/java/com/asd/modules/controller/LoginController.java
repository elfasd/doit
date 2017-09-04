package com.asd.modules.controller;

import com.sinosoft.bpsdriver.domain.getMenuBySvr.MenuResInfo;
import com.sinosoft.bpsdriver.service.facade.SaaAPIService;
import com.sinosoft.bpsdriver.service.facade.UserMgrAPIService;
import com.sinosoft.bpsdriver.service.spring.SaaAPIServiceImpl;
import com.sinosoft.bpsdriver.service.spring.UserMgrAPIServiceImpl;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * 错误输入日志
     */
    public static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private SaaAPIService saa = new SaaAPIServiceImpl();
    private UserMgrAPIService userAPIService = new UserMgrAPIServiceImpl();
    private List<MenuResInfo> menuResInfos = null;


//    @Autowired
//    private SessionDAO shiroSessionDAO;
    /**
     * shiro登录认证
     * @param usercode 登录用户名
     * @param userpwd 用户密码
     * @param model 暂时没有用
     * @return 跳转路径 成功后跳转至index.jsp 失败重定向到 login.jsp
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户", httpMethod = "POST", response = LoginController.class, notes = "add user")
    public String login(String usercode, String userpwd, ModelMap model, HttpServletRequest request, HttpServletResponse response ){

        try {

            menuResInfos =  userAPIService.getMenuByUser("reinsop","A000008669","1");
            for(MenuResInfo m : menuResInfos) {
                System.out.println(m.getMENUCNAME() +"   ----------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.servlet.http.Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        for(Cookie cookie : cookies){
            System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
        }

        log.info(usercode + " 用户尝试登录");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(usercode,userpwd);
        token.setRememberMe(true);
        try {
            //如果已登录 返回index.jsp
            if (subject.isAuthenticated()) {
                return "redirect:/index.jsp";
            }
            //验证新的session
            subject.login(token);

        }catch(Exception e){
            e.printStackTrace();
            //say something!
        }

       return "redirect:/index.jsp";
    }

    /**
     * 退出当前登录
     * @return 重定向到登录界面
     *
     */
    @RequestMapping(value = "logout")
    public String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    public static void main(String[] args) {

    }


}
