<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="INCLUDE/head.jsp"></jsp:include>
<title>ASP</title>

</head>
<body>


	
	<!-- NAVBAR -->
	
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	
	<div id=body class="container">
	<div class="col-3"></div>
	<div class ="col-6">
	<button id="nuovo_medico" type="button" class="btn btn-primary btn-lg btn-block">Assegna nuovo medico</button>
	<div id="form_medico" style="display: none">
		<jsp:include page="INCLUDE/nuovo_medico.jsp"></jsp:include>
	</div>
	
	<%if(request.getAttribute("cod_medico") != null) {%>
	<h3>Ultimo medico registrato:</h3>
	<br></br>
	<p>Codice medico: <%=request.getAttribute("cod_medico") %></p>
	<p>Nome: <%=request.getAttribute("nome") %></p>
	<p>Cognome: <%=request.getAttribute("cognome") %></p>
	<%}%>
	</div>
	
	
	<form action="/SCHIERA/stampaReport" method="POST" target="_blank">
		<div class="form-group">
			<label for="report"> Seleziona tipo report: </label>
			<select required="required" id="report" name="report">
				<option value="1">Vaccini effettuati (include anche solo prima dose)</option>
				<option value="2">Vaccini completi (seconda dose o j&amp;j)</option>
			</select>
			<button id="report" type="submit" class="btn btn-primary btn-lg btn-block">Visualizza XML report</button>
		</div>
	</form>
	<%if(request.getAttribute("errore")!=null){%>
	<p>Errore <%=request.getAttribute("errore") %></p>
	<%}%>
	
</div>
<jsp:include page="INCLUDE/footer.jsp"></jsp:include>
</body>
<script>

$(".back").on("click", function(){
	
	$("#report").show();
	$("#nuovo_medico").show();
	$("#form_medico").hide();
	
});

$("#nuovo_medico").on("click", function(){

	$("#report").hide();
	$("#nuovo_medico").hide();
	$("#form_medico").show();

});


</script>
	
</html>