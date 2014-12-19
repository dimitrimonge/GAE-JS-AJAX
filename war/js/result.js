function recupResult()
{
	//var titre= getParameterByName('titre');
		var search = location.search.split('search=')[1];	// recupere le titre du training plan associé
	/*	$.ajax({
			type:"POST",
			dataType:'json',
			url:'resultsearch'})*/
		$.post('resultsearch',{'search':search},function(responsesearch){
			
			alert(responsesearch);
			var obj = JSON.parse(responseText);
			//alert obj;
			var nom=obj.arrayObj[0].title + " " + obj.arrayObj[0].descr + " " + obj.arrayObj[0].time ; 
			
			var nom2=obj.arrayObj[1].title + " " + obj.arrayObj[1].descr + " " + obj.arrayObj[1].time ; 
			alert(nom);
			alert(nom2);
		});
}


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
