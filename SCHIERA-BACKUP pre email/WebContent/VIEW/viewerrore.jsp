<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="INCLUDE/head.jsp"></jsp:include>
<title>Errore</title>
</head>
<body>

<!-- NAVBAR -->
	<jsp:include page="INCLUDE/navbar.jsp"></jsp:include>
	
	<p>Errore! <%= request.getAttribute("errore") %> </p>
	
</body>
</html>