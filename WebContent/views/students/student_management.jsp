<%@include file="/views/resource-bundles/directives.jsp"%>
<%@ page import="com.kodecamp.web.util.*" %>
<html>
<head>
<jsp:include page="/views/resource-bundles/styles.jsp"></jsp:include>
<title>Student Management Details</title>
</head>
<body>
<jsp:include page="/views/header/header.jsp"></jsp:include>
	<form action="./studentManagement" method="post">
		
		<%@ include file="/views/resource-bundles/message-panel.jsp"%>
		<input type="hidden" name="studentId" value="${studentModel.id}">
		<input type="hidden" name="action" id="action">
		<input type="hidden" name="param" id="param">
		<div class="container" id="container">
		
			<h2>Student Details</h2>
			<div class="form">
				<div class="form-row">
					<div class="label">
						<label>Name</label>
					</div>
					<div class="value">
						<input type="text" value="${studentModel.name}"
							disabled>
					</div>
				</div>

				<div class="form-row">
					<div class="label"><label>Address</label></div>
					<div class="value">
						<input type="text" name="newAddress"
							value="${studentModel.address}">
					</div>
				</div>

				<div class="form-row">
					<div class="label"><label>College Name</label></div>
					<div class="value">
						<input type="text" value="${studentModel.collegeName }"
							disabled>
					</div>

				</div>
				<div class="button-panel">
				<input type="submit" class="clear-btn" value="Back"
					onclick="submitForm('VIEW_LIST','')" />
					 
				<input type="submit"
					class="edit-btn" value="Update" onclick="submitForm('UPDATE_DETAILS','${studentModel.id}')" /> 
				
				<input type="submit" class="delete-btn" value="Delete"
					onclick="submitForm('delete','')" />
			</div>
			</div>

		</div>

	</form>
	<script>
		function submitForm(action,param) {
			document.querySelector("#action").value = action;
			
			document.querySelector("#param").value = param;
			/* alert(action +"  " + param); */
			document.forms[0].submit();
		}
	</script>
</body>
</html>