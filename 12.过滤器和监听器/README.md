# 过滤器和监听器

## 过滤器

Filter即为过滤，用于在Servlet之外对request或者response进行修改，它主要用于**对用户请求进行预处理**，也可以对**HttpServletResponse进行后处理**。使用Filter的完整流程：Filter对用户请求进行预处理，接着将请求交给Servlet进行处理并生成响应，最后Filter再对服务器响应进行后处理。在一个web应用中，可以开发多个Filter，这些Filter组合起来称之为一个Filter链。

如果是一个过滤器链：先配置先执行（请求是的执行顺序），响应时：以相反的顺序执行。



## 过滤器的实现

```
/**
 * 过滤器
 *  1.@WebFilter("/s1")    配置拦截的资源路径
 *  2.doFilter()方法中需要设置放行，否则请求无法到达资源 （filterChain.doFilter(servletRequest, servletResponse);）
 *  3.如果是过滤器链，则先配置的先执行（首字母在前的先执行）；响应时，先配置的后执行
 */
```



```java
@WebFilter("/s1")      //拦截路径是s1的资源
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter1 init");

    }

    /**
     * 过滤方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //doFilter放行方法前去做请求拦截的
        System.out.println("Filter1 正在拦截");
        //放行资源
        filterChain.doFilter(servletRequest, servletResponse);
        //doFilter放行方法后去做响应拦截的
        System.out.println("Filter1 处理响应");
    }

    @Override
    public void destroy() {
        System.out.println("Filter1 destroy");
    }
}
```



------

## 实例

### 请求乱码处理

```java
@WebFilter("/*")
public class Encoding implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //基于HTTP
        HttpServletRequest arg0 = null;
        HttpServletRequest request = arg0;
        HttpServletResponse arg1 = null;
        HttpServletResponse response = arg1;

        //处理请求乱码（处理post请求）
        request.setCharacterEncoding("UTF-8");

        //处理get请求且服务器在tomcat8以下
        String method = request.getMethod();
        //如果是get请求
        if ("GET".equalsIgnoreCase(method)) {
            //服务器版本在tomcat8以下的apache tomcat
            String serverinfo = request.getServletContext().getServerInfo();

            //得到具体的版本号（字符串截取）
            String versionStr = serverinfo.substring(serverinfo.indexOf("/") + 1, serverinfo.indexOf("."));
            //判断服务器版本是否小于8
            if (Integer.parseInt(versionStr)<8) {
                //得到自定义内部类
                HttpServletRequest myRequest = new MyWapper(request);
                //放行资源
                filterChain.doFilter(myRequest,response);
                return;

            }

        }

        //放行资源
        filterChain.doFilter(request, response);
    }

    /**
     * 定义内部类，继承HttpServletRequestWrapper包装类对象，重写getParamter（）方法
     */
    class MyWapper extends HttpServletRequestWrapper{

        private HttpServletRequest request;

        public MyWapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
    }

    /**
     * 重写getparameter方法
     */
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (value == null && !"".equals(value.trim())) {
            try {
                //将默认ISO编码转换为UTF-8
                value = new String(value.getBytes("ISO-8859-1"),"utf-8");
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    @Override
    public void destroy() {

    }
}
```



------

### 用户非法访问拦截

```
/**
 * 非法访问拦截
 *  拦截的资源：
 *      拦截所有的资源 /*
 *  需要放行的资源：
 *      1.放行指定页面（无需登录既可访问的页面 例如：登录页面，注册页面等）
 *      2.放行静态资源 （image，js，css文件等）
 *      3.放行指定操作 （无需登录即可执行的操作 例如：登录操作，注册操作）
 *      4.放行登录状态 （判断session中用户信息是否为空）
 *
 *      其他请求需要被拦截跳转到登录页面
 *
 */
```



```java
@WebFilter("/*")
public class LoginAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //基于HTTP请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求的路径
        String url = request.getRequestURI();
        System.out.println(url);

        //1.放行指定页面（无需登录既可访问的页面 例如：登录页面，注册页面等）
        if (url.contains("/login.jsp")) {
            filterChain.doFilter(request,response);
            return;
        }

        //2.放行静态资源 （image，js，css文件等）
        if (url.contains("/js") || url.contains("/image") || url.contains("/css")) {
            filterChain.doFilter(request, response);
            return;
        }

        //3.放行指定操作 （无需登录即可执行的操作 例如：登录操作，注册操作）
        if (url.contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        //4.放行登录状态 （判断session中用户信息是否为空）
        String uname = (String) request.getSession().getAttribute("user");
        //判断session是否为空
        if (uname != null) {
            filterChain.doFilter(request, response);
            return;
        }

        //当用户未登录时，拦截请求跳转到登录页面
        response.sendRedirect("login.jsp");

    }

    @Override
    public void destroy() {

    }
}
```



------

## 监听器

web监听器是Servlet中一种特殊的类，能帮助开发者监听web中的特定事件，比如**ServletContext，HttpSession，ServletRequest**的创建和销毁（生命周期）；变量的创建，销毁和修改等。可以在某种动作前后增加处理，实现监控。例如可以用来统计在线人数等。

```java
@WebListener
public class Listener1 implements HttpSessionListener {
    /**
     * 当session对象被创建时执行
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session对象被创建");
    }

    /**
     * 当session对象被销毁时执行
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session被销毁了");
    }
}
```



```java
/**
 * 在线人数统计
 *  当有新的Session对象创建时，人数+1
 *  当有session对象销毁时，人数-1
 */
@WebListener
public class OnlineListener implements HttpSessionListener {

    //定义在线人数
    private Integer onlineNumber = 0;

    /**
     * 当有新的Session对象创建时，人数+1
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //人数+1
        onlineNumber++;
//        //将人数设置到session作用域中
//        se.getSession().setAttribute("onlineNumber",onlineNumber);

        //将人数设置到servletContext作用域中
        se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    }

    /**
     * 当有session对象销毁时，人数-1
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //人数-1
        onlineNumber--;
        //将人数设置到servletContext作用域中
        se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber);
    }
}
```



```java
@WebServlet("/online")
public class OnlineServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建session对象
        HttpSession session = req.getSession();


        //获取参数值 key
        String key = req.getParameter("key");
        //判断参数是否为空，如果不为空，则表示做退出操作
        if (key != null && "logout".equals(key)) {
            //销毁session对象
            session.invalidate();
            return;
        }


        /**
         * 登录操作
         */

        //得到当前作用域中的在线人数
        Integer onlineNumber = (Integer) session.getServletContext().getAttribute("onlineNumber");

        //设置响应类型及编码
        resp.setContentType("text/html;charset=UTF-8");

        //输出人数
        resp.getWriter().write("<h2>当前在线人数：" + onlineNumber + "</h2> <a href='online?key=logout'>退出</a>");
    }
}
```



------

