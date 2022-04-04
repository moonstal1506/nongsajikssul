let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click",()=>{
			this.update();
		});

	},

	save: function() {

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
			crop: $("#crop").val(),
			location: $("#location").val(),
		}

		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status === 500) {
				alert("회원가입에 실패했습니다.");
			} else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
		update:function(){
		//alert('user의 save함수 호출됨');
		let data ={
			id:$("#id").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			crop:$("#crop").val(),
			location:$("#location").val()
		}
		//console.log(data);
		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data), //http body데이터
			contentType:"application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType:"json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든것이 문자열(생긴게 json이라면)=>자바스크립트 오브젝트로 변경
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	}

	
}

index.init();