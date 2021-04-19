/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}




var usuario = api.getUsuario(window.localStorage.usuario,function(obtenerUsuario){
		console.log(obtenerUsuario);
		$("#nombreUsuario").attr("value",obtenerUsuario.name);
		$("#correo").attr("value",obtenerUsuario.correo);
	
});

var monedas = api.getPerfil(window.localStorage.usuario,function(obtenerMonedas){
	
	
	api.logosComprados(window.localStorage.usuario,function(logosC){
		console.log(logosC);
		var numero=-1;
		for(n in logosC){
			if(logosC[n].recurso == obtenerMonedas.imagen_perfil){
				
				numero = parseInt(n);
				
				break;
			}
		}
		$("#imagen_perfil").append('<div class="imagenP"><img id="'+logosC[numero].recurso +'" src="/estilos/imagenes/' + logosC[numero].recurso + '" width="100%"></div>');
		$("#siguiente").click(function(){
			
			numero+=1;
			if(numero >= logosC.length){
				numero=0;
			}
			$(".imagenP").remove();
			$("#imagen_perfil").append('<div class="imagenP"><img id="'+logosC[numero].recurso +'" src="/estilos/imagenes/' + logosC[numero].recurso + '" width="100%"></div>');
			
		});
		$("#anterior").click(function(){
			
			numero-=1;
			if(numero < 0){
				numero=logosC.length-1;
			}
			$(".imagenP").remove();
			$("#imagen_perfil").append('<div class="imagenP"><img id="'+logosC[numero].recurso +'" src="/estilos/imagenes/' + logosC[numero].recurso + '" width="100%"></div>');
			
		});
		
		$("#guardarCambios").click(async function(){
		$(".error").remove();
		var nombre = $("#nombreUsuario").val();
		var correo = $("#correo").val();
		re=/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
		let falg=true;
	
		if(nombre.length < 5){
			console.log("rrrrr");
			$("#nombreUsuario").after('<small class="error">Nombre demasiado corto</small>');
			
			falg=false;
		}
		if(!re.exec(correo)){
			console.log(correo);
			$("#correo").after('<small class="error">Correo es invalido</small>');
			falg=false;
		}
		console.log(falg);
		if(falg){
			console.log($(this).attr("id"));
			await api.actualizarDatos(nombre,correo,window.localStorage.usuario);
			await api.actualizarDatosPerfil(logosC[numero].recurso,window.localStorage.usuario);
		}


	});
		
			
	});
	
});



