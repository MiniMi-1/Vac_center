<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="INCLUDE/head.jsp"></jsp:include>
<title>Impiegato</title>
</head>

<body>
	
	<!-- NAVBAR -->
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	
	<div id=body class="container">
		<div class="centered">
		<form action="/SCHIERA/inCoda" method="POST">
			<div class="form-group">
	
	
			<label for="cod_prenotazione">Id Prenotazione:</label>
    		<input type="number" class="form-control"  name="cod_prenotazione" id="cod_prenotazione" placeholder="Id Prenotazione" required="required"></input>
    
    		<button type="submit" class="btn btn-primary">Check In</button>
    		
    		</div>
    		
		</form>	
		<%if(request.getAttribute("messaggio") != null){ %>
    		<p> <%= request.getAttribute("messaggio") %> </p>
    	<% } %>
		
		<label for="pulisci_coda">PREMERE SOLO ALLA CHIUSURA DELLA STRUTTURA</label>
		<a href="/SCHIERA/pulisciCoda" id="pulisci_coda" type="button" class="btn btn-primary btn-lg btn-block">Pulisci Coda</a>
	</div>
	</div>
	
	
	
	<jsp:include page="INCLUDE/footer.jsp"></jsp:include>
	
</body>
</html>