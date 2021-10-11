/**
 * 
 */
$(document).on('click', '#btn_update', function(data) {
	if (confirm("정말 수정하시겠습니까 ?") == true) {		
		var boardNo = $('#boardNo').val()
		var boardTitle = $('#boardTitle').val()
		var boardContent = $('#boardContent').val()
		if(boardTitle === ''){
			alert('제목을 입력해주세요.');
			return;
		}		
		if(boardContent === ''){
			alert('내용을 입력해주세요.');
			return;
		}
		const params = {
				freeboard_no : boardNo,
				freeboard_title : boardTitle,
				freeboard_content :boardContent
				};
		$.ajax({
	         method: "post",
	         dataType: "text",
	         url: "/flow/BoardUpdateServlet",
	         data: params,
	         success: function(data){
	        	 alert('수정 완료');
				location.href='./boardList.html';
	         }    
	         ,error: function(request,status,error){ 
	        	 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
	    });
	} 
});