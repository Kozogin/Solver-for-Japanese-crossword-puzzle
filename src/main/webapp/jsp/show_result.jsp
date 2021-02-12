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

<link rel="stylesheet" href="../css/show_result.css">
<body>

	<div class="container">
		<div class="child">
			<h1>Japanese crossword puzzle, show</h1>

			<form:form method="GET" action="${contextPath}/show_result"
				class="form-signin">

				<table class="crossword">
					<tbody>

						<c:if test="${not empty showCrossword}">

							<c:forEach items="${showCrossword}" var="showCrossword_row">
								<tr>
									<c:forEach items="${showCrossword_row}"
										var="showCrossword_pixel">

										<c:if test="${showCrossword_pixel == 0}">
											<td class="light"></td>
										</c:if>

										<c:if test="${showCrossword_pixel == 1}">
											<td class="dark"></td>
										</c:if>

										<c:if test="${showCrossword_pixel == 2}">
											<td class="blue"></td>
										</c:if>

									</c:forEach>
								</tr>
							</c:forEach>
						</c:if>

					</tbody>
				</table>


				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</form:form>

		</div>
	</div>

</body>
</html>
