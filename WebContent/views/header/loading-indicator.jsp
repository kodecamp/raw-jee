<!-- <div class="loading-container" id="loading-container">
	<div id="loading">
		<img id="loading-image" src="./images/loading.gif" alt="Loading..." /><br />
	</div>
</div> -->

<div id="loading-container">

	<div id="preloader_1">
		<span></span> <span></span> <span></span> <span></span> <span></span>
	</div>
	
</div>
<script type="text/javascript">
	function displayLoadingIndicator() {
		document.getElementById("loading-container").style.display = "block";
	}

	window.onload = function() {
		document.getElementById("loading-container").style.display = "none";
	};
</script>