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

