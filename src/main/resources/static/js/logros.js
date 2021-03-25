var api = apiclient;
function logros(logros){
	$("#primeraRonda").css("background-image","url('estilos/imagenes/"+logros[0]+"')");
	$("#gana3Rondas").css("background-image","url('estilos/imagenes/en_racha.png')");
	$("#fullHouse").css("background-image","url('estilos/imagenes/full_house.png')");
	$("#ganaPorEscalera").css("background-image","url('estilos/imagenes/escalera.png')");
	$("#ganaPorColor").css("background-image","url('estilos/imagenes/color.png')");
	$("#ganaElJuego").css("background-image","url('estilos/imagenes/ganar_juego.png')");
	$("#apostarTodo").css("background-image","url('estilos/imagenes/apostar_todo.png')");
	$("#ganaPorRetiro").css("background-image","url('estilos/imagenes/cuadrado.png')");
}

$(document).ready(function(){	
	$(document).ready(function(){	
		api.getLogros(logros);			
		
	});
});