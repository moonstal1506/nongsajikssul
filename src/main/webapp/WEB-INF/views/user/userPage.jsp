<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container " style="width: 800px">

    <div>
		<h2 class="d-flex">${dto.user.username}님의 글<h2>
	</div>
	<div>
	    게시물 : <span>${dto.boardCount}</span> &nbsp;
    	구독자 : <span>${dto.subscribedCount}</span> &nbsp;
    	구독중 : <span>${dto.subscribeCount}</span> &nbsp;
    	<c:choose>
        	<c:when test="${dto.pageOwnerState}">
        	</c:when>
        	<c:otherwise>
    	        <c:choose>
                    <c:when test="${dto.subscribeState}">
                        <button class="badge" onclick="toggleSubscribe(${dto.user.id},this)">구독취소</button>
                    </c:when>
                    <c:otherwise>
                        <button class="badge" onclick="toggleSubscribe(${dto.user.id},this)">구독하기</button>
                      </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>

	<hr />

	<c:forEach var="board" items="${dto.user.boards}">
		<div class="card m-2" >
			<div class="card-body ">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-success ">상세보기</a>
			</div>
		</div>
	</c:forEach>
</div>

<script src="/js/userPage.js"></script>
<%@ include file="../layout/footer.jsp"%>