<%@ taglib uri="/bbNG" prefix="bbNG"%>

<bbNG:genericPage>
<bbNG:pageHeader>
<bbNG:pageTitleBar title="B2Cams Admin Page"></bbNG:pageTitleBar>
</bbNG:pageHeader>


<bbNG:tabView>
	<bbNG:tabViewTab title="Course Management">
	<bbNG:actionControlBar>
		<bbNG:actionButton url="xxx.jsp" title="Sync CAMS" primary="true"/>
		
		Testing the text after the control button 1
		<bbNG:actionButton url="xxx.jsp" title="View Courses" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Create Courses" primary="true"/>
		<bbNG:actionButton url="xxx.jsp" title="Delete Courses" primary="true"/>
	</bbNG:actionControlBar>
	
	
	
	Testing to see where this text shows up. this should be where the instructions, as well as the
	descriptions for each button should live.
	</bbNG:tabViewTab>
	
	
	<bbNG:tabViewTab title="User Management">
	hello users tab
	</bbNG:tabViewTab>
	
	<bbNG:tabViewTab title="General Settings">
	General Settings tab
	</bbNG:tabViewTab>
	
	<bbNG:tabViewTab title="Reports">
	Reports tab
	</bbNG:tabViewTab>
</bbNG:tabView>

<html>
<body>

</body>
</html>



</bbNG:genericPage>





