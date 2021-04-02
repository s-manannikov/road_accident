<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style><%@include file="/style.css"%></style>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<div class="wrapper">
    <div><h1 class="title">Accidents</h1></div>
    <div class="reply">
        <c:forEach items="${accidents}" var="accident">
        <p><div class="post">
            <div class="userName">
                <c:out value="${accident.value.name}"/>
            </div>
            <div class="address">
                <c:out value="${accident.value.address}"/>
            </div>
            <div class="text">
                <c:out value="${accident.value.text}"/>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>