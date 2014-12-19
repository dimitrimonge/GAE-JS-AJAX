




function recupData()
{
	//var titre= getParameterByName('titre');
	var titre = location.search.split('titre=')[1];	// recupere le titre du training plan associé
		$.get('resultdetail',{'titre':titre},function(responseText){
			//var tableau = [];
			
			alert(responseText);
			var obj = JSON.parse(responseText);
			//alert obj;
			var nom=obj.arrayObj[0].title + " " + obj.arrayObj[0].descr + " " + obj.arrayObj[0].time ; 
			
			var nom2=obj.arrayObj[1].title + " " + obj.arrayObj[1].descr + " " + obj.arrayObj[1].time ; 
			alert(nom);
			alert(nom2);
			
			for (k=0;k<obj.arrayObj.length;k++)
			{
				 $('#exo').append( 
		                    '<tr>'+
						 	'<td class=" col-md-12 col-sm-12 col-xs-12">'+
	                        '<div class="row">'+
	                            '<div class=" col-md-3 col-sm-12 col-xs-12 ">'+
	                               ' <h3>'+  obj.arrayObj[k].title    +'</h3>'+
	                           ' </div>'+
	                           ' <div class=" col-md-1 col-sm-2 col-xs-2 ">'+
	                              '  <p id="totalTimeValue" style="margin-top:25px"><span class="glyphicon glyphicon-time"></span>'+ obj.arrayObj[k].time+'</p>'+
	                           ' </div>'+
	                       ' </div>'+
	                        '<div class="row">'+
	                           ' <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>'+
	                           ' <div class=" col-md-6 col-sm-12 col-xs-12 ">'+
	                            '    <p>'+obj.arrayObj[0].descr+'</p>'+
	                           ' </div>'+
	                           ' <div class=" col-md-3 col-sm-12 col-xs-12 ">'+
	                           '     <div class=" col-md-12 col-sm-12 col-xs-12 ">'+
	                                   ' <div id="flipcountdownbox2">       '+                     
	                                   ' </div>'+
	                               ' </div>'+
	                               ' <div class=" col-md-12 col-sm-12 col-xs-12 centered">'+
	                                   ' <div class="btn-group">'+
	                                       ' <button type="button" class="btn btn-default " ><span class="glyphicon glyphicon-play"></span> </button>'+
	                                       ' <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-pause"></span> </button>'+
	                                        '<button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-stop"></span> </button>'+
	                                   ' </div>'+
	                                   ' <button type="button" class="btn btn-default " > <span class="glyphicon glyphicon-repeat"></span> </button>'+
	                               ' </div>'+
	                                
	                           ' </div>'+
	                            
	                        '</div>'+
	                          '  <div class=" col-md-2 ol-sm-5 col-xs-12 text-center" >'+
	                            '    <button type="submit" class="btn btn-success btn-lg" disabled="disabled"> <span class="glyphicon glyphicon-ok"></span> </button>  '+  
	                            '    <button type="submit" class="btn btn-danger btn-sm" disabled="disabled"> <span class="glyphicon glyphicon-fast-forward"></span> </button>'+
	                          '  </div>'+
	                    '    </td>'+
	                   ' </tr>')
			}
			
			
		});

}
