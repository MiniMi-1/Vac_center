
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="INCLUDE/head.jsp"></jsp:include>

<title>Index</title>
</head>



<body>

	<!-- NAVBAR -->
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	
	<br /><br /><br /><br /><br /><br /><br /><br /><br />
	
	<div class="container" id="body">
		
		<div class="container" id="buttons">
		<!-- qui va tasto prenota e gestione prenotazione -->
			<button id="prenotabtn" type="button" class="btn btn-primary btn-lg btn-block">    Prenota vaccino   </button>
			<button id="gestionebtn" type="button" class="btn btn-primary btn-lg btn-block">Gestisci prenotazione</button>
		</div>	
	
		<!-- FORM DI PRENOTAZIONE -->
		<div id="prntform" style="display: none">
			<jsp:include page="INCLUDE/prenotaForm.jsp"></jsp:include>
		</div>
		
		<!-- FORM DI GESTIONE -->
		<div id="gstform" style="display: none">
			<jsp:include page="INCLUDE/gestionePrenotazioneForm.jsp"></jsp:include>
		</div>
		
		<!-- FORM LOGIN -->
		<div id="loginform" style="display: none">
			<jsp:include page="INCLUDE/login.jsp"></jsp:include>
		</div>
	
	
	</div>
	
	<jsp:include page="INCLUDE/footer.jsp"></jsp:include>
	
</body>

<script type="text/javascript">
	$("#prenotabtn").on("click", function(){
		$("#buttons").hide();
		$("br").hide();
		$("#prntform").show();
	});
	
	$("#gestionebtn").on("click", function(){
		$("#buttons").hide();
		$("br").hide();
		$("#gstform").show();
	});
	
	$(".back").on("click", function(){
		$("#prntform").hide();
		$("#gstform").hide();
		$("#loginform").hide()
		$("#buttons").show();
		$("br").show();
		
	});
	
	$("#login").on("click", function(){
		$("#buttons").hide();
		$("br").hide();
		$("#loginform").show();
	});
	
	<%if(request.getAttribute("sto_prenotando")!=null){ %>
		$(document).ready(function(){
			$("#buttons").hide();
			$("br").hide();
			$("#prntform").show();
		});
	<%}%>
	
	
</script>
</html>