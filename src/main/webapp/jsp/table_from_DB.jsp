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
			<h1>DB Japanese crossword puzzle, enter</h1>			
			
			<form:form method="POST" action="${contextPath}/table_from_DB"
			 class="form-signin">
				
				<h2>vertical row ${vertical}</h2>
				<c:forEach items="${vertic}" var="currentCrossword" >
					
							<input name="vertical_row" type="text"
								value="${currentCrossword.lineNumbers}" > <br><br>					
					
				</c:forEach>
				
				<h2>horizontal column ${horizontal}</h2>
				<c:forEach items="${horiz}" var="currentCrossword">
					
							<input name="horizontal_column" type="text"
								value="${currentCrossword.lineNumbers}"/> <br><br>					
					
				</c:forEach>
				
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</form:form>			

		<br><br><br><br>
		</div>
		
	</div>




</body>
</html>
