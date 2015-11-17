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


List of Courses and Students in CAMS: 
<c:forEach items="${helloWS}" var="elem">
    <c:out value="${elem.courseName}"/> <br/>
    Students: 
    <c:forEach items="${elem.courseEnrollment}" var="item" varStatus= "loop">
    	${item.key} , ${item.value.studentName} ${!loop.last ? ',' : ''}
    	<br/>
    </c:forEach><br/>
</c:forEach>
<br/>

List of All Courses and Students in Blackboard: 
<c:forEach items="${bbCourseEnrollmentMap}" var="course">
	Course Id = ${course.key}
	<br />
	Users = 
		<c:forEach items="${course.value}" var="user" varStatus="loop">
			${user} <br /> ${!loop.last ? '' : '' }
		</c:forEach> <br>
	</c:forEach>


List of Students in Cams that are not in blackboard: 



Courses records:<br/>
 <c:forEach items="${camsList}" var="element">
    <c:out value="${element.name}"/> => <c:out value="${element.value}"/><br/>
</c:forEach>

List to use to create batch user upload: <br/>

</body>
</html>