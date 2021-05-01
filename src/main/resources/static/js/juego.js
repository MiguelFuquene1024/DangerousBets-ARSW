var api = apiclient;
var juegoComenzado=false;
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

function actualizarJuego(){
	
	api.investigarPokerPlayer(name,window.localStorage.usuario,function(datos){
		console.log(datos);
	});
	api.obtenerMesa(name,function(datos){
		console.log(datos);
		$("#jugadormesa").append('<img src="/estilos/imagenes/2-corazones.png">');
	});
	
}



var name=getQueryVariable("name");
var jugadores_actuales=[];
$(document).ready(function(){
		
	api.investigarSala(name, async function(mesa){
		
			for(numero in mesa.jugadores){
				let rnumero=parseInt(numero) +1;
				await api.getPerfil(mesa.jugadores[numero],function(perfil){
				
			$("#mesa_poker").append('<div id="jugador'+ rnumero + '" class="jugador"><div id="monedas_apuestas'+ rnumero +'" class="monedas_apuestas"></div><div id="monedas_propias'+ rnumero +'" class="monedas_propias"></div><img class="img1" src="/estilos/imagenes/2-corazones.png"><img class="img2" src="/estilos/imagenes/2-corazones.png"><img class="img3" src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100px" height="auto"></div>');
			});
		
			
				
				
				
		
			
			
			
		}
	});
	setInterval(actualizarJuego, 3000);

	$("#boton_pasar").click(function(){
		api.pasarJugador(name);
		
	});
	$("#boton_apostar").click(function(){
		api.apostar(name,300);
		
	});
	$("#boton_abandonar").click(function(){
		api.abandonarJuego(name);
		
	});
		

	
	

});