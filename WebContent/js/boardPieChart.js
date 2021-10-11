/**
 * 
 */
google.charts.load('current', {'packages' : [ 'corechart' ]});
google.charts.setOnLoadCallback(drawChart);

function drawChart(data) {
	$.ajax({		
		type : 'get',
		url:'/flow/BoardPieChartServlet',
		data: 'json',
		success : function (data) {
		var datajson= JSON.parse(data);
		var dataPie = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], 
				[ '첨부파일 없는 게시물', datajson.fileRemainder ], 
				[ '파일첨부 게시물', datajson.fileCount ]]);
		var	options = {
					title : '한달 이내 첨부파일 게시물 차트 (총 건수: '+ datajson.totalCount +')',
					is3D: true
			};
		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
			chart.draw(dataPie, options);
		}	
	});
}