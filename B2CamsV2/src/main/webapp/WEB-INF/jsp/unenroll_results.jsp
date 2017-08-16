<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%--@elvariable id="fooList" type="java.util.List"--%>

<<bbNG:genericPage>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar title="Unenrollment Results"></bbNG:pageTitleBar>
	</bbNG:pageHeader>

	<bbNG:actionControlBar>
		<bbNG:actionButton url="fooJPAController" title="Sync CAMS" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Logs" primary="true"/>

	</bbNG:actionControlBar>

		<html>
		<body>
		<a href="CamsController">Back</a><br />
		
		<b> B2Cams Sync Results </b><br/>
		<b>Number of Students to Unenroll:</b> ${syncListSize} <br />
		<b>Student Name - Course to Unenroll from</b>
		<c:forEach items="${syncList}" var="userToRemove">
			<c:out value= "${userToRemove.studentName}" /> - <c:out value= "${userToRemove.studentID}" /> - <c:out value= "${userToRemove.courseName}" /> <br />
		</c:forEach>		
		</body>
		</html>

</bbNG:genericPage>