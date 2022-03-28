<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container" style="width: 600px">
	<button class="btn btn-success" onclick="history.back()">돌아가기</button>
	<a href="/board/${board.id }/updateForm" class="btn btn-warning">수정</a>
	<button id="btn-delete" class="btn btn-danger">삭제</button>
	<br /><br />
	<div>
		<h3>${board.title }</h3>
	</div>
	<hr />
	<div>
		<div>${board.content }</div>
	</div>
	<hr />
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>