$(document).ready(function(){

	  	$.ajax({
	  		type:"GET",
	  		dataType:'json',
	  		url:'authenuser'
	  	}).then(function(responseText){
	  		$('#authentifie').text(responseText);		
	  		
	  	});
});


function redirectToGoogle(){
  	$.ajax({
  		type:"GET",
  		dataType:'json',
  		url:'authenuser'
  	}).then(function(data){
  		window.location.href =data.Google;
  	})
}

function redirectToyahoo(){
  	$.ajax({
  		type:"GET",
  		dataType:'json',
  		url:'authenuser'
  	}).then(function(data){
  		window.location.href = data.Yahoo;
  	})
	
}

function redirectTomyopenID(){
  	$.ajax({
  		type:"GET",
  		dataType:'json',
  		url:'authenuser'
  	}).then(function(data){
  		window.location.href = data.MyOpenId;
  	})
}

