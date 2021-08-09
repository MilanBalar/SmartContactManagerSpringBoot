console.log("js is called");

$(document).ready(function() {
	alert("hello");
    console.log( "ready!" );
});

function toggleSidebar() {
	
	if($(".sidebar").is(":visible")){
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}
	else{
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
}