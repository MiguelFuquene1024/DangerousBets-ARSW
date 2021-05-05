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
	console.log("hola");
	api.investigarPokerPlayer(name,window.localStorage.usuario,function(datos){
		//console.log(datos);
		console.log("hola2");
		for(numero in datos){
			let rnumero=parseInt(numero) +1;
			if(datos[numero].nickName==window.localStorage.usuario){
				console.log("#jugador"+rnumero+" img.img1");
	
				$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/"+datos[numero].cartas[1]+"-"+datos[numero].cartas[0]+".png");
				$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/"+datos[numero].cartas[3]+"-"+datos[numero].cartas[2]+".png");
			}else{
				$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/atras.png");
				$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/atras.png");
			}
			console.log(datos[numero].turno);
			if(datos[numero].turno){
				$("#jugador"+ rnumero +" img.img3").attr('style','border:8px solid yellow');
				
			}else{
				$("#jugador"+ rnumero +" .img3").attr('style','border:0px');
			}
		}		
	});
	api.obtenerMesa(name,function(datos){
	
		$(".borrar").remove();
		for(let i=0;i<5;i++){
	
			if(i>=datos.length){
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/carta-blanca.png')
			}
			else{
			
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/'+datos[i][1]+'-'+datos[i][0]+'.png');
			}
		}
		
	});
	
}



var name=getQueryVariable("name");
var jugadores_actuales=[];
var numeroJugador=0;
$(document).ready(function(){
		
	api.investigarSala(name, async function(mesa){
		
			for(numero in mesa.jugadores){
				let rnumero=parseInt(numero) +1;
				await api.getPerfil(mesa.jugadores[numero],function(perfil){
				
				$("#mesa_poker").append('<div id="jugador'+ rnumero + '" class="jugador"><div id="monedas_apuestas'+ rnumero +'" class="monedas_apuestas"></div><div id="monedas_propias'+ rnumero +'" class="monedas_propias"></div><img class="img1" src="/estilos/imagenes/carta-blanca.png"><img class="img2" src="/estilos/imagenes/carta-blanca.png"><img class="img3" src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100px" height="auto"></div>');
			});
		}
	});
	setInterval(actualizarJuego, 2000);

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