/**
 * 
 */
$(document).on('click','#btn_delete',
		function delet(data) {
			var boardNo = $('#boardNo').val()
			var formData = new FormData();
			formData.append("freeboard_no", boardNo);
			$.ajax({
				type : "post",
				dataType : "text",
				url : "/flow/BoardDeletServlet",
				data : {"freeboard_no" : boardNo},
				success : function(data) {
					alert('삭제 완료');
					location.href = './boardList.html';
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});
		});