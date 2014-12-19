var i=0;
var tab_duree = [];
var tableauTitle = [];
var tableauDescr = [];
var tableauTemps = [];

function MAJtempsTot()
{
	oldDate = new Date(0,0,0,0,0,0);
	for (k=0;k<tab_duree.length;k++)
	{
		oldDate.setSeconds(oldDate.getSeconds() + tab_duree[k].getSeconds());
		oldDate.setMinutes(oldDate.getMinutes() + tab_duree[k].getMinutes());
		oldDate.setHours(oldDate.getHours() + tab_duree[k].getHours());
	}	
	var oldtemps=Math.round((oldDate.getHours()*60+oldDate.getMinutes()+oldDate.getSeconds()/60)*100)/100;
	$('#timeID').text(oldtemps+" min"); 
}

function test()
{
	
	var heure= parseInt($("#heure").val());
	var minute =parseInt ($("#minute").val());
	var seconde=parseInt($("#seconde").val());
	titre= $("#titleDescription").val(); 
	descr=  $("#exerciceDescription").val();
	
	if (isNaN(heure)|| isNaN(minute) || isNaN(seconde) ){alert(" Le format de la durée n'est pas un chiffre ! ");}
	else if (titre==""){ alert("veuillez entrer un titre ")}
	else if (descr==""){ alert("veuillez entrer une description ")}
	else 
	{	
		
		tableauTitle.push(titre); 
		tableauDescr.push(descr);
		
		// --------------Ajout des temps --------------
		date1=new Date(0,0,0,heure,minute,seconde);
		tab_duree.push(date1);
		MAJtempsTot();
		var temps = Math.round((heure*60+minute+seconde/60)*100)/100;	
		tableauTemps.push(temps.toString());
		
		
		// --------------/ Ajout des temps --------------
		i=i+1;
		 $('#exercicesAdded').append("<tr id="+i+"><td> </td><td>"+ tableauTitle[i -1]+"</td> <td class='hidden-xs'><p>"+
				 tableauDescr[i-1]+
				 "</p></td><td>"+
				 temps+ " min"+
				 '</td> <td><button onClick="supr(\''+i+'\');" type="submit" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> </button></td></tr>' 
		 );
	}
}

function supr(id)
{
	tab_duree.splice(id, 1);
	tableauTitle.splice(id, 1);
	tableauDescr.splice(id, 1);
	tableauTemps.splice(id, 1);
	MAJtempsTot();
	var idDelete="#"+ id;
	$(idDelete).remove();
	console.log(id);
}


function datastore()
{
	var inputTitle= $("#inputTitle").val();
	inputTitle = inputTitle.replace(/ /g, "_");
	var inputDescription= $("#inputDescription").val();
	var domain= $("#e1").val();
	var timeID= $("#timeID").text();
	/*var exercicesTitle= $("#TableauTitle").text();
	var exercicesDescr= $("#TableauDescr").text();
	var exercicesTime= $("#Tab_duree").text();*/
	$.get('ajout', 
	{ 
		'inputTitle':inputTitle,
		'inputDescription':inputDescription,
		'domain':domain,
		'timeID':timeID,
		'exercicesTabTitle':tableauTitle,
		'exercicesTabDescr':tableauDescr,
		'exercicesTabTime':tableauTemps	
	},
	function(responseText)
	{
			
  	});
	alert("Votre plan d'exercice à bien été ajouté")
	setTimeout(function(){	// pour la MAJ du datastore
			window.location="ha-result-detail-screen.html?titre="+inputTitle;
	  		},1000);	  	
	
}