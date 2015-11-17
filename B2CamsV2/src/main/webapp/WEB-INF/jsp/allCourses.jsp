<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="couse" type="blackboard.data.course.Course"--%>
<%--@elvariable id="users" type="java.util.List"--%>

<html>
<body>
<pre>
<h1> List of Courses in Blackboard</h1>
<br/>
<c:forEach items="${courses}" var="c">
	<c:out value= "${c.displayTitle}" /><br />
</c:forEach>

<h1>List of Courses and Users</h1>
<c:forEach items="${courseEnrollmentMap}" var="course">
	Course Id = ${course.key}
	<br />
	Users = 
		<c:forEach items="${course.value}" var="user" varStatus="loop">
			${user} <br /> ${!loop.last ? '' : '' }
		</c:forEach> <br>
	</c:forEach>
	
	<h1> List of courses and users that are in Cams, but NOT in Blackboard</h1>
		<c:forEach items="${courseUsersToEnroll}" var="courseUsersToEnroll"> 
			Course: <c:out value= "${courseUsersToEnroll.courseName}" /><br />
			Student username: <c:out value= "${courseUsersToEnroll.studentID}" /><br />
			Student Name: <c:out value= "${courseUsersToEnroll.studentName}" /><br />
			Course Name: <c:out value= "${courseUsersToEnroll.course}" /><br />
			Instructor Name: <c:out value= "${courseUsersToEnroll.instructor}" /><br />
			faculty ID Name: <c:out value= "${courseUsersToEnroll.facultyID}" /><br />
			Section Name: <c:out value= "${courseUsersToEnroll.section}" /><br />
			Department Name: <c:out value= "${courseUsersToEnroll.department}" /><br />
			term calendar id Name: <c:out value= "${courseUsersToEnroll.termCalendarID}" /><br />
			Grouping Name: <c:out value= "${courseUsersToEnroll.grouping}" /><br />
			Course URL Name: <c:out value= "${courseUsersToEnroll.courseURL}" /><br />
			Course Type Name: <c:out value= "${courseUsersToEnroll.department}" /><br />
			-------------------------------------------------------------------------------------------
		</c:forEach>
</pre>
</body>
</html>