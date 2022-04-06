<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
		<html>
			<body>
			
				<h2>Numero risultati: <xsl:value-of select="/report/numero_risultati"/></h2>
					
				<h2>Vaccinati</h2>
			
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Nome</th>
						<th>Cognome</th>
						<th>Codice Fiscale</th>
						<th>Vaccino</th>
					</tr>
					<xsl:for-each select="report/vaccino">
						<tr>
							<td>
								<xsl:value-of select="nome"/>
							</td>
							<td>
								<xsl:value-of select="cognome"/>
							</td>
							<td>
								<xsl:value-of select="codice_fiscale"/>	
							</td>
							<td>
								<xsl:value-of select="tipo_vaccino"/>	
							</td>
						</tr>
					</xsl:for-each>
				</table>
			
			</body>
		</html>
		
	</xsl:template>
</xsl:stylesheet>