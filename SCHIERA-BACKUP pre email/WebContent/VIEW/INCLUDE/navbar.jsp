<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand">Centro Vaccini Schiera</a>
	
	
	
	<% if (session.getAttribute("id_medico") == null) { %>
	
  	<a class="nav-link" id="home" href="/SCHIERA/VIEW/index.jsp">Torna alla Home</a>
  	<a class="nav-link" id="login" href="#">Login Impiegato</a>
  	
  	<% } else { %>
  	<a class="nav-link" id="logout" href="/SCHIERA/logout">Logout</a>
  	<% } %>
  	
</nav>
