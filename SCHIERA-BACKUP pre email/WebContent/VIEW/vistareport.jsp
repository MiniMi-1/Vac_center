<%@ page contentType="text/xml" %>
<%@ page import="com.schiera.data.Report"%>
<?xml-stylesheet type = "text/xsl" href = "${pageContext.request.contextPath}/xsl/myxsl.xsl"?>

<%Report[] report = (Report[]) request.getAttribute("report"); %>

<report>>
	<numero_risultati>
	<%= report[0].getCount() %>
	</numero_risultati>
	
	<%for(int i=0; i<report.length; i++){ %>
	<vaccino>
		<nome><%= report[i].getNome() %></nome>
		<cognome><%= report[i].getCognome() %></cognome>
		<codice_fiscale><%= report[i].getCod_fiscale() %></codice_fiscale>
		<tipo_vaccino><%= report[i].getTipo_vaccino() %></tipo_vaccino>
	</vaccino>
	<%}%>
</report>