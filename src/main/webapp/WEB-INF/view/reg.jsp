<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/style.css"%></style>

<html>
<body>
<div class="wrapper">
    <a href="<c:url value='/'/>">Main</a> | <a href="<c:url value='/create'/>">Add accident</a> | <a href="<c:url value='/login'/>">Login</a>
</div>
<div class="wrapper">
    <div><h1 class="title">Register</h1></div>
    <c:if test="${not empty errorMessage}">
        <div style="color:red; font-weight: bold; margin: 30px 0;">
                ${errorMessage}
        </div>
    </c:if>
    <form name='login' action="<c:url value='/reg'/>" method="post">
        <table>
            <tr>
                <td><label class="form" for="username"> UserName:</label></td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td><label class="form" for="password"> Password:</label></td>
                <td><input type="password" id="password" name="password"/></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="submit"/></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
</body>
</html>