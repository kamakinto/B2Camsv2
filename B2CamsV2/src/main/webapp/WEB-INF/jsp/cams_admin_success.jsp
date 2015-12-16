<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page import="blackboard.base.FormattedText,
				 blackboard.platform.plugin.PlugInUtil,
				blackboard.plugin.springdemo.model.Properties,
				blackboard.plugin.springdemo.dao.CamsPropertiesDAO"
				
%>


<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%--@elvariable id="fooList" type="java.util.List"--%>

<<bbNG:genericPage 
	ctxId="ctx">
	<bbNG:pageHeader>
		<bbNG:pageTitleBar title="CAMS Settings"></bbNG:pageTitleBar>
	</bbNG:pageHeader>

	<bbNG:actionControlBar>
		<bbNG:actionButton url="xxx.jsp" title="Sync CAMS" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Logs" primary="true"/>

	</bbNG:actionControlBar>

		<html>
		<body>
			<h1> Thank you ${user} Settings Saved Successfully! </h1><br />
	
		
		</body>
		</html>

</bbNG:genericPage>