## ServletContext对象

每一个web应用都有且仅有一个ServletContext对象，又称Application对象，从名称中可知，该对象是与应用程序相关的。在web容器启动的时候，会为每一个web应用程序创建一个对应的ServletContext对象。

该对象有两大作用，第一，作为域对象用来共享数据，此时数据在整个应用程序中共享；第二，该对象中保存了当前应用程序的相关信息，例如可以通过getServerInfo()方法获取当前服务器信息，getRealPath(String path)获取资源的真是路径。



## ServletContext对象的获取



```Java
/**
 * 获取servletcontext对象
 * 通过request对象获取
 * 通过session对象获取
 * 通过servletconfig对象获取
 * 直接获取
 *
 */
@WebServlet("/ser1")
public class servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取servletcontext对象
        ServletContext servletContext = req.getServletContext();

        //通过session对象获取
        ServletContext servletContext2 = req.getSession().getServletContext();

        //通过servletconfig对象获取
        ServletContext servletContext3 = getServletConfig().getServletContext();

        //直接获取
        ServletContext servletContext4 = getServletContext();

        //常用方法
        //1.获取当前服务器的版本信息
        String serverinfo = req.getServletContext().getServerInfo();
        System.out.println("获取当前服务器版本信息：" + serverinfo);
        //获取项目的真实路径
        String realPath = req.getServletContext().getRealPath("/");
        System.out.println("获取项目的真实路径： " + realPath);
    }
}

```



------

## ServletContext域对象

ServletContext也可以当作域对象来使用，通过向ServletContext中存取数据，可以使得整个应用程序共享某些数据。当然不建议存放过多数据，因为ServletContext中的数据一旦存储进去没有手动移除将会一直保存。



Servlet的三大域对象

1.request域对象

在一次请求中有效。请求转发有效，重定向失效。

2.session域对象

在一次会话中生效。请求转发和重定向都有效，session销毁后失效。

3.servletContext域对象

在整个应用程序中有效。服务器关闭后失效。