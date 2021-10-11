/**
 * 
 */
$.ajax({
	type:"get",
	url:"/flow/SchListServlet",
	data: 'JSON',
	success: function(data) {
		var result = data;
		var str = '';
		var resultJson = JSON.parse(data);

		$.each(resultJson, function(i) {
			str += '<tr>';
			str += '<td>' + resultJson[i].index_no + '</td><td>'+ resultJson[i].type1 + '</td><td>' + resultJson[i].type1_Time + '</td><td>' + resultJson[i].type2 + '</td><td>'+ resultJson[i].type2_Time +'</td>';
			str += '</tr>';				
		});
		$("#shc_list").append(str);
	}			
});