<%@include file="/views/resource-bundles/directives.jsp"%>
<%@ page import="com.kodecamp.web.util.*"%>
<html>
<head>
<jsp:include page="/views/resource-bundles/styles.jsp"></jsp:include>
<title>View Student Details</title>
</head>
<body>
<jsp:include page="/views/header/header.jsp"></jsp:include>
	<form action="./newStudent"
		method="post">
		
		<%@ include file="/views/resource-bundles/message-panel.jsp"%>
		<input type="hidden" name="studentId" value="${studentModel.id}">
		<input type="hidden" name="action" id="action">

		<div class="container" id="container">
			<h2>Add New Student</h2>

			
			<div class="form">
				<div class="form-row">
					<div class="label">
						<label>Name</label>
					</div>
					<div class="value">
						<input name="name" type="text" value="${name}">
					</div>

				</div>

				<div class="form-row">
					<div class="label"><label>Address</label></div>
					<div class="value">
						<input type="text" name="address"
							value="${address}">
					</div>
				</div>

				<div class="form-row">
					<div class="label"><label>College Name</label></div>
					<div class="value">
						<input name="collegeName" type="text" value="${collegeName }"
							>
					</div>

				</div>
				<div class="button-panel">
				<input type="submit" class="clear-btn" value="Back"
					onclick="submitForm('VIEW_LIST','')" />
					 
				<input type="submit"
					class="edit-btn" value="Add" onclick="submitForm('ADD_NEW_STUDENT','${studentModel.id}')" /> 
				
				<input type="submit" class="delete-btn" value="Clear"
					onclick="submitForm('delete','')" />
			</div>
			</div>

		</div>

	</form>
	<script>
		function submitForm(action) {
			document.querySelector("#action").value = action;
			document.forms[0].submit();
		}
	</script>
</body>
</html>