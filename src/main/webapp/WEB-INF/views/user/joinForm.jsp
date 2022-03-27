<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width:600px">
	<form>
		<div class="form-group">
			<label for="username">닉네임</label> <input type="text" class="form-control" placeholder="닉네임을 입력해주세요" id="username">
		</div>

		<div class="form-group">
			<label for="email">이메일</label> <input type="email" class="form-control" placeholder="이메일을 입력해주세요" id="email">
		</div>

		<div class="form-group">
			<label for="pwd">비밀번호</label> <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="password">
		</div>
	
	<div class="form-group">
			<label for="username">관심작물</label> <input type="text" class="form-control" placeholder="관심작물이 있나요?" id="crop">
		</div>
		<div class="form-group">
			<label for="username">위치</label> <input type="text" class="form-control" placeholder="어디에 사시나요?" id="location">
		</div>
	</form>
	<button id="btn-save" class="btn btn-success">회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>