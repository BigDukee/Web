# HttpServletRequest对象

HttpServletRequest对象：主要作用是用来接收客户端发送过来的请求信息，例如：请求的参数，发送的头信息等都属于客户端发来的信息，service()方法中形参接受的是HttpServletRequst接口的实例化对象，表示该对象主要应用在HTTP协议上，该对象是由Tomcat封装好传递过来。

HttpServletRequest是ServletRequest的子接口，ServletRequest只有一个子接口，就是HttpServletRequest。

从长远来看：现在主要使用的协议是HTTP协议，但是以后可能会出现更多的新协议。若以后想支持这种新协议，只需要直接继承ServletRequest接口就行了。

在HttpServletRequest接口中，定义的方法很多，但都是围绕接受客户端参数的。这些对象直接在Service方法中有容器传入过来，而我们需要做的就是去除对象中的数据，进行分析和处理。一切请求都围绕Request。

 ## 接受请求

### 常用方法

| 方法             | 内容                                       |
| :--------------- | ------------------------------------------ |
| getRequestURL()  | 获取客户端发送请求时的完整URL              |
| getRequestURI()  | 获取请求行中的资源名称部分（项目名称开始） |
| getQueryString() | 获取请求行中的参数部分                     |
| getMethod()      | 获取客户端请求方式                         |
| getProtocol()    | 获取HTTP版本号                             |
| getContextPath() | 获取webapp名字                             |



```Java
@WebServlet("/ser1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //常用方法
        //获取请求时的完整路径（从http开始，到"?"前面结束）
        String url = req.getRequestURL() + "";
        System.out.println("获取请求时的完整路径：" + url);

        //获取请求时的部分路径（从项目的站点名开始，到"?"前面结束）
        String uri = req.getRequestURI();
        System.out.println("获取请求时的部分路径：" + uri);

        //获取请求时的参数字符串（到"?"后面开始，到最后的字符串）
        String queryString = req.getQueryString();
        System.out.println("获取请求时的参数字符串：" + queryString);

        //获取请求方式（GET和POST）
        String method = req.getMethod();
        System.out.println("获取请求方式：" + method);

        //获取当前协议版本（HTTP/1.1）
        String protocol = req.getProtocol();
        System.out.println("获取当前协议版本：" + protocol);

        //获取项目的站点名（项目对外访问路径）
        String Webapp = req.getContextPath();
        System.out.println("获取项目站点名：" + Webapp);
    }
}
```

> 获取请求时的完整路径：http://localhost:8080/s01/ser1
> 获取请求时的部分路径：/s01/ser1
> 获取请求时的参数字符串：null
> 获取请求方式：GET
> 获取当前协议版本：HTTP/1.1
> 获取项目站点名：/s01



------

### 获取请求参数

当我们使用getQueryString()的时候，发现传过来的是一大串字符串，不方便，所以引申出来两个方法**getParameter(name)**和**getParameterValues(String name)**

| 方法                            | 内容                     |
| ------------------------------- | ------------------------ |
| getParameter(name)              | 获取指定名称的参数       |
| getParameterValues(String name) | 获取指定名称参数的所有值 |



```Java
        //获取请求的参数
        //获取指定名称的参数值,返回字符串（重点）
        String unmae = req.getParameter("uname");     //括号内的值一定要与传过来的参数保持一致
        String upwd = req.getParameter("upwd");
        System.out.println("uname:" + unmae + ", upwd：" + upwd);


        //获取指定名称的参数的所有参数值，返回字符串数组（用于复选框传值）
        String[] hobbys = req.getParameterValues("hobby");
        //判断数组是否为空
        if(hobbys != null && hobbys.length > 0){
            for(String hobby : hobbys){
                System.out.println("爱好：" + hobby);
            }
        }
```



------



## 请求乱码问题

由于request属于接收客户端的参数，所以必然有其默认的语言编码，主要在解析过程中默认使用的是编码方式为ISO-8859-1（此编码不支持中文），所以解析时一定会出现乱码。需要设置request中的编码方式，告诉服务器以何种方式来解析数据，或者在接收到乱码数据以后，再通过相应的解码格式还原。

```Java
/**
 * 乱码原因：
 * 由于在解析过程中默认使用的编码方式不支持中文，所以解析式一定会出现乱码
 *
 * 请求乱码问题
 * GET请求 Tomcat8以上不会乱码
 * POST请求 会乱码，通过设置服务器解析编码格式
 *req.setCharacterEncoding("UTF-8")     只能针对POST请求，POST请求才有效
 *
 * 如果tomcat版本小于等于7，需要借助String对象的方法，对任何请求都有效，通用
 */
@WebServlet("/ser2")
public class servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("UTF-8");

        //获取客户端传递的参数
        String uname = req.getParameter("uname");
        String upwd = req.getParameter("upwd");
        System.out.println("姓名：" + uname);
        System.out.println("姓名：" + upwd);

        //解决tomcat7及一下版本的GET请求编码，不能随便乱用
        String name = new String(req.getParameter("uname").getBytes("ISO-8859-1"),"UTF-8")
        System.out.println("姓名：" + name);
    }

```

