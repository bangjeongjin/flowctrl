/**
 * 
 */
$(function() {
    $("#schRun2").click(function() {
      $.ajax({
        type:"get",
        url:"/flow/APIScheduler2",
        success: function() {
          alert("동작");
        },
        error: function(e) {
          alert("동작 실패");
        }			
      });
    });
  });