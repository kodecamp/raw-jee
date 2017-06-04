<%@ include file="/views/resource-bundles/directives.jsp"%>
<%@ page import="com.kodecamp.web.util.*"%>

<html>
<head>
<jsp:include page="/views/resource-bundles/styles.jsp"></jsp:include>

<title>Student List</title>
</head>
<body>

	<jsp:include page="/views/header/header.jsp"></jsp:include>

	<!-- action="./studentList" method="post" -->
	<form action="./studentList" method="post">

		<%@ include file="/views/resource-bundles/message-panel.jsp"%>
		<div class="container" id="container">
			<jsp:include page="/views/header/loading-indicator.jsp"></jsp:include>

			<h2>Students List</h2>
			<div class="table-controls">
				<a href="./studentList?action=VIEW_NEW_STUDENT" id="addLink"
					class="add-link">+ New Student</a>
			</div>
			<div class="table-container-white" id="table-container">
				<table class="datatable" id="datatable">
					<tr>
						<th colspan="5"><lable>Search By College</lable> <input
							name="searchText" type="text" class="search-text"
							value="${searchText}"> <input type="button"
							value="Search" class="search-button"
							onclick="submitForm('SEARCH','');"></th>
					</tr>
					<tr>
						<!-- <th>ID</th> -->
						<th>Name</th>
						<th>Address</th>
						<th>College</th>
						<th>Action(Post)</th>
						<th>Action(Get)</th>
					</tr>
					<c:forEach items="${currentPage.items}" var="student">
						<tr>
							<%-- <td>${student.id}</td> --%>
							<td>${student.name}/${student.id}</td>
							<td>${student.address}</td>
							<td>${student.collegeName}</td>
							<td><input type="button" id="submitBtn"
								value="View Details (Post)"
								onclick="submitForm('VIEW_DETAILS','${student.id}');"></td>
							<td><a class="link"
								href="./studentList?action=VIEW_DETAILS&param=${student.id}">View
									Details (Get) </a></td>
					</c:forEach>
				</table>
				<div class="paginator">

					<c:choose>
						<c:when test="${paginator.previous}">
							<input type="button" value="Previous"
								onclick="submitForm('PREVIOUS_PAGE','');">
						</c:when>
						<c:otherwise>
							<input type="button" value="Previous" disabled>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${paginator.next}">
							<input type="button" value="Next"
								onclick="submitForm('NEXT_PAGE','');">
						</c:when>
						<c:otherwise>
							<input type="button" value="Next" disabled>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<input type="hidden" name="param" id="param"> <input
				type="hidden" name="action" id="action">


		</div>

	</form>
	<script>
		function submitForm(action, param) {
			document.querySelector("#param").value = param;
			document.querySelector("#action").value = action;
			displayLoadingIndicator();
			document.forms[0].submit();
		}

		var container = document.querySelector('#table-container');
		var addLink = document.querySelector('#addLink');

		addLink.addEventListener('mouseenter', function() {
			container.classList.remove('table-container-white');
			container.classList.add('table-container-black');
		});

		addLink.addEventListener('mouseleave', function() {
			container.classList.add('table-container-white');
			container.classList.remove('table-container-black');
		});
	</script>
</body>
</html>