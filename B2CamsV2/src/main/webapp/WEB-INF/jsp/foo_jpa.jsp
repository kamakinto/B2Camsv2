<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%--@elvariable id="fooList" type="java.util.List"--%>

<<bbNG:genericPage>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar title="CAMS Administration"></bbNG:pageTitleBar>
	</bbNG:pageHeader>

	<bbNG:actionControlBar>
		<bbNG:actionButton url="fooJPAController" title="Sync CAMS" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Logs" primary="true"/>

	</bbNG:actionControlBar>

		<html>
		<body>
		<a href="CamsController">Back</a><br />
		
		<b> B2Cams Sync Results </b><br/>
		<b> Cams Records:</b> ${CamsRecordsSize} <br />
		<b> Blackboard Course's for the ${term} :</b> ${bbRecordSize}<br />
		<b>Number of records to be synced to Bb:</b> ${syncListSize}<br />
		<br />
		
		
	<b>	List of Courses and Students in CAMS:</b> <br/>
		<c:forEach items="${helloWS}" var="elem">
		   Course Name: <c:out value="${elem.courseName}"/> <br/>
		   Course Number:  <c:out value="${elem.courseNum}"/> <br/>
		   Course Section:  <c:out value="${elem.section}"/> <br/>
		   Course Department: <c:out value="${elem.department}"/> <br/>
		   Course CrossListed ID:  <c:out value="${elem.crossListedID}"/> <br/>
		    Students: <br/>
		    <c:forEach items="${elem.courseEnrollment}" var="item" varStatus= "loop">
		    	${item.key} , ${item.value.studentName} ${!loop.last ? ',' : ''}
		    	<br/>
		    </c:forEach><br/>
		</c:forEach>
		<br/>
		
		<b>List of All Courses and Students in Blackboard for ${term}":</b> <br />
		<c:forEach items="${bbCourseEnrollmentMap}" var="course">
			Course Id = ${course.key.courseId}
			<br />
			Course Name = ${course.key.title} <br />
			Users = <br/>
				<c:forEach items="${course.value}" var="user" varStatus="loop">
					${user.userName} <br /> ${!loop.last ? '' : '' }
				</c:forEach> <br>
			</c:forEach>
		
		
		<b> List of Students in Cams that are not in blackboard: </b><br />
		<c:forEach items="${syncList}" var="courseUsersToEnroll"> 
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
		
		
		
		</body>
		</html>

</bbNG:genericPage>