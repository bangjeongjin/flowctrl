/**
 * 
 */
$(document).ready(function(){	 
	$.ajax({
        type:"get",
        url:"/flow/APISchedulerCheck",     
        success: function(data) {
        	let x =  document.getElementsByClassName("sch-text")[0];
        	x.innerText = data;        	
        }			
      });
});
$(function () {
    $("#schRun").click(function() {
      $.ajax({
        type:"get",
        url:"/flow/APISchedulerCheck",
        
        success: function(data) {
        	if(data){
        		let x =  document.getElementsByClassName("sch-text")[0];
        		x.innerText = "Running";
        	}else{
        		let x =  document.getElementsByClassName("sch-text")[0];
        		x.innerText = "Stopped";
        	}
        },
        error: function(e) {
          alert("error");
        }			
      });
    });
  });
