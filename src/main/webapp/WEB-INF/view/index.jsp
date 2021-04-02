<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>

<table class="container">
<c:forEach items="${items}" var="item">
    <tr><td><c:out value="${item}"/></td></tr>
</c:forEach>
</table>

</body>
</html>