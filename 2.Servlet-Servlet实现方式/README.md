# servlet

- [servlet](#servlet)
  * [1.三种方式继承servlet，HttpServlet有父类](#1------servlet-httpservlet---)
    + [1.使用HttpServlet继承](#1--httpservlet--)
    + [2.使用GenericServlet](#2--genericservlet)
    + [3.使用Servlet接口](#3--servlet--)
  * [2.重写service方法](#2--service--)

## 1.三种方式继承servlet，HttpServlet有父类

```java
public abstract class HttpServlet extends GenericServlet
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable
public interface Servlet
```



------



### 1.使用HttpServlet继承

```Java
public class servlet1 extends HttpServlet
```

### 2.使用GenericServlet

```java
public class servlet2 extends GenericServlet
```

### 3.使用Servlet接口

```java
public class servlet3 implements Servlet
```

------

## 2.重写service方法

进入service方法里面，会有各种方法

```java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
String method = req.getMethod();
if (method.equals("GET")) 
else if (method.equals("HEAD")) 
else if (method.equals("POST")) 
........
```

直接service方法

```java
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
    }
```

------

重写service方法

```java
    /**
     * GET请求调用方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET请求调用方法");
        //调用POST请求
        doPost(req, resp);
    }

    /**
     * POST请求调用方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST请求调用方法");
        //调用GET请求
        doGet(req, resp);
    }
```

