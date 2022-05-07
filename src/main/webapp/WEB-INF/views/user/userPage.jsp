<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container " style="width: 800px">
	<c:forEach var="board" items="${user.boards}">
		<div class="card m-2" >
			<div class="card-body ">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-success ">상세보기</a>
			</div>
		</div>
	</c:forEach>
</div>

<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${boards.first}">
			<li class="page-item disabled "><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a style="color: #28a745;" class="page-link page-link-success" href="?page=${boards.number-1}">Previous</a></li>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${boards.last}">
			<li class="page-item disabled "><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a style="color: #28a745;" class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:otherwise>
	</c:choose>
</ul>


<%@ include file="../layout/footer.jsp"%>