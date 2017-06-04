<%@ include file="/views/resource-bundles/directives.jsp"%>
<%@ page import="com.kodecamp.web.util.*" %>

${ServletActions.VIEW_DETAILS_ACTION.value}
	<!-- setting a variable -->
	<c:set var="studentName" value="Shivam Yadav"/> 
	<c:out value="${studentName}">Name Not Present</c:out>
	<c:out value="${dummyStudentName}">Name Not Present</c:out>
	
	<c:if test="${studentName != null }">
		<h1>${studentName}</h1>
	</c:if>
	
	<c:choose>
		<c:when test="${studentName != null}">
			<h3>Student name is ${studentName}</h3>
		</c:when>
		
		<c:otherwise>
			<h3>Otherwise clause</h3>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="1" end="5" step="1">
		<c:out value="${i}"/>
	</c:forEach>
	
	<c:forEach var="headerMap" items="${header}">
		<p>
			<c:out value="${headerMap.key}"/>:<c:out value="${ headerMap.value}"/>
		</p> 
	</c:forEach>
