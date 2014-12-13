var i=0;
function test()
{
	
	
      
   // console.log(fruits.length);
	var tableauTitle ;  // Public 
	var tableauDuree ; 
	var tableauDescr ; 
	var heure= parseInt($("#heure").val());
	var minute =parseInt ($("#minute").val());
	var seconde=parseInt($("#seconde").val());
	
	
	/*if(heure!= NaN && minute!=NaN && seconde!= NaN)	// retourne vrai si non un entier 
		{
			
			alert("ouaaai");
		}*/

	tableauTitle= $("#titleDescription").val(); 
	tableauDescr=  $("#exerciceDescription").val();
	//isNaN(valeur); //retourne vrai si valeur  
	var temps = $("#heure").val() + $("#minute").val() + $("#seconde").val();
	tableauDuree = temps;
	i=i+1;
	
	 $('#exercicesAdded').append("<tr id="+i+"><td> </td><td>"+ tableauTitle+"</td> <td class='hidden-xs'><p>"+
			 tableauDescr+
			 "</p></td><td>"+
			 tableauDuree+
			 '</td> <td><button onClick="supr(\''+i+'\');" type="submit" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> </button></td></tr>' 
	 );



}

function supr(id)
{
	//alert(id)
	
	var idDelete="#"+ id;
	$(idDelete).remove();
	console.log(id);
}