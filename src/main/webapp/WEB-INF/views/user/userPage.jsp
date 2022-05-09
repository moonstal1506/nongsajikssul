<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container " style="width: 800px">

    <div>
		<h2 class="d-flex">${dto.user.username}님의 글<h2>
	</div>
	<div>
	    게시물 : <span>${dto.boardCount}</span> &nbsp;
    	<a href="javascript:subscribedInfoModalOpen(${dto.user.id });">
    	    구독자 : <span>${dto.subscribedCount}</span> &nbsp;
    	</a>
    	<a href="javascript:subscribeInfoModalOpen(${dto.user.id });">
    	    구독중 : <span>${dto.subscribeCount}</span> &nbsp;
    	</a>
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

<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">구독정보</h4>
        <button type="button" onclick="deleteList()" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" onclick="deleteList()" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script src="/js/userPage.js"></script>
<%@ include file="../layout/footer.jsp"%>