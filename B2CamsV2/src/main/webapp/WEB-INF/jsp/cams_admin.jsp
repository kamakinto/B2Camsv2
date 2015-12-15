<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/bbNG" prefix="bbNG"%>

<%--@elvariable id="fooList" type="java.util.List"--%>

<<bbNG:genericPage>
	<bbNG:pageHeader>
		<bbNG:pageTitleBar title="CAMS Settings"></bbNG:pageTitleBar>
	</bbNG:pageHeader>

	<bbNG:actionControlBar>
		<bbNG:actionButton url="xxx.jsp" title="Sync CAMS" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Logs" primary="true"/>

	</bbNG:actionControlBar>

		<html>
		<body>
			<h1>Current Settings: </h1><br />
Username: ${username} <br/>
<c:forEach items="${settingsList}" var="element">
    frequency: <c:out value="${element.frequency}"/> <br/>
   username: <c:out value="${element.username}"/> <br/>
    password:<c:out value="${element.password}"/> <br/>
    Sync State: <c:out value="${element.syncState}"/> <br/>
    Web Address: <c:out value="${element.webAddress}"/> <br/>

</c:forEach>
		
		
			<bbNG:form action="saveProperties" method="POST">
			<bbNG:dataCollection markUnsavedChanges="true" showSubmitButtons="true" hasRequiredFields="true">
			<bbNG:step title="General" instructions="Use these settings to edit the general settings.">
				<bbNG:dataElement isRequired="false" label="Sync Mode">
					<bbNG:selectElement name="enabled" size="1">
					<bbNG:selectOptionElement value="on" optionLabel="On"/>
					<bbNG:selectOptionElement value="off" optionLabel="Off"/>
					</bbNG:selectElement>
				</bbNG:dataElement>
				
		<bbNG:dataElement label="Frequency">
        	<bbNG:selectElement name="frequency" size="1" >
        	<bbNG:selectOptionElement value="hourly" optionLabel="Hourly" />
        	<bbNG:selectOptionElement value="twice per day" optionLabel="Twice Per Day" />
        	<bbNG:selectOptionElement value="daily" optionLabel="Daily" />
        	</bbNG:selectElement>
      </bbNG:dataElement>
			
			
			</bbNG:step>
			<bbNG:step title="Connection Credentials" instructions="Use these settings to edit the credentials to connect to the Drupal WS">
				<bbNG:dataElement isRequired="false" label="Username">
					<bbNG:textElement name="username" value="sample text for element" size="30" minLength="1"/>
				</bbNG:dataElement>
				<bbNG:dataElement isRequired="false" label="Password">
					<bbNG:textElement name="password" value="" size="30" minLength="1"/>
				</bbNG:dataElement>
				<bbNG:dataElement isRequired="false" label="Web Address">
					<bbNG:textElement name="webAddress" value="" size="30" minLength="1"/>
				</bbNG:dataElement>
			</bbNG:step>
			<bbNG:stepSubmit cancelUrl="${cancelURL}" showCancelButton="true">
			<bbNG:stepSubmitButton label="Submit Changes" />
			</bbNG:stepSubmit>
			</bbNG:dataCollection>
			</bbNG:form>
		
		
		
		
		</body>
		</html>

</bbNG:genericPage>