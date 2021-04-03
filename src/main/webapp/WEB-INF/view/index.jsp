<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style><%@include file="/style.css"%></style>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<div class="wrapper">
    <a href="<c:url value='/create'/>">Add accident</a> |
</div>
<div class="wrapper">
    <div><h1 class="title">Accidents</h1></div>
    <div class="reply">
        <c:forEach items="${accidents}" var="accident">
        <p><div class="post">
            <div class="userName">
                <c:out value="${accident.name}"/>
            </div>
            <div class="address">
                <c:out value="${accident.address}"/>
            </div>
            <div class="address">
                <c:out value="${accident.type.name}"/>
            </div>
            <div class="text">
                <c:out value="${accident.text}"/>
            </div>
            <div class="text">
                <a href="<c:url value='/edit?id=${accident.id}'/>">Edit accident</a>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>