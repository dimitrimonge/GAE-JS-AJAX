
function recupData()
{
	alert("coucou");
	//var titre= getParameterByName('titre');
	var titre = location.search.split('titre=')[1];	// recupere le titre du training plan associé
	alert(titre);
		$.get('resultdetail',{'titre':titre},function(responseText){
			alert("in");
			//var tableau = [];
			alert(responseText);
			
			
			
		});

}
