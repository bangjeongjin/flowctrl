/**
 * 
 */
google.charts.load('current', {'packages' : [ 'line' ]});
google.charts.setOnLoadCallback(drawChart);

function dateFormat(afterDay) {	
	let today = new Date();
	var dayOfMonth = today.getDate();
	today.setDate(dayOfMonth - afterDay);
	  	  
    let month = today.getMonth() + 1;
    let day = today.getDate();   

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;

    return today.getFullYear() + '-' + month + '-' + day;
}
function drawChart(data) {	
	$.ajax({		
		type : 'get',
		url:'/flow/BoardLineChartServlet',
		data: 'json',
		success : function (data) {
		var dataJson = JSON.parse(data);		
		var dataLine = new google.visualization.DataTable();
			dataLine.addColumn('string', 'week');
			dataLine.addColumn('number', 'My First Dataset');			
			dataLine.addRows([ [ dateFormat(42), dataJson.sixWeek ], 
						   	   [ dateFormat(35), dataJson.fiveWeek ],
						       [ dateFormat(28), dataJson.fourWeek ],
						       [ dateFormat(21), dataJson.threeWeek ],
						       [ dateFormat(14), dataJson.twoWeek ],
						       [ dateFormat(7), dataJson.oneWeek ] ]);			
		var options = {
			chart : {
				title : '최근 6주 간 게시물 등록 추이'
			},
			width : 900, height : 500
		};	
		var chart = new google.charts.Line(document.getElementById('linechart_material'));	
			chart.draw(dataLine, google.charts.Line.convertOptions(options));
		}		
	});			
}