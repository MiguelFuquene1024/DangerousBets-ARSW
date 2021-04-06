var api = apiclient;
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}


function salasPublicas(datos){
	console.log(datos);
	
	let s='<table class="tabla"><tr><th class="nameSala">Nombre sala</th><th class="cantJugadores">Cantidad de Jugadores</th><th class="vSala">valor Sala</th><th class="unirseSala">Unirse a Sala</th></tr>';

	for(numero in datos){
		
		s=s+'<tr class="jugadores"><th>'+ datos[numero][0].nombre +'</th><th>' + datos[numero][1] +'/8</th><th>'+ datos[numero][0].valorsala +'</th><th><label id="'+ datos[numero][0].nombre +'" class="link">Unirse a Sala</label></th></tr>';
		

	}
	s=s+'</table>';
	$("#salasPublicas").append(s);
	$(".link").click(function(){
			api.anadirJugador($(this).attr("id"));
		});
	
}


$(document).ready(function(){	
	$(document).ready(function(){	
		api.getSalasPublicas(salasPublicas);			
		
	});
});