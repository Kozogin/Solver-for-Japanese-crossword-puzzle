<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!DOCTYPE html>
<html lang="en">
<head>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../css/enter_table.css">
<body>

	<div class="container">
		<div class="child">
			<h1>Japanese crossword puzzle, enter</h1>			
			
			<form:form method="POST" action="${contextPath}/form_table"
			 class="form-signin">
				
				<h2>vertical row ${vertical}</h2>
				<c:forEach var="i" begin="1" end="${vertical}">
					
							<input name="vertical_row" type="text"
								value="" placeholder="vertical row <c:out value="${i}" />"> <br><br>					
					
				</c:forEach>
				
				<h2>horizontal column ${horizontal}</h2>
				<c:forEach var="i" begin="1" end="${horizontal}">
					
							<input name="horizontal_column" type="text"
								value="" placeholder="horizontal column <c:out value="${i}" />"> <br><br>					
					
				</c:forEach>
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			
			</form:form>
			<br><br><br><br>
		</div>
		
	</div>

</body>
</html>
