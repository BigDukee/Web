<%--
  Created by IntelliJ IDEA.
  User: BigDuke
  Date: 2020/11/3
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL的使用</title>
    <%--
        JSTL的使用
            1.下载JSTL所需要的jar包（standard.jar 和 jstl.jsr）
            2.在项目的web目录下的WEB-INF中新建lib目录，将jar拷贝进去
            3.选择“FILE”，在选择项目结构（“project structure”）
            4.选择"Modules"，选择右侧的"Dependencies",选择右侧的"+"号，将对应的lib目录加载进来
            5.在需要使用标签库的JSP页面通过taglib标签引入指定库
    --%>
    <%--通过taglib标签引入所需要的标签库--%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <c:if test="${1==1}">
        hello
    </c:if>
</body>
</html>
