<form action="/SCHIERA/login" method="POST">
	
	<div class="form-group">
	
	<label for="cod_impiegato">Codice impiegato:</label>
    <input type="number"  class="form-control" name="cod_impiegato" id="cod_impiegato" placeholder="Codice Impiegato" required="required"></input>
	
	<label for="nome_i">Nome:</label>
    <input type="text" class="form-control" name="nome_i" id="nome_i" placeholder="Nome" required="required"></input>
    	
   	<label for="cognome_i">Cognome:</label>
   	<input type="text" class="form-control" name="cognome_i" id="cognome_i" placeholder="Cognome" required="required"></input>
	
	
	<button type="submit" class="btn btn-primary">Login</button>
    <button type="button" class="btn btn-primary back">Indietro</button>
    

	</div>

</form>