$(document).ready(function(){

	
	  	$.ajax({
	  		type:"POST",
	  		dataType:'json',
	  		url:'resultsearch'
	  	}).then(function(data){
	  		$('#exercice').text(data.exercice);
	  		$('#training').text(data.training);

	  		
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
  		window.location.href = data.MyOpenId.com;
  	})
}

function send_data(){
	 //recup des valeurs dans le formulaire
	 var research=document.getElementById('research').value;
	 //ajout dans l'url
		 window.location.href = "ha-result-screen.html?search="+research;  	
	}
