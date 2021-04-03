<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style><%@include file="/style.css"%></style>

<html>
<body>
<div class="wrapper">
    <form action="<c:url value='/save'/>" method="POST">
    <table>
        <tr>
            <td><label for="name">Name:</label></td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td><label for="address">Address:</label></td>
            <td><input type="text" name="address" id="address"></td>
        </tr>
        <tr>
            <td><label for="text">Text:</label></td>
            <td><input type="text" name="text" id="text"></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="Save"></td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>