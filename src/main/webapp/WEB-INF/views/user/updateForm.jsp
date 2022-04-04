<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width: 600px">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" value="${principal.user.username }" class="form-control" placeholder="아이디를 입력해주세요" id="username" readonly>
		</div>

		<div class="form-group">
			<label for="email">이메일</label> <input type="email" value="${principal.user.email}" class="form-control" placeholder="이메일을 입력해주세요" id="email">
		</div>
		<c:if test="${empty principal.user.oauth }">
			<div class="form-group">
				<label for="pwd">비밀번호</label> <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="username">관심작물</label> <input type="text" value="${principal.user.crop}" class="form-control" placeholder="관심작물이 있나요?" id="crop">
		</div>
		<div class="form-group">
			<label for="username">위치</label> <input type="text" value="${principal.user.location}" class="form-control" placeholder="어디에 사시나요?" id="location">
		</div>
	</form>
	<button id="btn-update" class="btn btn-success">회원수정</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>