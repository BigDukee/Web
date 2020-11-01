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





------

## 文件下载

文件下载，即将服务器上的资源下载到本地，有两种方式，一种是通过超链接本身的特性来下载；第二种通过代码下载。

### 超链接下载

当我们在HTML或JSP页面使用a标签的时候，原意是希望能够进行跳转，担当超链接遇到浏览器不是别的资源是会自动下载；当遇见浏览器能够直接显示的资源，浏览器就会默认显示出来，比如txt,png,jpg等。当然我们也可以通过download属性规定浏览器进行下载。但有些浏览器并不支持。



```html
<body>
<!--
    超链接下载
        当使用超链接（a标签）时，如果遇到浏览器能够识别的资源，则会显示内容；如果遇到浏览器不能识别的资源，则会进行下载。
    download属性
        通过download属性规定浏览器进行下载。download属性可以不写任何信息，会自动使用默认文件名。如果设置了download属性的
        值，则使用设置的值作为文件名。
-->
<!--浏览器能够识别的资源-->
<a href="download/新建文本文档.txt">文本文件</a>
<a href="download/123.png">图片文件</a>
<!--浏览器不能识别的资源-->
<a href="download/plaidctf-2013-master.zip">压缩文件</a>
<hr>
<a href="download/新建文本文档.txt" download="">文本文件</a>
<a href="download/123.png" download="这是一张图片.png">图片文件</a>

</body>
```



### 后台实现下载

```java
/**
 * 文件下载
 * 1.需要通过response.setContentType 方法设置Content-type头字段的值，为浏览器无法使用某种方式或激活某个程序来处理的MIME类型，
 * 例如"application/octet-stream"或"application/x-msdownload"等。
 * 2.需要通过response.setHeader方法设置Content-Disposition头的值为"attachment;filename=文件名"
 * 3.读取下载文件，调用response.getOutputStream方法向客户端写入附件内容。
 */
```



------




```Java
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        System.out.println("文件下载");

        //设置请求的编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取参数（得到要下载的文件名）
        String filename = req.getParameter("filename");
        //参数的非空判断   trmi()去除字符串的前后空格
        if (filename == null || "".equals(filename.trim())) {
            resp.getWriter().write("请输入要下载的文件名！");
            resp.getWriter().close();
            return;
        }

        //得到图片存放的路径
        String path = req.getServletContext().getRealPath("/download/");

        //通过路径得到file对象
        File file = new File(path + filename);

        //判断文件对象是否存在，并且是一个标准文件
        if (file.exists() && file.isFile()) {
            //设置响应类型（为浏览器无法使用某种方式或激活某个程序来处理的MIME类型）
            resp.setContentType("application/x-msdownload");
            //设置响应头
            resp.setHeader("Content-Disposition","attachment;filename" + filename);
            //响应文件，得到file文件的输入流
            InputStream in = new FileInputStream(file);
            //得到字节输出流
            ServletOutputStream out = resp.getOutputStream();
            //定义byte数组
            byte[] bytes = new byte[1024];
            //定义长度
            int len = 0;
            //循环输出
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            //关闭资源
            out.close();
            in.close();
        }else {
            resp.getWriter().write("文件不存在，请重试！");
            resp.getWriter().close();
        }
    }
```

