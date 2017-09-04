package com.asd.common.shiro.realm;

import com.sinosoft.bpsdriver.domain.getMenuBySvr.MenuResInfo;
import com.sinosoft.bpsdriver.service.facade.SaaAPIService;
import com.sinosoft.bpsdriver.service.facade.UserMgrAPIService;
import com.sinosoft.bpsdriver.service.spring.SaaAPIServiceImpl;
import com.sinosoft.bpsdriver.service.spring.UserMgrAPIServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 从PICC用户系统获取权限并且授权
 * Created by asd on 2017/7/3.
 */
public class SaaRealm extends AuthorizingRealm{

    private SaaAPIService saa = new SaaAPIServiceImpl();
    private UserMgrAPIService userAPIService = new UserMgrAPIServiceImpl();
    private List<MenuResInfo> menuResInfos = null;


    /**
     *  用于授权
     * @param principalCollection 授权信息
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //这里需要注入service
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        try {

            //添加权限
            List<String> permissions =  new ArrayList<String>();
             List<String> sss = saa.getTaskCodeByUser("reins",username,"");

             for(String s:sss){
                 System.out.println(s);
             }

            permissions.add("admin");
//            permissions.add("user:delete");
//            permissions.add("user:update");
//            permissions.add("user:find");
            //给当前用户分配权限
            info.addStringPermissions(permissions);

            //添加role 即我们的grade
            //List<String>  roles = saa.getGradeMsgByUserCode("reins",username);
            //这里的roles的返回结果其实是 gradecode-gradecname
            // PageInfo p = userAPIService.getGradeByUserCode(username,"reins");


            List<String>  roles = new ArrayList<>();
            roles.add("4分保");
            info.addRoles(roles);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return info;
    }

    /**
     *  用于认证
     * @param authenticationToken 用于认证
     * @return 用于认证
     * @throws AuthenticationException 用于认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = authenticationToken.getPrincipal().toString();
        String password = new String((char[])authenticationToken.getCredentials());

        System.out.println(userName +"尝试登陆 他的密码是  " + password);

        boolean b = false;
        try{
            //boolean b = saa.checkLoginByPwd("casServer","A000001127","zbxt2017","nameAndPwd");
            b = saa.checkLoginByPwd("casServer",userName,password,"nameAndPwd");

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        if(!b){
            throw new UnknownAccountException("用户名或密码错误!");
        }

        return new SimpleAuthenticationInfo(userName,password,getName());

    }
}
