$(document).ready(function(){
    $("input[name=cartChkBox]").change( function(){
        getOrderTotalPrice();
    });
});

function getOrderTotalPrice(){
    let orderTotalPrice = 0;
    $("input[name=cartChkBox]:checked").each(function() {
        let cartItemId = $(this).val();
        let price = $("#price_" + cartItemId).attr("data-price");
        let count = $("#count_" + cartItemId).val();
        orderTotalPrice += price*count;
    });

    $("#orderTotalPrice").html(orderTotalPrice+'원');
}

function changeCount(obj){
    let count = obj.value;
    let cartItemId = obj.id.split('_')[1];
    let price = $("#price_" + cartItemId).data("price");
    let totalPrice = count*price;
    $("#totalPrice_" + cartItemId).html(totalPrice+"원");
    getOrderTotalPrice();
    updateCartItemCount(cartItemId, count);
}

function checkAll(){
    if($("#checkall").prop("checked")){
        $("input[name=cartChkBox]").prop("checked",true);
    }else{
        $("input[name=cartChkBox]").prop("checked",false);
    }
    getOrderTotalPrice();
}

function updateCartItemCount(cartItemId, count){
    let url = "/cartItem/" + cartItemId+"?count=" + count;

    $.ajax({
        url      : url,
        type     : "PATCH",
        dataType : "json",
        cache   : false,
        success  : function(result, status){
            console.log("cartItem count update success");
        },
        error : function(jqXHR, status, error){
            if(jqXHR.status == '401'){
                alert('로그인 후 이용해주세요');
                location.href='/members/login';
            } else{
                alert(jqXHR.responseJSON.message);
            }
        }
    });
}
