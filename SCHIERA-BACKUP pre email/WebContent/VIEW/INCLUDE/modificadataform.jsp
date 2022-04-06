<form action="/SCHIERA/modificaData" method="POST">
	
	<div class="form-group">
			
				
		<label for="data">Modifica data:</label>
    	<input type="text" class="form-control" name="data" id="data" required="required"></input>

    	<button type="submit" class="btn btn-primary">Conferma Modifica Prenotazione</button>
    	<button type="button" class="btn btn-primary annulla">Annulla</button>
		
		<%if(request.getAttribute("errore") != null) {%>
			<p style="color:tomato;"> <%=request.getAttribute("errore")%> </p>
		<%}%>
	</div>

</form>


<script type="text/javascript">
	
	$(document).ready(function() {
		$("#data").datepicker({
	    	minDate: 1,
	    	maxDate: 40,
	    	dateFormat: "yy-mm-dd"
		});			
	});
</script>