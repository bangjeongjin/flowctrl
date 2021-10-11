/**
 * 
 */

function formSubmit(){	
	
    var form = $('#writeForm')[0]
    var data = new FormData(form);    
    $('#writeForm').prop('disabled', true);	
    $.ajax({
        type: "post",
        enctype: 'multipart/form-data',
        url: "/flow/BoardWriteServlet",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#writeForm').prop('disabled', false);
        	alert('success',location.href = "boardList.html");    	
        },
        error: function (e) {
            $('#writeForm').prop('disabled', false);
            alert('fail');
        }
    });
}