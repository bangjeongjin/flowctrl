/**
 * 
 */
function searchajax() {				
		var words = $("#searchWord").val();
		const params = {searchWord : words};		
		if (words != ''){
			$.ajax({
				type: 'get',
				url: '/flow/BoardSearchServlet',
				data: params,
				success: function(data) {
					if(data.length > 0){
						var str = '';						
						var resultJson = JSON.parse(data);
				
						$.each(resultJson, function(i) {
							str += '<tr>';
							str += '<td>' + resultJson[i].freeboard_no + '</td><td><a href="#" onclick="boardRead('+resultJson[i].freeboard_no+');">'+ resultJson[i].freeboard_title + '</a></td><td>' + resultJson[i].freeboard_content + '</td>';
							str += '</tr>';							
						});							
						$("#board_ser").html(str);						
					}
				},
				error: function(e) {
					console.log('error:' + e.status);
				}
			});
		}
}
function boardRead(freeboard_no) {
	location.href="./boardRead.html?freeboard_no="+freeboard_no;
}