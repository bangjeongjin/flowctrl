/**
 * 
 */
$(document).ready(function(){
	currentPage(1);
});
function currentPage(pageNum){
	var words = $("#searchWord").val();
	var type = $("#searchType").val();

	if(pageNum == null){
		pageNum = 1;
	}
	const params = {currentPage : pageNum,
					searchWord : words,
					searchType : type};
	$.ajax({
		type : 'get',
		url : '/flow/BoardListServlet',
		data : params,
		success : function(data) {
		    var result = data;
			var str = '';
			var resultJson = JSON.parse(data);    

			$.each(resultJson, function(i) {
				str += '<tr>';
				str += '<td>' + resultJson[i].freeboard_no + '</td><td><a href="#" onclick="boardRead('+resultJson[i].freeboard_no+');">'+ resultJson[i].freeboard_title + '</a></td><td>' + resultJson[i].freeboard_content + '</td><td>' + resultJson[i].freeboard_writedate + '</td>';
				str += '</tr>';	
				
			});
			$("#board_ser").html(str);			
			pageview(pageNum);
		}
	});		
}	
// 게시글 번호 받아서 넘겨주기
function boardpaginationRead(freeboard_no) {
	location.href="./boardRead.html?freeboard_no="+freeboard_no;
}
function pageview(pageNum) {
	var words = $("#searchWord").val();
	var type = $("#searchType").val();

	const params = {currentPage : pageNum,
					searchWord : words,
					searchType : type};	
	$.ajax({
		type : 'get',
		url : '/flow/BoardPageServlet',
		data : params,
		success : function(data) {
			
			var dataHtml = '<ul class="pagination">';
			var resultJson = JSON.parse(data);  
			
			// 1 페이지로
			if(resultJson.onePage){
				dataHtml += '<li class="page-item"><a href="javascript:currentPage('+resultJson.onePage+');"> << </a></li>';
			}else{
				dataHtml += '<li class="page-item disabled"><a> << </a></li>';
			}
			//단락 첫 페이지로
			if(resultJson.prev > 0){
				dataHtml += '<li class="page-item"><a class="page-item" href="javascript:currentPage('+(resultJson.startPage-1)+');"> < </a></li>';
			}else{
				dataHtml += '<li class="page-item disabled"><a class="page-item"> < </a></li>';
			}
			//페이지 단락 10개 생성
			for(var i=resultJson.startPage-1 ; resultJson.endPage > i; i++){								
				if(pageNum == i+1){
					dataHtml += '<li class="page-item disabled"><a class="page-item">' + (i+1) + '</a></li>';
				}else{
					dataHtml += '<li class="page-item" data-page="'+(i+1)+'" onclick="javascript:currentPage('+(i+1)+');"><a class="page-item">' + (i+1) + '</a></li>';
				}				
			}
			//다음 단락으로
			if(resultJson.next > 0){
				dataHtml += '<li class="page-item" onclick="javascript:currentPage('+(resultJson.endPage+1)+');"><a class="page-item"> > </a></li>';
			}else{
				dataHtml += '<li class="page-item disabled"><a class="page-item"> > </a></li>';
			}
			//끝 페이지로
			if(resultJson.totalPage){
				dataHtml += '<li class="page-item" onclick="javascript:currentPage('+resultJson.totalPage+');"><a class="page-item"> >> </a></li>';
			}else{
				dataHtml += '<li class="page-item disabled"><a class="page-item"> >> </a></li>';
			}
			dataHtml += '</ul>';
		    
		    $("#data-container").html(dataHtml);
		}
	})	
} 