<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="fooList" type="java.util.List"--%>

<html>
<body>
<!-- <form action="fooJPAController">
	<br/>Name: <input type="text" name="n" value=""/>
	<br/>Value: <input type="text" name="v" value=""/>
	<br/><input type="submit"/>
</form> -->

XML-RPC Web Service Test: <br/>



<c:forEach items="${helloWS}" var="elem">
    <c:out value="${elem.courseName}"/> <br/>
    Students: 
    <c:forEach items="${elem.courseEnrollment}" var="item" varStatus= "loop">
    	${item.key} ${!loop.last ? ',' : ''}
    </c:forEach><br/>
</c:forEach>
<br/>


Courses records:<br/>
 <c:forEach items="${camsList}" var="element">
    <c:out value="${element.name}"/> => <c:out value="${element.value}"/><br/>
</c:forEach>
</body>
</html>