<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Add</title>
<style type="text/css">
	<%@ include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
 <%@ include file="menu.jsp"%>

 <c:url value="/save" var="theAction" />
 <form:form method="post" action="${theAction}" modelAttribute="unitForm">

  <table class="formTable" id="formTable">
   <tbody>
    <tr>
     <td>Nimi:</td>
     <td><form:input id="nameBox" path="unit.name" /></td>
    </tr>
    <tr>
     <td>Kood:</td>
     <td><form:input id="codeBox" path="unit.code" /></td>
    </tr>
    <tr>
     <td>Ülemüksus:</td>
     <td><form:select id="superUnitCode" path="superUnitCode">
       <option value=""> </option>
       <c:forEach var="unit" items="${units}">
        <option value="${unit.code}">${unit.name}</option>
       </c:forEach>       
      </form:select></td>
    </tr>

    <tr>
     <td>Alamüksused:</td>
     <td></td>
    </tr>
    <tr>
     <td class=align colspan="2"><br /> <input type="submit"
      value="Lisa" id="addButton" /></td>
    </tr>
   </tbody>
  </table>
 </form:form>
</body>
</html>