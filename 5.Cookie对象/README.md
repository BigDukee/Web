# Cookie对象

Cookie是浏览器提供的一种技术，通过服务器的程序能将一些只需保存在客户端，或者在客户端进行处理的数据，放在本地的计算机上，不需要通过数据传输，因而提高网页处理的效率，并且能够减少服务器的负载，在世由于Cookie是服务器端保存在客户端的信息，所以其安全性也是很差的。例如常见的记住密码则可以通过Cookie来实现。

有一个专门操作Cookie的类**javax.servlet.http.Cookie**。随着服务器端的响应发送给客户端，保存在浏览器。当下次访问服务器时把Cookie再待会服务器。

Cookie的格式：键值对用"="链接，多个键值对通过";"隔开。

------



## Cookie的创建和发送

```java
        //Cookie的创建
        Cookie cookie = new Cookie("name","admin");

        //发送（响应）Cookie对象
        resp.addCookie(cookie);
```



------



## Cookie的获取

```java
        //获取cookie数组
        Cookie[] cookies = req.getCookies();

        //判断cookie是否为空
        if (cookies != null && cookies.length > 0) {
            //遍历cookie数组
            for (Cookie cookie : cookies) {
                //获取cookie的名称和值
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("名称：" + name + "，值：" + value);
            }
        }

```



 

------



## Cookie设置到期时间

cookie是一种浏览器技术，和服务器没有关系，无论服务器是否关闭，cookie只要不到期，就一直存在。

cookie的到期时间指的是用来指定该cookie何时有效。默认为当前浏览器关闭即失效。我们可以手动设定cookie的有效时间。

到期时间的取值

- 负整数

  若为负数，表示不该存储该cookie。

  cookie的maxAge属性的默认值就是-1，表示只在浏览器内存中存活，一旦关闭浏览器窗口，那么cookie就会消失。

  ```Java
  private int maxAge = -1;
  ```

  

- 正整数

  若大于0的正数，表示存储的秒数。

  表示cookie对象可存活指定的秒数。当生命大于0时，浏览器会把cookie保存到硬盘上，就算关闭浏览器，重启客户端电脑，cookie也会存活相应的时间。

- 零

  若为0，表示删除该cookie。

  cookie生命等于0是一个特殊的值，它表示cookie被作废！也就是说，原来浏览器已经保存了这个cookie，那么通过cookie的setMaxAge( 0)来删除这个Cookie。无论是在浏览器内存中，还是在客户端硬盘上都会删除这个cookie。

```Java
/**
 * Cookie的到期时间
 * 负整数
 * 表示只在浏览器内存中存活，关闭浏览器失效（默认值为-1）
 *
 * 正整数
 * 表示存活指定秒数
 *
 * 0
 * 表示删除cookie
 */
@WebServlet("/ser3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置到期时间为负整数（默认值是-1，表示只在浏览器内存中存活，关闭浏览器失效）
        Cookie cookie = new Cookie("uname1", "zhangsan1");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);

        //设置到期时间为正整数（表示存活指定秒数，会将数据存在硬盘中）
        Cookie cookie2 = new Cookie("uname2", "zhangsan2");
        cookie2.setMaxAge(30);
        resp.addCookie(cookie2);

        //设置到期时间为0（表示删除cookie）
        Cookie cookie3 = new Cookie("uname3", "zhangsan3");
        cookie3.setMaxAge(0);
        resp.addCookie(cookie3);
    }
}

```



------



## Cookie的注意点

- Cookie保存在当前浏览器中。

- 在一般的站点中常常有记住用户名这样一个操作，该操作只是将信息保存在本机上，换台电脑以后这些信息就无效了。而且cookie不能跨浏览器。

- Cookie存在中文问题

  cookie中不能出现中文，如果有中文则通过URLEncoder.encode()来解码，获取时通过URLDecoder.decode()来进行解码。

  

- 同名cookie问题

  如果服务器端发送重复的cookie那么会覆盖原有的cookie。

- 浏览器存放cookie的数量

  不同的浏览器对cookie也有限定，cookie的存储有上限的（大小和数量）。Cookie是存储在客户端（浏览器）的，而且一般有服务器端创建和设定。后期结合session来实现会话跟踪。

------

```Java
/**
 * Cookie的注意点
 * cookie存只在当前浏览器中有效（不跨浏览器和电脑）
 * cookie不能存中文
 * 如果一定要存中文，则需要通过URLEncoder.encode编码，通过URLDecoder.decode解码
 *
 * 如果出现同名的cookie对象，则会覆盖
 *
 * cookie的存储数量是有上限的，不同浏览器的上限不通。cookie存储的大小是有限的，大概在4kb左右
 */
@WebServlet("/ser4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //cookie存中文
        String name = "姓名";
        String value = "张三";

        //将中文通过URLEncoder 进行编码
        name = URLEncoder.encode(name);
        value = URLEncoder.encode(value);

        //创建cookie对象
        Cookie cookie = new Cookie(name,value);

        //响应cookie
        resp.addCookie(cookie);

        //获取cookie时，通过URLEncoder进行解码
        Cookie[] cookies = req.getCookies();
        //判断非空
        if (cookies != null && cookies.length > 0) {
            //遍历
            for (Cookie cookiee : cookies) {
                //解码
                System.out.printf(URLDecoder.decode(cookiee.getName()));
                System.out.println(URLDecoder.decode(cookiee.getValue()));
            }
        }

        //将原来已有的cookie对象重新设置
        Cookie cookie2 = new Cookie("name","zhangsan");
        resp.addCookie(cookie2);

    }
}
```



------



## Cookie的路径

Cookie的setPath设置cookie的路径，这个路径直接决定了服务器的请求是否会从浏览器中加在某些cookie。

当访问的路径中包含了cookie的路径时，则该请求将带上cookie；若访问路径不包含cookie路径，则该请求不会携带该cookie。



```java
/**
 * Cookie的路径问题
 * 当前服务器下任何项目的任意资源都可获取cookie对象
 * 当前项目下的资源可获取cookie对象（默认不设置cookie的path）
 * 指定项目下的资源可获取cookie对象
 * 指定目录下的资源可获取cookie对象
 */
@WebServlet("/ser5")
public class Servlet5 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //当前服务器下任何项目的任意资源都可获取cookie对象
        Cookie cookie1 = new Cookie("cookie1","cookie2");
        //设置路径为"/"，表示在当前服务器下任何项目都可以访问到Cookie对象
        cookie1.setPath("/");
        resp.addCookie(cookie1);

        //当前项目下的资源可获取cookie对象（默认不设置cookie的path）
        Cookie cookie2 = new Cookie("cookie2","cookie2");
        //设置不设置cookie的path或者设置为当前站点名
        cookie2.setPath("/sc04");
        resp.addCookie(cookie2);

        //指定项目下的资源可获取cookie对象
        Cookie cookie3 = new Cookie("cookie3","cookie3");
        //设置指定项目的站点名
        cookie3.setPath("/sr03");
        resp.addCookie(cookie3);

        //指定目录下的资源可获取cookie对象
        Cookie cookie4 = new Cookie("cookie4","cookie4");
        //设置指定项目的站点名
        cookie4.setPath("/sc04/ser3");
        resp.addCookie(cookie4);
        
    }
}
```

