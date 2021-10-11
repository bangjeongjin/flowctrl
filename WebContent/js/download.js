/**
 * 
 */
function Down(){
	var boardFile = $('#boardFile').val();
    const encFileName = encodeURI(boardFile);
    window.location ='/flow/BoardDownloadServlet?file_name=' + encFileName 
}