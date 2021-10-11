/**
 * 
 */
function getParam(sname) {
	var params = location.search.substr(location.search.indexOf("?") + 1);
    var sval = "";
    params = params.split("&");
    for (var i = 0; i < params.length; i++) {
        temp = params[i].split("=");
        if ([temp[0]] == sname) { sval = temp[1]; }
    }
    return sval;
}    
window.onload = function(){ 	
    var param = {};
    param.freeboard_no = getParam("freeboard_no");
	$.ajax({
		type : 'get',
		url : '/flow/BoardReadServlet',
		data : param,
		success : function(data) {
			getBoardRead(data);
			console.log(data);
		},
		error : function(xhr, status, error) {
			console.log(xhr, status, error);
		}
	});
}
function getBoardRead(data) {
	if(data != null){			
		var dataToJson = JSON.parse(data);
		var boardNo = dataToJson.freeboard_no;
		var boardTitle = dataToJson.freeboard_title;						
		var boardContent = dataToJson.freeboard_content;
		var boardFile = dataToJson.file_name;
		
		$('#boardNo').val(boardNo)
		$('#boardTitle').val(boardTitle)
		$('#boardContent').val(boardContent)
		$('#boardFile').val(boardFile)
	}
}