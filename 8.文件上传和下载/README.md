# 文件上传和下载



## 文件上传



### 前台页面

在做文件上传的时候，会有一个上传文件的界面，首先我们需要一个表单，并且表单的请求方式为POST；其次我们的form表单的enctype必须设为“multiparty/form-data”，即**enctype=“multiparty/form-data**”，意思是设置表单的类型为文件上传表单。默认情况下这个表单的类型是“application/x-www-form-urlencoded”，不能用于文件上传。只有使用了multiparty/form-data才能完整的传递文件数据。

```html
<body>
    <!--
        文件上传
        1.准备表单
        2.设置表单的提交类型为POST    method="POST"
        3.设置表单类型为文件上传表单     enctype="multipart/form-data"
        4.设置文件提交的地址
        5.准备表单元素
            1.普通的表单项    type="text"
            2.文件项   type="file"
        6.设置表单元素的name属性值（表单提交一定要设置表单元素的name属性值，否则后台无法接受数据！）

    -->
    <form method="post" enctype="multipart/form-data" action="uploadServlet">
        姓名：<input type="text" name="uname"> <br>
        文件：<input type="file" name="myfile"> <br>
        <!--button默认的类型是提交类型 type="submit"-->
        <button>提交</button>
    </form>

</body>
```

------



### 后台页面

使用注解@MultipartConfig将一个Servlet标识为支持文件上传。Servlet将multiparty/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。 

```java
/**
 * 文件上传
 * 使用注解@MultipartConfig将一个Servlet标识为支持文件上传。
 * Servlet将multiparty/form-data的POST请求封装成Part对象，通过Part对上传的文件进行操作。
 */
@WebServlet("/uploadServlet")
@MultipartConfig
public class uploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        System.out.println("文件上传");

        //设置请求的编码格式
        req.setCharacterEncoding("UTF-8");

        //获取普通表单项（获取参数）
        String uname = req.getParameter("uname");    //表单中表单元素的uname属性值
        System.out.println("uname: " + uname);

        //获取part对象（Servlet将multiparty/form-data的POST请求封装成Part对象）
        Part part = req.getPart("myfile");      //表单中文件域的name属性值

        //通过part对象得到上传的文件名
        String filename = part.getSubmittedFileName();
        System.out.println("上传文件名：" + filename);

        //得到文件存放的路径
        String filePath = req.getServletContext().getRealPath("/");
        System.out.println("文件存放的路径：" + filePath);

        //上传文件到指定目录
        part.write(filePath + "/" + filename);
    }
```



