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



------

## 请求转发

请求转发，是一种服务器的行为，当客户端请求到达后，服务器进行转发，此时会将请求对象进行保存，地址栏中的URL地址不会改变，得到响应后，服务端再将响应发送给客户端，从始至终只有一个请求发出。

```java
req.getRequestDispatcher(url).forward(req, resp);
```

```java
/**
 * 请求转发跳转
 * req.getRequestDispatcher(url).forward(req, resp);
 * 可以让请求从服务器端跳转到客户端（或跳转到指定Servlet）
 *
 * 特点：
 * 1.服务端行为
 * 2.地址栏不发生改变
 * 3.从始至终只有一个请求
 * 4.request数据可以共享
 */
@WebServlet("/ser3")
public class Servlet3 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受客户端请求的参数
        String uname = req.getParameter("uname");
        System.out.println("Servlet3 uname : " + uname);

        //请求转发跳转到Servlet4
//        req.getRequestDispatcher("ser4").forward(req, resp);    //相当于传到ser4中的req，resp

        //请求转发跳转到jsp页面
//        req.getRequestDispatcher("login.jsp").forward(req, resp);

        //请求转发跳转到html页面
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
```



------

## request作用域

通过该对象可以在一个请求中传递数据，作用范围：在一次请求中有效，即服务器跳转有效（只会在请求转发中有效）。

request域对象中的数据在一次请求中有效，则经过请求转发，request域中的数据依然存在，则在请求转发的过程中可以通过request来传输/共享数据

```java
<%
    // 获取域对象
    // 获取对象内容
    // 获取对象内容
    String name = (String) request.getAttribute("name");
    System.out.println("name: " + name);
    Integer age = (Integer) request.getAttribute("age");
    System.out.println("age: " + age);
    List<String> list = (List<String>) request.getAttribute("list");
    System.out.println(list.get(0));
%>
```



------



# HttpServletResponse对象

web服务器收到客户端的http请求，会针对每一次请求，分别创建一个用于**代表请求**的request对象和**代表响应**的response对象。

request和response对象代表请求和响应：获取客户端数据，需要通过request对象；**向客户输出数据，需要通过response对象**。

HttpServletResponse的主要功能用于服务器和客户端的请求进行响应，将web服务器处理后的结果返回给客户端。service()方法中的形参接受的是HttpServletResponse接口的实例化对象，这个对象中封装了向客户端发送数据，发送响应头，发送响应状态码的方法。

------

## 响应数据

```java
/**
 * 响应数据
 * getWrite（） 字符输出流（输出字符串）
 * getOutputStream（）  字节输出流（输出一切数据）
 * 两个不能同时使用 
 */
@WebServlet("/ser7")
public class servlet7 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        //getWrite（）
//        //获取字符输出流
//        PrintWriter writer = resp.getWriter();
//        //输出数据
//        writer.write("hello world");

        //getOutputStream()
        ServletOutputStream out = resp.getOutputStream();
        //输出数据
        out.write(("hello world".getBytes());
    }
```



## 响应乱码问题

解决方案一：

```Java
 * 第一步，设置服务端的编码格式
 * resp.setContentType("UTF-8");
 * 第二部，设置客户端的编码格式
 * resp.setHeader("content-type","text/html;charset=UTF-8")
 *
 * 以上，需要设置客户端和服务端的编码都支持中文，并保持一致
```

解决方案二：

```java
* 同时设置客户端和服务端的编码格式
* resp.setContentType("text/html;charset=UTF-8")
```

------

字节流响应数据，响应中文必定会乱码

```Java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        //设置服务端的编码格式
//        resp.setCharacterEncoding("UTF-8");
//        //设置客户端的编码格式和响应的MIME类型
//        resp.setHeader("content-type","text/html;charset=UTF-8");

        //同时设置客户端和服务端的编码格式
        resp.setContentType("text/html;charset=UTF-8");

        //getWrite（）
        //获取字符输出流
        PrintWriter writer = resp.getWriter();
        //输出数据
        writer.write("<h2>你好</h2>");
```

------



字节流响应数据，解决方案同字符流一样

```Java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    //同时设置客户端和服务端的编码格式
    resp.setContentType("text/html;charset=UTF-8");

    //getOutputStream()
    ServletOutputStream out = resp.getOutputStream();
    //输出数据
    out.write("<h2>你好<h2>".getBytes("UTF-8"));
```