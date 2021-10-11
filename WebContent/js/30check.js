/**
 * 
 */
$(document).ready(function(){	 
	$.ajax({
        type:"get",
        url:"/flow/APISchedulerCheck2",
       
        success: function(data) {
        	let x =  document.getElementsByClassName("sch-text1")[0];
        	x.innerText = data;
        }			
      });
});
$(function () {
    $("#schRun2").click(function() {
      $.ajax({
        type:"get",
        url:"/flow/APISchedulerCheck2",
        
        success: function(data) {
        	if(data){
        		let x =  document.getElementsByClassName("sch-text1")[0];
        		x.innerText = "Running";
        	}else{
        		let x =  document.getElementsByClassName("sch-text1")[0];
        		x.innerText = "Stopped";
        	}
        },
        error: function(e) {
          alert("error");
        }			
      });
    });
  });