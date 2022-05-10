<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" style="width: 600px">
	<form action="/auth/login" method="post">
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" name="username" class="form-control" placeholder="아이디를 입력해주세요" id="username">
		</div>

		<div class="form-group">
			<label for="pwd">비밀번호</label> <input type="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		
		<button id="btn-login" class="btn btn-success">로그인</button>
		
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=	&redirect_uri=http://localhost:9000/auth/kakao/callback&response_type=code"><img height="38px"
			src="/image/kakao_login_button.png" /></a>
		<button onclick="javascript:location.href='/oauth2/authorization/facebook'" id="btn-login" class="btn btn-primary">페이스북로그인</button>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>