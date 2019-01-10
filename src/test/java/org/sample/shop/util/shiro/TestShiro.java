//package org.sample.shop.util.shiro;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.config.IniSecurityManagerFactory;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.util.Factory;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class TestShiro {
//
//    private static final Logger LOGGER = LogManager.getLogger();
//
//    @Test
//    public void test() {
//        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
//
//        try {
//            subject.getUser(token);
//        } catch (AuthenticationException e) {
//            LOGGER.debug(e);
//        }
//        assertEquals(true, subject.isAuthenticated());
//        subject.logout();
//        assertEquals(false, subject.isAuthenticated());
//    }
//
//    @Test
//    public void testCustomRealm() {
//        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
//        Factory<org.apache.shiro.mgt.SecurityManager> factory =
//                new IniSecurityManagerFactory("classpath:shiro-realm.ini");
//
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
//        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//
//        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1234");
//
//        try {
//            //4、登录，即身份验证
//            subject.getUser(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//            LOGGER.debug(e);
//        }
//
//        assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
//
//        //6、退出
//        subject.logout();
//    }
//}
