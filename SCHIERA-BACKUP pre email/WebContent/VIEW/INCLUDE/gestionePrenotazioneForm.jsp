<form action="/SCHIERA/cercaPrenotazione" method="POST">
	
	<div class="form-group">
	
	<label for="cod_prenotazione">Codice Prenotazione:</label>
    <input type="number" class="form-control" min="1" max="32767" name="cod_prenotazione" id="cod_prenotazione" placeholder="Codice Prenotazione" required="required"></input>
    
    <label for="cod_fiscale">Codice Fiscale:</label>
    <input type="text" class="form-control" name="cod_fiscale" id="cod_fiscale" placeholder="Codice Fiscale" required="required"></input>
	
	<button type="submit" class="btn btn-primary">Cerca Prenotazione</button>
    <button type="button" class="btn btn-primary back">Indietro</button>

    </div>

</form>