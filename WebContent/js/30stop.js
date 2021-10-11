/**
 * 
 */
$(function() {
    $("#schStop2").click(function() {

      $.ajax({
        type:"get",
        url:"/flow/APISchedulerStop2",
        success: function() {
          alert("중지");	
          let x =  document.getElementsByClassName("sch-text1")[0];
          x.innerText = "Stopped";
        },
        error: function(e) {
          alert("중지 실패");
        }			
      });
    });
  });
