package web04.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import web04.controller.MemberAddController;
import web04.controller.MemberDeleteController;
import web04.controller.MemberListController;
import web04.controller.MemberLoginController;
import web04.controller.MemberLogoutController;
import web04.controller.MemberUpdateController;
import web04.dao.MySqlMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
    static ApplicationContext applicationContext;
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            //applicationContext = new ApplicationContext();
            
            String resource ="web/dao/mybatis-config.xml";
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

}
