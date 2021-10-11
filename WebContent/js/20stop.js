/**
 * 
 */
$(function() {
    $("#schStop").click(function() {
      $.ajax({
        type:"get",
        url:"/flow/APISchedulerStop",
        success: function() {
          alert("중지");
          let x =  document.getElementsByClassName("sch-text")[0];
          x.innerText = "Stopped";
        },
        error: function(e) {
          alert("중지 실패");
        }			
      });
    });
  });
