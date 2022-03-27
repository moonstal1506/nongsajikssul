<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">이메일:</label> <input type="email" class="form-control" placeholder="이메일을 입력해주세요" id="email">
		</div>


		<div class="form-group">
			<label for="pwd">비밀번호:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>

		<button type="submit" class="btn btn-success">로그인</button>
	</form>
</div>


<%@ include file="../layout/footer.jsp"%>