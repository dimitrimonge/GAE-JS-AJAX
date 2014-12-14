var i=0;
oldDate = new Date(0,0,0,0,0,0);
function test()
{
	  
   // console.log(fruits.length);
	var tableauTitle ;  
	var tableauDescr ; 
	var heure= parseInt($("#heure").val());
	var minute =parseInt ($("#minute").val());
	var seconde=parseInt($("#seconde").val());
	tableauTitle= $("#titleDescription").val(); 
	tableauDescr=  $("#exerciceDescription").val();
	
	
	//document.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + 

	
	if (isNaN(heure)|| isNaN(minute) || isNaN(seconde) ){alert(" Le format de la durée n'est pas un chiffre ! ");}
	else if (tableauTitle==""){ alert("veuillez entrer un titre ")}
	else if (tableauDescr==""){ alert("veuillez entrer une description ")}
	else 
	{	
		date1 = new Date(0,0,0,heure,minute,seconde);
		//oldDate=oldDate+date1;
		
		oldDate.setSeconds(oldDate.getSeconds() + seconde);
		oldDate.setMinutes(oldDate.getMinutes() + minute);
		oldDate.setHours(oldDate.getHours() + heure);
		
		var oldtemps=Math.round((oldDate.getHours()*60+oldDate.getMinutes()+oldDate.getSeconds()/60)*100)/100;
		var temps = Math.round((heure*60+minute+seconde/60)*100)/100;
		i=i+1;
		
		
		 $('#timeID').text(oldtemps+" min"); 
		 
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
	//alert(id)
	
	var idDelete="#"+ id;
	$(idDelete).remove();
	console.log(id);
}