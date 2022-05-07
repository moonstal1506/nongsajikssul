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
			url: "/auth/join",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if (resp.status === 500) {
			    console.log(resp);
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
		let data ={
			id:$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val(),
			crop:$("#crop").val(),
			location:$("#location").val()
		}

		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
		    if (resp.status === 500) {
		    console.log(resp);
        		alert("회원수정에 실패했습니다.");
        	} else {
        		alert("회원수정이 완료되었습니다.");
                location.href = "/";
        	}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();