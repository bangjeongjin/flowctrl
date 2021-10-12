/**
 * 
 */
$(function() {
    $("#schRun").click(function() {
      $.ajax({
        type:"get",
        url:"/flow/APIScheduler",
        success: function() {
          alert("동작");
        },
        error: function(e) {
          alert("동작 실패");
        }			
      });
    });
  });