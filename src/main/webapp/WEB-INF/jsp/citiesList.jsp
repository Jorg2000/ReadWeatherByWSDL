<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 29.06.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="include.jsp"%>
<html>
<head>
    <title>Enter Country Name</title>
</head>

<form action="/w/gw" method="post">

    <select name = 'city'>
          <c:forEach  items="${cities}" var="c">
             <option value = '${c}'> ${c}</option> >
          </c:forEach>
    </select>
    <input type="hidden" name="country" value = ${country}  >
    <input type="submit" value="Select city">
</form>



</body>
</html>
