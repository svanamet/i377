<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View</title>
<style type="text/css">
	<%@ include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	<jsp:include page="menu.jsp" />

	<form id="unitForm" method="post" action="view">
		<table class="formTable" id="formTable">

			<tbody>
				<tr>
					<td>Nimi:</td>
					<td><input name="name" id="nameBox" disabled='disabled' value="${name}"/>
					
				</tr>
				<tr>
					<td>Kood:</td>
					<td><input name="code" id="codeBox" disabled='disabled' value="${code}"/></td>
				</tr>
				<tr>
					<td>Ülemüksus:</td>
     					<td><select name="superUnitCode" id="superUnitCode" disabled='disabled'>
     
     						<!-- items="${requestScope['units']}" == items="${units}" -->
<%--      						<c:forEach items="${units}" var="unit"> --%>
        
<%--         						<c:if test="${super_name.name == unit.name}"> --%>
         								<option selected="selected" value="${super_name.code}">${super_name.name}</option>
<%--         						</c:if> --%>
        
<%--        						</c:forEach> --%>

     			</select></td>
			    </tr>
			    <tr>
			     <td>Alamüksused:</td>
			     
			     <td><c:forEach items="${codes}" var="code" varStatus="status">
			       <span id="sub_${code.code}">${code.code}</span>
			       <c:if test="${!status.last}">,</c:if>
			      </c:forEach></td>
					
				</tr>
				<tr>
					<td colspan="2" align="right"><br /> 
					<a href="<c:url value='/search' />" id="backLink">Tagasi</a></td>
				</tr>
			</tbody>
			
		</table>
	</form>
	
</body>
</html>