var api = apiclient;
function logros(logros){
	
	$("#primeraRonda").css("background-image","url('estilos/imagenes/"+logros[0]+"')");
	$("#gana3Rondas").css("background-image","url('estilos/imagenes/"+logros[1]+"')");
	$("#fullHouse").css("background-image","url('estilos/imagenes/"+logros[2]+"')");
	$("#ganaPorEscalera").css("background-image","url('estilos/imagenes/"+logros[3]+"')");
	$("#ganaPorColor").css("background-image","url('estilos/imagenes/"+logros[4]+"')");
	$("#ganaElJuego").css("background-image","url('estilos/imagenes/"+logros[5]+"')");
	$("#apostarTodo").css("background-image","url('estilos/imagenes/"+logros[6]+"')");
	$("#ganaPorRetiro").css("background-image","url('estilos/imagenes/"+logros[7]+"')");
}

$(document).ready(function(){	
	$(document).ready(function(){	
		api.getLogros(logros);			
		
	});
});