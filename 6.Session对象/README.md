# HttpSession对象

HttpSession对象是javax.servlet.http.HttpSession的实例，该接口只是一个纯粹的接口。这是因为session本身就属于HTTP协议的范畴。

对于服务器而言，每一个连接到它的客户端都是一个session，servlet容器使用此接口创建HTTP客户端和HTTP服务器之间的会话。会话将保留指定的时间段，跨多个连接或来自用户的页面请求。一个会话通常对应一个用户，该用户可能多次访问一个站点。可通过此接口查看和操作有关某个会话的信息，比如会话标识符，创建时间和最后一次访问时间。在整个session中，最重要的就是属性的操作。

session无论客户端还是服务器端都可以感知到，若重新打开一个新的浏览器，则无法取得的之前设置的session，因为每一个session只保存在当前的浏览器当中，并在相关的页面取得。

session的作用就是为了标识一次会话，或者确认一个用户，并且再一次会话（一个用户的多次请求）期间共享数据。我们可以通过request.getSession()的方法，来获取当前会话的session对象。

```Java
/**
 * session 对象
 * session对象的获取
 * req.getSession()
 * 当获取session对象时，会先判断session对象是否存在，如果存在，获取session对象，或不存在，则创建session对象
 * 常用方法
 * 获取session的会话标识符  getId()
 * 获取session的创建时间   getCreationTime()
 * 获取最后一次访问时间   getLastAccessedTime()
 * 判断是否是新的session对象     isNew()
 */
@WebServlet("/ser1")
public class session1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session
        HttpSession session = req.getSession();

        //获取session的会话标识符
        String id = session.getId();
        System.out.println("id");

        //获取session的创建时间
        System.out.println(session.getCreationTime());

        //获取最后一次访问时间
        System.out.println(session.getLastAccessedTime());

        //判断是否是新的session对象
        System.out.println(session.isNew());
    }
}

```



------



## 标识符JSESSIONID

session既然是为了标记一次会话，那么此次会话就应该有一个唯一的标志，这个标志就是sessionid。

每当一次请求到达服务器，如果开启了会话（访问了session），服务器第一步会查看是否从客户端回传一个名为JSESSIONID的cookie，如果没有则会认为这是一次新的会话，会创建一个新的session对象，并用唯一的sessionid为此次会话做一个标志。如果有JESSIONID这个cookie回传，服务器则会根据JSESSIONID这个值去查看是否含有id为JSESSION值的session对象，如果没有则认为是一个新的会话，重新创建一个新的session对象，并标志此次会话；如果找到了相应的session对象，则认为是此前标志过的一次会话，返回session对象，数据达到共享。

这里提到一个叫做JSESSIONID的cookie，这是一个比较特殊的cookie，当用户请求服务器时，如果访问了session，则服务器会创建一个名为JSESSIONID，值为获取到的session（无论是获取到的还是新创建的）的sessionid的cookie对象，并添加到response对象中，响应给客户端，有效时间为关闭浏览器。

**所以session的底层依赖cookie实现。**



------

## session域对象

session用来表示一次会话，在一次会话中数据是可以共享的，这时session作为域对象存在，可以通过setAttribute(name,value)方法向域对象中添加数据，通过getArrtibute(name)从域对象中获取数据，通过removeAttribute(name)从域对象中移除数据。

```Java
/**
 * session 域对象
 *setAttribute()设置域对象
 * getAttribute()设置域对象
 * removeAttribute()移除域对象
 *
 * 请求转发，一次请求,request作用域是有效的，session作用域有效
 * 重定向，两次请求，request作用域无效，session有效
 */
@WebServlet("/ser2")
public class session2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //session对象
        //获取session对象
        HttpSession session = req.getSession();

        //设置session域对象
        session.setAttribute("uname","admin");
        session.setAttribute("uname1","admin1");

        //移除session域对象
        session.removeAttribute("uname");

        //request域对象
        req.setAttribute("name","admin2");

//        //请求转发跳转到jsp页面
//        req.getRequestDispatcher("index.jsp").forward(req, resp);

        //重定向调转到index.jsp页面
        resp.sendRedirect("index.jsp");
    }
}
```



------

## session对象的销毁



### 默认时间到期

当客户端第一次请求servlet并且操作session时，session对象生成，tomcat中的session默认的存活时间为30min，即使你不操作界面的时间，一旦有操作，session会重新计时。

可以在tomcat中的conf目录下的web.xml文件进行修改

```html
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>
```



### 自己设定到期时间

```Java
		//获取session对象的最大不活动时间
        System.out.println("time: " + session.getMaxInactiveInterval());
        //修改最大不活动时间
        session.setMaxInactiveInterval(15);     //15秒失效
```



### 立即失效

```Java
        //立即销毁
        session.invalidate();
```



### 关闭浏览器

session底层依赖cookie，cookie对象默认只在浏览器内存中存活，关闭浏览器则失效。



### 关闭服务器

关闭服务器，session失效


