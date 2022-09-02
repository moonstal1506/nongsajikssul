let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },

    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        })
            .done(function (resp) {
                alert("글쓰기가 완료되었습니다.");
                console.log(resp);
                location.href = "/";
            })
            .fail(function (error) {
                alert(error);
            });
    },

    deleteById: function () {
        let id = $("#id").text();
        console.log(id);
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json",
        })
            .done(function (resp) {
                alert("삭제가 완료되었습니다.");
                location.href = "/";
            })
            .fail(function (error) {
                alert(error);
            });
    },

    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        })
            .done(function (resp) {
                alert("글수정이 완료되었습니다.");
                location.href = "/";
            })
            .fail(function (error) {
                alert(error);
            });
    },

    replySave: function () {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        })
            .done(function (resp) {
                alert("댓글작성이 완료되었습니다.");
                location.href = `/board/${data.boardId}`;
            })
            .fail(function (error) {
                if (error.responseJSON.data === undefined) {
                    alert(error.responseJSON.message);
                } else {
                    alert(JSON.stringify(error.responseJSON.data));
                }
            });
    },

    replyDelete: function (boardId, replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json",
        })
            .done(function (resp) {
                alert("댓글삭제 성공");
                location.href = `/board/${boardId}`;
            })
            .fail(function (error) {
                alert(error);
            });
    },
};

function toggleLike(boardId) {
    console.log("ok")
    let likeIcon = $('.fa-heart');
    if (likeIcon.hasClass("far")) {
        $.ajax({
                type: "post",
                url: `/board/${boardId}/likes`,
                dataType: "json"
            }
        ).done(res => {
            let likeCountStr = $('#likeCount').text();
            let likeCount = Number(likeCountStr) + 1;
            $('#likeCount').text(likeCount);
            likeIcon.addClass("fas");
            likeIcon.removeClass("far");
        }).fail(error => {
            console.log("오류", error);
        });
    } else {
        $.ajax({
                type: "delete",
                url: `/board/${boardId}/likes`,
                dataType: "json"
            }
        ).done(res => {
            let likeCountStr = $('#likeCount').text();
            let likeCount = Number(likeCountStr) - 1;
            $('#likeCount').text(likeCount);
            likeIcon.removeClass("fas");
            likeIcon.addClass("far");
        }).fail(error => {
            console.log("오류", error);
        });
    }
}

index.init();