
<!--  message panel -->
<c:if test="${not empty message }">
	<div class="message-container"
		id="message-container">
		<div class="panel-header">
			<h4 class="message-title">${message.severity}</h4>
			<!-- <input type="button" class="panel-close-btn" value="X" onclick="closeModal()"> -->
			<img id="panel-close-btn" src="./images/header-close-red.svg" onhover="changeImage()" onclick="closeModal()" class="img-btn-close">
		</div>
		<div class="message-info ${message.severity}">
			<p class="">${message.message}</p>
		</div>
	</div>
</c:if>
<script>

	function changeImage(){
		document.querySelector("#panel-close-btn").src = "./images/header-close-red.svg";	
	}
	
	function closeModal() {
		document.querySelector("#message-container").classList
				.toggle("hide-message-container");
	}
	
</script>