
function preparar(){
//const express=require('express');
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
		for(numero in datos.jugadores){
			if(datos.estadoPartida=="finJuego"){
				setTimeout(function(){
					window.location.href="/menu.html";
				}, 5000);
			}
			$(".monedas_apuestasmesa").html(datos.apuestaTotalMesa);
			$("#monedas_propias"+ datos.jugadores[numero].numeroJugador +" label.mpropias").html(datos.jugadores[numero].moneda);
			$("#monedas_apuestas"+ datos.jugadores[numero].numeroJugador +" label.mapuestas").html(datos.jugadores[numero].misApuestas);
			
			if(datos.jugadores[numero].nickName==window.localStorage.usuario){
				
				if(datos.jugadores[numero].cartas[0]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/"+datos.jugadores[numero].cartas[1]+"-"+datos.jugadores[numero].cartas[0]+".png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/carta-blanca.png");
				}if(datos.jugadores[numero].cartas[3]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/"+datos.jugadores[numero].cartas[3]+"-"+datos.jugadores[numero].cartas[2]+".png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				if(datos.jugadores[numero].turno){
					$(".botones_juego").removeAttr("disabled");
					
						
					if(datos.apuesta>datos.jugadores[numero].misApuestas){
						$("#boton_pasar").attr("disabled","true");
						apu_grande= parseInt(datos.apuesta)-datos.jugadores[numero].misApuestas;
						$("#apuesta_extra").html("Debes apostar minimo " + apu_grande);
						
					}else{
						$("#apuesta_extra").html("");

						
					}
					
				}else{
					$(".botones_juego").attr("disabled","true");
					
				}
			}else if(datos.estadoPartida=="buscarGanador"){
				if(datos.jugadores[numero].cartas[0]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/"+datos.jugadores[numero].cartas[1]+"-"+datos.jugadores[numero].cartas[0]+".png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/carta-blanca.png");
				}if(datos.jugadores[numero].cartas[3]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/"+datos.jugadores[numero].cartas[3]+"-"+datos.jugadores[numero].cartas[2]+".png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/carta-blanca.png");
				}
			}
			
			else{	
				if(datos.jugadores[numero].cartas[0]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/atras.png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img1").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				if(datos.jugadores[numero].cartas[3]!=undefined){
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/atras.png");
				}else{
					$("#jugador"+datos.jugadores[numero].numeroJugador+" img.img2").attr("src","/estilos/imagenes/carta-blanca.png");
				}
				
			}
			if(datos.jugadores[numero].turno){
				
				$("#jugador"+ datos.jugadores[numero].numeroJugador +" img.img3").attr('style','border:8px solid yellow');
				
				
			}else{
				if(datos.jugadores[numero].eliminado){
					$("#jugador"+datos.jugadores[numero].numeroJugador).remove();
				}
				$("#jugador"+ datos.jugadores[numero].numeroJugador +" .img3").attr('style','border:0px');
			}
		}
		
		for(let i=0;i<5;i++){
	
			if(i>=datos.cartasMesa.length){
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/carta-blanca.png')
			}
			else{
			
				$("#jugadormesa img.carta"+i).attr('src','/estilos/imagenes/'+datos.cartasMesa[i][1]+'-'+datos.cartasMesa[i][0]+'.png');
			}
		}
		if(datos.cronometro<10){
			$("#cronometro").html("0"+datos.cronometro);
		}else{
			$("#cronometro").html(datos.cronometro);
		}
		
		
	});
	
	api.recibirMensaje(name,window.localStorage.usuario,function(data){
		for(numero in data){
			$("#mensajes_recibidos").append('<p>'+ data[numero] +'</p>');
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
				
				$("#mesa_poker").append('<div id="jugador'+ rnumero + '" class="jugador"><div id="monedas_apuestas'+ rnumero +'" class="monedas_apuestas"><label class="mapuestas"></label></div><div id="monedas_propias'+ rnumero +'" class="monedas_propias"><label class="mpropias"></label></div><img class="img1" src="/estilos/imagenes/carta-blanca.png"><img class="img2" src="/estilos/imagenes/carta-blanca.png"><img class="img3" src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100px" height="auto"><label>'+ perfil.nickname +'</label></div>');
			});
		}
	});
	setInterval(actualizarJuego, 500);

	$("#boton_pasar").click(function(){
		$(".botones_juego").attr("disabled","true");
		api.pasarJugador(name);
		
	});
	$("#boton_apostar").click(function(){
		$(".botones_juego").attr("disabled","true");
		api.apostar(name, $("#numero").val());
	});
	$("#boton_abandonar").click(function(){
		$(".botones_juego").attr("disabled","true");
		api.abandonarJuego(name);
		
	});
	
	$("#boton_abandonar_Juego").click(function(){
		if(confirm("Seguro que quiere abandonar la partida?")){
			api.eliminarJugador(name,window.localStorage.usuario);
		}
		
	});
	
	$("#invocar_chat").click(function(){
		
		if($("#chat").css("display")=="none"){
			$("#chat").css("display","block");
		}else{
			$("#chat").css("display","none")
		}
		
	});
	
	$("#enviar_mensaje").click(function(){
		let mensaje='<label style="font-weight:700;">' + window.localStorage.usuario + ": </label>"+ $("#mensaje").val();
		
		api.nuevoMensaje(name,mensaje);
		$('input[type="text"]').val('');
	});
	$("input").keypress(function(tecla){
		
        if(tecla.which==13){
			let mensaje='<label style="font-weight:700;">' + window.localStorage.usuario + ": </label>"+ $("#mensaje").val();
			
			api.nuevoMensaje(name,mensaje);
			$('input[type="text"]').val('');
		}
    });
		

	
	

});
}
preparar();