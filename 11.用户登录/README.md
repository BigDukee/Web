# 用户登录

## 1.数据库创建对应用户表 tb_user（MySQL）

## 2.前台页面

​        登录页面  login.jsp
​            用户登录：JS校验
​                登录表单验证
​                1.给登录按钮绑定点击事件(通过id选择器绑定)
​                2.获取用户姓名和密码的值
​                3.判断姓名是否为空
​                    如果姓名为空，提示用户（span标签赋值），并且return
​                4.判断密码是否为空
​                    如果密码为空，提示用户（span标签赋值），并且return
​                5.如果都不为空，则手动提交表单
​        首页      index.jsp

### 3.后台实现

​        登录功能

### 分层思想（解耦：高内聚低耦合）
​    controller层
​        接受请求
​        （调用service层，返回结果）
​        响应结果
​    service层
​        业务逻辑判断
​    mapper
​        接口类
​        mapper.xml      mybatis与数据库的相关操作
​    entity（po，model）
​        JavaBean实体
​    util
​        工具类（通用的方法/类）
​    test
​        测试方法/类