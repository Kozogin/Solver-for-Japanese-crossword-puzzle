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

<link rel="stylesheet" href="../css/enter.css">
<body>

	<div class="container">
		<div class="child">
			<h1>Japanese crossword puzzle, enter</h1>

			<form:form method="POST" action="${contextPath}/enter_data"
				 class="form-signin">
				 
				<h2 class="form-signin-heading">number of vertical rows</h2>

				<div class="form-group ${status.error ? 'has-error' : ''}">
					<input type="text" name="vertical" class="form-control"
						placeholder="vertical" autofocus="true"></input>
				</div>

				<h2 class="form-signin-heading">number of horizontal rows</h2>

				<div class="form-group ${status.error ? 'has-error' : ''}">
					<input type="text" name="horizontal" class="form-control"
						placeholder="horizontal" autofocus="true"></input>
				</div>
				
				<h2 class="form-signin-heading">OR number crossword in BD</h2>

				<div class="form-group ${status.error ? 'has-error' : ''}">
					<input type="text" name="crossword" class="form-control"
						placeholder="crossword in DB max ${crossword}" autofocus="true"></input>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		
			</form:form>






			<%-- 
			<form:form method="POST" modelAttribute="createEnterDataForm"
				class="form-signin">
				<h4 class="form-signin-heading">
					number of horizontal rows
				</h4>

				<spring:bind path="lineNumbers">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="lineNumbers" class="form-control"
							placeholder="" autofocus="true"></form:input>
						<form:errors path="lineNumbers"></form:errors>
					</div>
				</spring:bind>
				
				
				<h4 class="form-signin-heading">
					number of vertical rows
				</h4>

				<spring:bind path="crossword">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="crossword" class="form-control"
							placeholder="" autofocus="true"></form:input>
						<form:errors path="crossword"></form:errors>
					</div>
				</spring:bind>
				
				
				<h4 class="form-signin-heading">
					OR number crossword in BD
				</h4>

				<spring:bind path="crossword">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:input type="text" path="crossword" class="form-control"
							placeholder="" autofocus="true"></form:input>
						<form:errors path="crossword"></form:errors>
					</div>
				</spring:bind>
				
				

				
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Ok
						</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				

			</form:form>
 --%>






		</div>
	</div>




</body>
</html>
