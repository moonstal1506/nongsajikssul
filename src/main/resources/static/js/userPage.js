function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "구독취소") {
		$.ajax({
			type: "delete",
			url: "/api/subscribe/" + toUserId,
			dataType: "json"
		}).done(res => {
			$(obj).text("구독하기");
		}).fail(error => {
			alert(error.responseJSON.message);
		});
	} else {
		$.ajax({
			type: "post",
			url: "/api/subscribe/" + toUserId,
			dataType: "json"
		}).done(res => {
			$(obj).text("구독취소");
		}).fail(error => {
			alert(error.responseJSON.message);
		});
	}
}

function subscribeInfoModalOpen(pageUserId) {
	$('#myModal').modal('show');
	$.ajax({
		url: `/api/user/${pageUserId}/subscribe`,
		dataType: "json"
	}).done(res => {
		console.log(res.data);
		res.data.forEach((dto) => {
			let item = getSubscribeModalItem(dto);
			$(".modal-body").append(item);
		});
	}).fail(error => {
		console.log("구독정보 불러오기 오류", error);
	});
}

function subscribedInfoModalOpen(pageUserId) {
	$('#myModal').modal('show');
	$.ajax({
		url: `/api/user/${pageUserId}/subscribed`,
		dataType: "json"
	}).done(res => {
		console.log(res.data);
		res.data.forEach((dto) => {
			let item = getSubscribeModalItem(dto);
			$(".modal-body").append(item);
		});
	}).fail(error => {
		console.log("구독정보 불러오기 오류", error);
	});
}

function getSubscribeModalItem(dto) {
	let item = `<a href="/user/${dto.id}">
	            <h4 style="display:inline;">${dto.username}</h4></a> &nbsp`;
	if (!dto.equalUserState) {
		if (dto.subscribeState) {
			item += `<button class="badge" onclick="toggleSubscribe(${dto.id},this)">구독취소</button>`;
		} else {
			item += `<button class="badge" onclick="toggleSubscribe(${dto.id},this)">구독하기</button>`;
		}
	}
	item += `<br/>`;
	return item;
}

function deleteList(){
    $(".modal-body").empty();
}