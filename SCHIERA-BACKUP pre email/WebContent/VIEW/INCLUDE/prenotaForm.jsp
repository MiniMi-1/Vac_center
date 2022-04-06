<!-- 
Inizialmente mostra solo il datepicker con la data, se la data scelta
ha ancora posti liberi allora viene mostrato il form da compilare con
il resto dei dati, tenendo la data come hidden input 
-->


<% 
boolean show_form;

if(request.getAttribute("show_form")!=null){
	show_form=(boolean)request.getAttribute("show_form");
}
else{
	show_form=false;
}
%>

<%if(!show_form){ %>
<form action="/SCHIERA/postiLiberi" method="POST">
	
	<div class="form-group">
	    <label for="data_check">Data vaccino:</label>
    	<input type="text" class="form-control" name="data_check" id="data_check" required="required"></input>
    	
    	<%if(request.getAttribute("no_posti") != null){ %>
    	<p style="color:tomato;">Nessun posto disponibile per la data selezionata</p>
    	<%}%>
    	
    	<button type="submit" class="btn btn-primary">Controlla posti liberi</button>
    	<button type="button" class="btn btn-primary back">Indietro</button>
	</div>
</form>
<%} else{ %>
<form action="/SCHIERA/nuovaPrenotazione" method="POST">

	<div class="form-group">
		
		<label for="data">Data scelta: <%=request.getAttribute("data_check")%></label>
		<input id="data" name="data" type="hidden" value="<%=request.getAttribute("data_check")%>"></input>
		
		<label for="nome">Nome:</label>
    	<input type="text" class="form-control" name="nome" id="nome" placeholder="Nome" required="required"></input>
    	
    	<label for="cognome">Cognome:</label>
    	<input type="text" class="form-control" name="cognome" id="cognome" placeholder="Cognome" required="required"></input>
    	
    	<label for="cod_fiscale">Codice Fiscale:</label>
    	<input type="text" class="form-control" name="cod_fiscale" id="cod_fiscale" placeholder="Codice Fiscale" required="required"></input>
    	
    	<label for="malattie">Segnali eventuali malattie e/o allergie:</label>
    	<textarea class="form-control" name="malattie" id="malattie" rows="4" cols="30"></textarea>
    	
    	<button type="submit" class="btn btn-primary">Prenota</button>
    	<a href="/SCHIERA/cambiaData" class="btn btn-primary back">Indietro</a>
    	
    </div>

</form>

<%}%>

<script type="text/javascript">
	//maxDate 40 giorni per evitare conflitti con turni seconda dose
	$(document).ready(function() {
		$("#data_check").datepicker({
	    	minDate: 0,
	    	maxDate: 40,
	    	dateFormat: "yy-mm-dd"
		});
	});
	
</script>