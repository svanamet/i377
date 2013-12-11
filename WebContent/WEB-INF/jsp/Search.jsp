<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<style type="text/css">
	<%@ include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<form method="get" action="search">
		<input name="searchString" id="searchStringBox" value="${param.searchString}" /> <input
			type="submit" id="filterButton" value="Filtreeri" /> <br /> <br />
		<table class="listTable" id="listTable">
			<thead>
				<tr>
					<th scope="col">Nimi</th>
					<th scope="col">Kood</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="unit" items="${units}">
					<tr>
						<td>
						<div id="row_${unit.code}">
						<a href="view/${unit.code}" id="view_${unit.code}">${unit.name}</a>
						</div>
						</td>
						
						<td>${unit.code}</td>
						<td><a href="<c:url value='/delete/${unit.code}' />" id="delete_${unit.code}">Kustuta</a></td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</form>

</body>
</html>