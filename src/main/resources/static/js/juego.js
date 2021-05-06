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
	api.obtenerMesa(name,function(datos){
		console.log(datos);
		for(numero in datos[1]){
			let rnumero=parseInt(numero) +1;
			$(".monedas_apuestasmesa").html(datos[3]);
			$("#monedas_propias"+rnumero+" label.mpropias").html(datos[1][numero].moneda);
			if(datos[1][numero].nickName==window.localStorage.usuario){
				console.log("#jugador"+rnumero+" img.img1");
				if(datos[1][numero].cartas[0]!=undefined){
					$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/"+datos[1][numero].cartas[1]+"-"+datos[1][numero].cartas[0]+".png");
				}else{
					$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/carta-blanca.png");
				}if(datos[1][numero].cartas[3]!=undefined){
					$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/"+datos[1][numero].cartas[3]+"-"+datos[1][numero].cartas[2]+".png");
				}else{
					$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				if(datos[1][numero].turno){
					$(".botones_juego").removeAttr("disabled");
				}else{
					$(".botones_juego").attr("disabled","true");
				}
			}else{
				if(datos[1][numero].cartas[0]!=undefined){
					$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/atras.png");
				}else{
					$("#jugador"+rnumero+" img.img1").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				if(datos[1][numero].cartas[3]!=undefined){
					$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/atras.png");
				}else{
					$("#jugador"+rnumero+" img.img2").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				
			}
			console.log(datos[1][numero].turno);
			if(datos[1][numero].turno){
				$("#jugador"+ rnumero +" img.img3").attr('style','border:8px solid yellow');
				
			}else{
				$("#jugador"+ rnumero +" .img3").attr('style','border:0px');
			}
		}
		
		for(let i=0;i<5;i++){
	
			if(i>=datos[2].length){
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/carta-blanca.png')
			}
			else{
			
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/'+datos[2][i][1]+'-'+datos[2][i][0]+'.png');
			}
		}
		if(datos[0]<10){
			$("#cronometro").html("0"+datos[0]);
		}else{
			$("#cronometro").html(datos[0]);
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
				
				$("#mesa_poker").append('<div id="jugador'+ rnumero + '" class="jugador"><div id="monedas_apuestas'+ rnumero +'" class="monedas_apuestas"><label class="mapuestas"></label></div><div id="monedas_propias'+ rnumero +'" class="monedas_propias"><label class="mpropias"></label></div><img class="img1" src="/estilos/imagenes/carta-blanca.png"><img class="img2" src="/estilos/imagenes/carta-blanca.png"><img class="img3" src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100px" height="auto"></div>');
			});
		}
	});
	setInterval(actualizarJuego, 1000);

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