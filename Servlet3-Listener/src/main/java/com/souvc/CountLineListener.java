package com.souvc;


import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author souvc
 */

@WebListener
public class CountLineListener implements HttpSessionListener {

    /**
     * 用户上线
     *
     * @param event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("创建session......");
        ServletContext context = event.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        if (count == null) {
            count = new Integer(1);
        } else {
            int co = count.intValue();
            count = new Integer(co + 1);
        }
        System.out.println("当前用户人数：" + count);

        //保存人数
        context.setAttribute("count", count);

    }





    /**
     * 用户下线
     *
     * @param event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("销毁session......");
        ServletContext context = event.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        int co = count.intValue();
        count = new Integer(co - 1);
        context.setAttribute("count", count);
        System.out.println("当前用户人数：" + count);
    }

}