<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<bbNG:genericPage>
<bbNG:pageHeader>
<bbNG:pageTitleBar title="B2Cams User Admin Page"></bbNG:pageTitleBar>
</bbNG:pageHeader>

<html>
<body>


Check to see if the user was created. 

<b>List of new users:</b> <br />




<c:forEach items="${newUsers}" var="c">
	username: <c:out value= "${c.netid}" /><br />
	Role: <c:out value= "${c.role}" /><br />
	First Name: <c:out value= "${c.firstname}" /><br />
	Last name: <c:out value= "${c.lastname}" /><br />
	
	<hr />
</c:forEach>






</body>
</html>



</bbNG:genericPage>
