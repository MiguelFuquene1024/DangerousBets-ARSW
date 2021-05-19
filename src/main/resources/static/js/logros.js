var api = apiclient;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function logros(logros){
	console.log(logros);
	for(numero in logros){
		if(logros[numero][0] != null){
			$("#medallas").append('<div class="medalla"><div id="primeraRonda" class="imagen"><img src="estilos/imagenes/'+logros[numero][0]+'" width="100%"></div><label>'+logros[numero][1]+'</label></div>');

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