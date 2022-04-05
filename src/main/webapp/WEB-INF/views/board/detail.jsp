<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container" style="width: 800px">
	<button class="btn btn-success" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id }/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /><br />
	<div>
		글 번호 : <span id="id"><i>${board.id } </i></span> 
		작성자 : <span><i>${board.user.username } </i></span>
	</div>
	<br />
	<div>
		<h3>${board.title }</h3>
	</div>
	<hr />
	<div>
		<div>${board.content }</div>
	</div>
	<hr />
	
	<div class="card">
		  <form>
		  	
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-success">등록</button>
				</div>
		 </form>
	</div>
	
	<br />
	
	<div class="card">
		<div class="card-header">댓글</div>
		<ul id="reply-box" class="list-group">
			
				<li id="reply" class="list-group-item d-flex justify-content-between">
					<div>댓글내용</div>
					<div class="d-flex ">
						<div class="font-italic">작성자 : 문수정 &nbsp;</div>
						
							<button class="badge">삭제</button>
						
					</div>
				</li>
	



		</ul>
	</div>
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>