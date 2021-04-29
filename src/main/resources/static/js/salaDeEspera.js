var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function getQueryVariable(variable) {
   var query = window.location.search.substring(1);
   var vars = query.split("&");
   for (var i=0; i < vars.length; i++) {
       var pair = vars[i].split("=");
       if(pair[0] == variable) {
           return pair[1];
       }
   }
   return window.localStorage.usuario;
}

function priv(){
	api.investigarSala(name,function(mesa){		
		$(".botonP_P").remove();
		if(mesa.iniciada){
			window.location.href="/juego.html?name="+name;
		}
		
		else if(mesa.publico){
			$("#boton_privacidad").append('<button id="boton_priv" class="btn btn-success botonP_P" type="button" class="btn btn-primary">Publico</button>');
		}
		
		else{
			$("#boton_privacidad").append('<button id="boton_priv" class="btn btn-danger botonP_P" type="button" class="btn btn-danger">Privado</button>');
		}
		$(".botonP_P").click(async function(){
			let valor=await api.privacidadSala(name);
			priv();
			
		});
		
	});
}

function perfilJugadores(){
	
	api.investigarSala(name,async function(mesa){
		for(numero in mesa.jugadores){
			console.log(jugadores_actuales.includes(mesa.jugadores[numero]));
			if(jugadores_actuales.includes(mesa.jugadores[numero])==false){
				await api.getPerfil(mesa.jugadores[numero],function(perfil){
					$("#jugadores").append('<div id="' + perfil.nickname + '" class="jugador"><div class="superior"><img src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100%"></div><div class="inferior"><label>'+ perfil.nickname +'<label></div></div>');
				});
			}else{
				let ind=jugadores_actuales.indexOf(mesa.jugadores[numero]);
				jugadores_actuales.splice(ind,1);
			}
		}
		for(numero in jugadores_actuales){
			console.log(jugadores_actuales[numero]);
			$("#"+jugadores_actuales[numero]).remove();
		}
		
		jugadores_actuales=mesa.jugadores;
	});
}

var name=getQueryVariable("name");
var jugadores_actuales=[];
$(document).ready(async function(){
	
	await api.investigarSala(name,async function(mesa){
		
		$("#mesa_vacio").html(mesa.nombre);
		$("#clave_vacio").html(mesa.clave);
		jugadores_actuales=mesa.jugadores;
		for(numero in mesa.jugadores){
			console.log(jugadores_actuales.includes(mesa.jugadores[numero]));
				await api.getPerfil(mesa.jugadores[numero],function(perfil){			
					$("#jugadores").append('<div id="' + perfil.nickname + '"class="jugador"><div class="superior"><img src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100%"></div><div class="inferior"><label>'+ perfil.nickname +'<label></div></div>');
				});				
		}
		jugadores_actuales=mesa.jugadores;

		setInterval(priv, 3000);
		setInterval(perfilJugadores, 3000);

		
	});
	
				
	
	$("#boton_comenzar").click(function(){
		
		api.comenzarJuego(name);
		juegoComenzado=true;
		
	});

	$("#boton_salir").click(function(){
		api.eliminarJugador(name,window.localStorage.usuario);
		
	});
	
	

});