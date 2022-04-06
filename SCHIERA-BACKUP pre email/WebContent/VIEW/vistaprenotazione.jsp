<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.schiera.data.Prenotazione"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="INCLUDE/head.jsp"></jsp:include>
<title>Gestisci Prenotazione</title>

</head>
<body>
	
	<% Prenotazione prenotazione = (Prenotazione)session.getAttribute("prenotazione");%>
	
	<!-- NAVBAR -->
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	<div class="container">
		
		<div class="col-4"></div>
		<div id="dati" class="col-8">
			<div class=row>
				<p>Codice prenotazione: <%=prenotazione.getCod_prenotazione()%></p>
				<p>Prenotazione di <%=prenotazione.getNome()%> <%=prenotazione.getCognome()%></p>
				<p>Data vaccino: <%=prenotazione.getData()%></p>
				
				<%if(prenotazione.getData_prima_dose() != null) {%>
				<p>Data prima dose: <%=prenotazione.getData_prima_dose()%></p>
				
				<%} if(prenotazione.getTipo_vaccino() != null) {%>
				<p>Vaccino ricevuto: <%=prenotazione.getTipo_vaccino() %></p>
				
				<%}if(request.getAttribute("data_modificata") != null){%>
    				<p style="background-color: #7FFFD4"> Data modificata con successo </p>
    			<% } %>
			
			</div>
	
		
			<div id="form_mod" class="row" style="display: none">
			
				<jsp:include page="INCLUDE/modificadataform.jsp"></jsp:include>
		
			</div>
			<div class="row">
		
				<%if(prenotazione.getData_prima_dose() == null || prenotazione.isIs_rifiutata()){ %>
				<div  id="buttons">
			
				<button type="button" id="modifica" class="btn btn-primary btn-lg">Modifica Prenotazione</button>
				<button type="button" id="cancella" class="btn btn-secondary btn-lg">Cancella Prenotazione</button>
			
				<a href="/SCHIERA/cancellaPrenotazione" type="button" id="conferma" class="btn btn-secondary btn-lg" style="display: none">Conferma Cancellazione</a>
				<button type="button" id="annulla" class="btn btn-secondary btn-lg" style="display: none">Annulla</button>
			
				</div>
				<%} else{ %>
				<p id="message">Non è più possibile modificare/cancellare la prenotazione</p>
				<%} %>
			</div>
	</div>
	
	</div>
</body>

<script type="text/javascript">

$("#modifica").on("click", function(){
	$("#buttons").hide();
	$("#form_mod").show();
});

$(".annulla").on("click", function(){
	$("#form_mod").hide();
	$("#buttons").show();
});

$("#cancella").on("click", function(){
	$("#cancella").hide();
	$("#conferma").show();
	$("#annulla").show();
});

$("#annulla").on("click", function(){
	$("#conferma").hide();
	$("#annulla").hide();
	$("#cancella").show();
});


</script>


</html>