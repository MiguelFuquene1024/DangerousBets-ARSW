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



function perfilJugadores(){
	
	api.investigarSala(name,async function(mesa){
	
		for(numero in mesa.jugadores){
			
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
			
			$("#"+jugadores_actuales[numero]).remove();
		}
		
		jugadores_actuales=mesa.jugadores;
		//Privacidad Sala
		if(mesa.iniciada){
			window.location.href="/juego.html?name="+name;
		}
		
		else if(mesa.publico){
			$("#boton_priv").attr("background-color","#28a745");
			$("#boton_priv").html("Publico");
		}
		
		else{
			$("#boton_priv").attr("background-color","#dc3545");
			$("#boton_priv").html("Privado");
		}
		
		
		//Dueño de la sala
		
		api.getPrincipal(window.localStorage.usuario,mesa.jugadores[0],function(data){
			
			if(data===false){
				$("#boton_comenzar").attr("disabled","true");
				$("#boton_priv").attr("disabled","true");
			}else{
				$("#boton_comenzar").removeAttr("disabled");
				$("#boton_priv").removeAttr("disabled")
			}
		})
	
		
		
		
		
	});
	
	api.recibirMensaje(name,window.localStorage.usuario,function(data){
		for(numero in data){
			$("#mensajes_recibidos").append('<p>'+ data[numero] +'</p>');
		}
	});
	
	
}

var name=getQueryVariable("name");
var jugadores_actuales=[];
var jugador="";
$(document).ready(async function(){
	
	await api.investigarSala(name,async function(mesa){
		
		$("#mesa_vacio").html(mesa.nombre);
		
		jugadores_actuales=mesa.jugadores;
		for(numero in mesa.jugadores){
			
				await api.getPerfil(mesa.jugadores[numero],function(perfil){			
					$("#jugadores").append('<div id="' + perfil.nickname + '"class="jugador"><div class="superior"><img src="/estilos/imagenes/'+ perfil.imagen_perfil +'" width="100%"></div><div class="inferior"><label>'+ perfil.nickname +'<label></div></div>');
				});				
		}
		jugadores_actuales=mesa.jugadores;

		setInterval(perfilJugadores, 1000);

		
	});
	
	api.obtenerClaveDeAcceso(name, function(clave){
		$("#clave_vacio").html(clave);
		
	});
	$("#boton_comenzar").click(function(){
		
		api.comenzarJuego(name);
		juegoComenzado=true;
		
	});

	$("#boton_salir").click(function(){
		api.eliminarJugador(name,window.localStorage.usuario);
		
	});
	
	$("#invocar_chat").click(function(){
		
		if($("#chat").css("display")=="none"){
			$("#chat").css("display","block");
		}else{
			$("#chat").css("display","none")
		}
		
	});
	$("#boton_priv").click(function(){
		
			let valor=api.privacidadSala(name);

		});
	$("#enviar_mensaje").click(function(){
		api.getPerfilToken(window.localStorage.usuario, function(yo){
			jugador=yo.nickname;
		
		});
		let mensaje='<label style="font-weight:700;">' + jugador + ": </label>"+ $("#mensaje").val();
		
		api.nuevoMensaje(name,mensaje);
		$('input[type="text"]').val('');
	});
	$("input").keypress(function(tecla){
		api.getPerfilToken(window.localStorage.usuario, function(yo){
			jugador=yo.nickname;
		
		});
        if(tecla.which==13){
			let mensaje='<label style="font-weight:700;">' + jugador + ": </label>"+ $("#mensaje").val();
			
			api.nuevoMensaje(name,mensaje);
			$('input[type="text"]').val('');
		}
    });
});

