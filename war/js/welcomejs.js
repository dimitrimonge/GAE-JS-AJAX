$(document).ready(function(){

	  	$.get('welcome', function(responseText){
	  		$('#texte_accueil').text(responseText);
	  		
	  		setTimeout(function(){
	  			window.location.href = "ha-search-screen.html";
	  		},3000);	  		
	  		
	  	});
});