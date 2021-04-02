var api = apiclient;
function logros(logros){
	console.log(logros);
	for(numero in logros){
		if(logros[numero][0] != null){
			$("#medallas").append('<div class="medalla"><div id="primeraRonda" class="imagen"><img src="estilos/imagenes/'+logros[numero][0]+'" width="100%"></div><label>'+logros[numero][1]+'</label></div>');
			console.log(logros[numero][0]);
			//$("#primeraRonda").css("background-image","url('estilos/imagenes/"+logros[0]+"')");
		}else{
			$("#medallas").append('<div class="medalla"><div id="primeraRonda" class="imagen"><img src="estilos/imagenes/cuadrado.png" width="100%"></div><label>'+logros[numero][1]+'</label></div>');
		}
	}
	
}

$(document).ready(function(){	
	$(document).ready(function(){	
		api.getLogros(logros);			
		
	});
});