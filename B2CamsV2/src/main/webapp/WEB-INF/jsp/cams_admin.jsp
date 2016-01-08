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
		<bbNG:actionButton url="fooJPAController" title="Sync CAMS" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Logs" primary="true"/>

	</bbNG:actionControlBar>

		<html>
		<body>
			<h1>Current Settings: </h1><br />
<h3>Username: ${username} <br/>
	<br/>
	Password: ${password} <br/>
	<br/>
	Enabled: ${enabled} <br/>
	<br/>
	Web Address: ${webAddress} <br/>
	<br/>
	Frequency: ${frequency} <br/>
		<br/>
	Year: ${year} <br/>
		<br/>
	Term: ${term} <br/>

	</h3>	
		
			<bbNG:form action="saveProperties" method="POST" nonceId="/saveProperties">
			
			
			
			<bbNG:dataCollection markUnsavedChanges="true" showSubmitButtons="true" hasRequiredFields="true">
			<bbNG:step title="General" instructions="Use these settings to edit the general settings.">
				<bbNG:dataElement isRequired="false" label="Enabled">
					<bbNG:selectElement name="enabled" size="1">
					<bbNG:selectOptionElement value="true" optionLabel="On"/>
					<bbNG:selectOptionElement value="false" optionLabel="Off"/>
					</bbNG:selectElement>
				</bbNG:dataElement>
				
		<bbNG:dataElement label="Syncing Frequency">
        	<bbNG:selectElement name="frequency" size="1" >
  			<bbNG:selectOptionElement value="1" optionLabel="Twice Per Day" />
        	<bbNG:selectOptionElement value="2" optionLabel="Daily" />
        	</bbNG:selectElement>
      </bbNG:dataElement>
      
      <bbNG:dataElement label="Current Term">
        	<bbNG:selectElement name="term" size="1" >
  			<bbNG:selectOptionElement value="fall" optionLabel="Fall" />
        	<bbNG:selectOptionElement value="spring" optionLabel="Spring" />
        	<bbNG:selectOptionElement value="summer1" optionLabel="Summer 1" />
        	<bbNG:selectOptionElement value="summer12" optionLabel="Summer 2" />
        	</bbNG:selectElement>
      </bbNG:dataElement>
      
      <bbNG:dataElement isRequired="false" label="Current Year">
					<bbNG:textElement name="year" value="${year}" size="2" minLength="2"/>
				</bbNG:dataElement>
			
			
			</bbNG:step>
			<bbNG:step title="Connection Credentials" instructions="Use these settings to edit the credentials to connect to the Drupal WS">
				<bbNG:dataElement isRequired="false" label="Blackboard Client Username">
					<bbNG:textElement name="username" value="${username}" size="30" minLength="1"/>
				</bbNG:dataElement>
				<bbNG:dataElement isRequired="false" label="Blackboard Client Password">
					<bbNG:textElement name="password" value="${password}" size="30" minLength="1"/>
				</bbNG:dataElement>
				<bbNG:dataElement isRequired="false" label="AUP Drupal Web Address">
					<bbNG:textElement name="webAddress" value="${webAddress}" size="30" minLength="1"/>
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