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


		<html>
		<body>
		Users in the Blackboard Database:   <br />
		Current student and employee NetID accounts: <br />
		Confirmed applicants NetID Accounts: <br />
		
		
		<bbNG:tabbedPanels collapsible="true">
	<bbNG:tabbedPanel title="New Users: ${numOfNewStudents}">
			<bbNG:inventoryList className="String" collection="${testUserList}" objectVar="user">
				<bbNG:listActionBar>
					<bbNG:listActionItem url="###.jsp" title="Create Account(s)"></bbNG:listActionItem> 
				</bbNG:listActionBar>
				
					<bbNG:listCheckboxElement name="ckbox" value="user"/>
					<bbNG:listElement label="First Name" name="firstName">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Last Name" name="LastName" >
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Net ID Name" name="NetID" isRowHeader="true">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Email Address" name="email">
						${user}
					</bbNG:listElement>
		</bbNG:inventoryList>
	
	
	</bbNG:tabbedPanel>
	<bbNG:tabbedPanel title="Confirmed Applicants ${numOfConfApp}">
			<bbNG:inventoryList className="String" collection="${testUserList}" objectVar="user">
				<bbNG:listActionBar>
					<bbNG:listActionItem url="###.jsp" title="Create Account(s)"></bbNG:listActionItem> 
				</bbNG:listActionBar>
				
					<bbNG:listCheckboxElement name="ckbox" value="user"/>
					<bbNG:listElement label="First Name" name="firstName">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Last Name" name="LastName" >
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Net ID Name" name="NetID" isRowHeader="true">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Email Address" name="email">
						${user}
					</bbNG:listElement>
		</bbNG:inventoryList>

	
	
	</bbNG:tabbedPanel>
	<bbNG:tabbedPanel title="Former Users ${numOfFormerUsers}">
		<bbNG:inventoryList className="String" collection="${testUserList}" objectVar="user">
				<bbNG:listActionBar>
					<bbNG:listActionItem url="###.jsp" title="Delete"></bbNG:listActionItem> 
				</bbNG:listActionBar>
				
					<bbNG:listCheckboxElement name="ckbox" value="user"/>
					<bbNG:listElement label="First Name" name="firstName">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Last Name" name="LastName" >
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Net ID Name" name="NetID" isRowHeader="true">
						${user}
					</bbNG:listElement>
					<bbNG:listElement label="Email Address" name="email">
						${user}
					</bbNG:listElement>
		</bbNG:inventoryList>


	</bbNG:tabbedPanel>
</bbNG:tabbedPanels>
			
			
			
			
			
		
		
		</body>
		</html>

</bbNG:genericPage>