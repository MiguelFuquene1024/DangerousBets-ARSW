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

function priv(mesa){
	$(".botonP_P").remove();
	if(mesa.publico){
		$("#boton_privacidad").append('<button id="boton_priv" class="btn btn-success botonP_P" type="button" class="btn btn-primary">Publico</button>');
	}else{
		$("#boton_privacidad").append('<button id="boton_priv" class="btn btn-danger botonP_P" type="button" class="btn btn-danger">Privado</button>');
	}
	$(".botonP_P").click(async function(){
		await api.privacidadSala(name);
		api.investigarSala(name,function(mesa){
			console.log(mesa);
			priv(mesa);
		});
	});
}



var name=getQueryVariable("name");
$(document).ready(function(){
	
	api.investigarSala(name,async function(mesa){
		
		$("#mesa_vacio").html(mesa.nombre);
		$("#clave_vacio").html(mesa.clave);
		for(numero in mesa.jugadores){
			console.log(mesa.jugadores[numero]);
			await api.getPerfil(mesa.jugadores[numero],function(perfil){
				
				console.log(perfil);
				$("#jugadores").append('<div class="jugador"><div class="superior"><img src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100%"></div><div class="inferior"><label>'+ perfil.nickname +'<label></div></div>');
			});
			
		}
		priv(mesa);
		
	});
	
				
	
	$("#boton_comenzar").click(function(){
	});

	$("#boton_salir").click(function(){
		api.eliminarJugador(name,window.localStorage.usuario);
		
	});
	

});