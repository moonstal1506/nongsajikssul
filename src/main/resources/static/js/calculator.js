function calculate(){
    const multiple =$("#multiple").val();
    const water =$("#water").val();
    const result =$("#result").val();

    if(isNaN(water) || isNaN(multiple)) {
        alert("숫자만 입력해주세요.");
    }
    if(multiple != 0){
        $('#result').text((water * 1000) / multiple);
    }

}