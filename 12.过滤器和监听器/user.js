function login() {
    //判断不为空则提交
    var uname = document.getElementById("uname").value;
    if (uname != null && uname.trim() != "") {
        document.getElementById("loginForm").submit();
    }
}