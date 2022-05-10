<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<div class="container " style="width: 800px">
    <div class="d-flex justify-content-end">
        <a href="/">최신순 </a>&nbsp;
        <a href="/popular">인기순 </a>&nbsp;
    </div>
    <div>
        <c:forEach var="board" items="${boards}">
            <div class="card m-2" >
                <div class="card-body ">
                    <h4 class="card-title">${board.title}</h4>
                    <a href="/board/${board.id}" class="btn btn-success ">상세보기</a>
                </div>
            </div>
        </c:forEach>
	</div>
</div>

<%@ include file="layout/footer.jsp"%>