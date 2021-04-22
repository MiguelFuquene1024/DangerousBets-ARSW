var api = apiclient;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}


function salasPublicas(datos){

	$(".tabla").remove();
	$(".jugadores").remove();
	let s='<table class="tabla"><tr><th class="nameSala">Nombre sala</th><th class="cantJugadores">Cantidad de Jugadores</th><th class="vSala">valor Sala</th><th class="unirseSala">Unirse a Sala</th></tr>';
	for(numero in datos){
		
		s=s+'<tr class="jugadores"><th>'+ datos[numero].nombre +'</th><th>' + datos[numero].jugadores.length +'/8</th><th>'+ datos[numero].valorsala +'</th><th><label id="'+ datos[numero].nombre +'" class="link">Unirse a Sala</label></th></tr>';
		

	}
	s=s+'</table>';
	$("#salasPublicas").append(s);
	$(".link").click(function(){
			api.anadirJugador($(this).attr("id"));
		});
	
}


$(document).ready(function(){
	
	$(document).ready(function(){	
		setInterval(function(){
			api.getSalasPublicas(salasPublicas);
		}, 3000);			
		
	});
});