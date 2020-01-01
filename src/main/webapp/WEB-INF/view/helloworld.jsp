<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("utf-8");

    Object name = request.getAttribute("name");
    if (name == null) name = "Everyone";
    String displayName = String.valueOf(name);
%>

<html>
<title>HelloWorld</title>
<body>
<h1>HelloWorld <%=name%>!!</h1>
</body>
</html>
