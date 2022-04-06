<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.schiera.data.Prenotazione"%>   
 
<!DOCTYPE html>
<html>
<head>

<jsp:include page="INCLUDE/head.jsp"></jsp:include>
<title>Medico</title>

</head>
<body>
	<%Prenotazione prenotazione =(Prenotazione)session.getAttribute("prenotazione"); %>
	<!-- NAVBAR -->
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	
	<div id="body" class="container">
		
		<div id="coda" class="center">
			<a class="btn btn-primary" id="in_coda" href="/SCHIERA/prelevaCoda">Preleva paziente in coda
			</a>
			
			<%if(request.getAttribute("coda_vuota")!= null){ %>
			<h2>Nessuna persona in coda al momento</h2>
			<%}%>
			
		</div>
		
		<%if(prenotazione != null){ %>
		<div class="center" id="dati_prenotazione">
			<p>Nome: <%=prenotazione.getNome()%></p>
			<p>Cognome: <%=prenotazione.getCognome()%></p>
			<p>Codice Fiscale: <%=prenotazione.getCodice_fiscale()%></p>
			<p>Malattie/Allergie segnalate: <%=prenotazione.getMalattie()%></p>
			
			<%if(prenotazione.isIs_prima_dose()){ %>
			
			<form action="/SCHIERA/vaccina" method="GET">
				<label for="vaccini"> Seleziona tipo vaccino: </label>
				<select required="required" id="vaccini" name="vaccini">
					<option value="pfizer">Pfizer</option>
					<option value="moderna">Moderna</option>
					<option value="astra_zeneca">Astra Zeneca</option>
					<option value="johnson_and_johnson">Johnson and Johnson</option>
				</select>
			
				<button type="submit" class="btn btn-primary">Conferma vaccinazione</button>
				
				<a href="/SCHIERA/rifiutaVaccinazione" class="btn btn-primary">Cancella vaccinazione</a>

			</form>
		
			<%} else { %>
			<p>Vaccino ricevuto prima dose: <%=prenotazione.getTipo_vaccino()%></p>
			<!-- POSSO METTERE LA FUNZIONE SECONDA DOSE IN UNA GET -->
			<a class="btn btn-primary" href="/SCHIERA/vaccina">Conferma seconda dose</a>
			<%}} %>
		</div>
<!-- DEVO CONTROLLARE SE E' PRIMA DOSE, SE IS PRIMA DOSE E' FALSO 		
	 ALLORA SLIDE CON TIPI DI VACCINI, GET DATE PRENOTAZIONE E 	
	 AGGIUNGO I GIORNI DELLA SECONDA DOSE
	 
	 SE SECONDA DOSE STAMPA SOLO TIPO DI VACCINO -->
		
		
	
	</div>
	

	<jsp:include page="INCLUDE/footer.jsp"></jsp:include>

</body>
</html>