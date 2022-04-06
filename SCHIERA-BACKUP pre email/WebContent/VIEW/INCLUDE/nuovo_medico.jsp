
<form action="/SCHIERA/nuovoMedico" method="POST">
	
	<div class="form-group">
	
<!-- genera un codice casuale per id ??? -->
		<label for="nome">Nome:</label>
    	<input type="text" class="form-control" name="nome" id="nome" placeholder="Nome" required="required"></input>
    	
    	<label for="cognome">Cognome:</label>
    	<input type="text" class="form-control" name="cognome" id="cognome" placeholder="Cognome" required="required"></input>
	
		<button type="submit" class="btn btn-primary">Registra</button>
    	<button type="button" class="btn btn-primary back">Indietro</button>
		
	</div>

</form>
