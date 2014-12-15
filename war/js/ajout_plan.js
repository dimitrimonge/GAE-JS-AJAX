var i=0;
var tab_duree = [];
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
	var tableauTitle ;  
	var tableauDescr ; 
	var heure= parseInt($("#heure").val());
	var minute =parseInt ($("#minute").val());
	var seconde=parseInt($("#seconde").val());
	tableauTitle= $("#titleDescription").val(); 
	tableauDescr=  $("#exerciceDescription").val();
	
	if (isNaN(heure)|| isNaN(minute) || isNaN(seconde) ){alert(" Le format de la durée n'est pas un chiffre ! ");}
	else if (tableauTitle==""){ alert("veuillez entrer un titre ")}
	else if (tableauDescr==""){ alert("veuillez entrer une description ")}
	else 
	{	// --------------Ajout des temps --------------
		date1=new Date(0,0,0,heure,minute,seconde);
		tab_duree.push(date1);
		MAJtempsTot();
		var temps = Math.round((heure*60+minute+seconde/60)*100)/100;	
		// --------------/ Ajout des temps --------------
		i=i+1;
		 $('#exercicesAdded').append("<tr id="+i+"><td> </td><td>"+ tableauTitle+"</td> <td class='hidden-xs'><p>"+
				 tableauDescr+
				 "</p></td><td>"+
				 temps+ " min"+
				 '</td> <td><button onClick="supr(\''+i+'\');" type="submit" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> </button></td></tr>' 
		 );
	}
}

function supr(id)
{
	tab_duree.splice(id, 1);
	MAJtempsTot();
	var idDelete="#"+ id;
	$(idDelete).remove();
	console.log(id);
}


function datastore()
{
	var inputTitle= $("#inputTitle").val();
	var inputDescription= $("#inputDescription").val();
	var domain= $("#e1").val();
	var timeID= $("#timeID").text();
	var exercicesAdded= $("#exercicesAdded").text();
	
	alert(timeID);
	
	$.get('ajout', 
	{ 
		'exercicesAdded':exercicesAdded,
		'inputTitle':inputTitle,
		'inputDescription':inputDescription,
		'domain':domain,
		'timeID':timeID
	},
	function(responseText)
	{
			
  			
  	});
}