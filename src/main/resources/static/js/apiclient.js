
var apiclient = (function () {
  
	return {
		getPerfil : function (user,callback) {
	
			$.getJSON("/perfil/"+ user ,function (data) {
			
					callback(data);
					
				}
			);
		},
		
		logosNoComprados : function (user,callback) {
	
			$.getJSON("/logosNoComprados/"+ user ,function (data) {
					callback(data);
					
				}
			);
		},
		obtenerClaveDeAcceso : function (sala,callback){
			user=window.localStorage.usuario;
			
			$.getJSON("/obtenerClaveDeAcceso/"+ sala + "/" + user,function (data) {
					callback(data);
					
				}
			);
		},
		logosComprados : function (user,callback) {
	
			$.getJSON("/logosComprados/"+ user ,function (data) {
					callback(data);
					
				}
			);
		},
		obtenerCartaJugadores : function (user,sala,callback) {
	
			$.getJSON("/obtenerCartasJugadores/"+ sala + "/" + user ,function (data) {
					callback(data);
					
				}
			);
		},
		
		
		
                getUsuario : function (user,callback) {

			$.getJSON("/usuario/"+ user ,function (data) {
					callback(data);
				}
			);
		},
		getPerfilToken : function (user,callback) {

			$.getJSON("/perfil/token/"+ user ,function (data) {
					callback(data);
				}
			);
		},
		getUsuarioToken : function (user,callback) {

			$.getJSON("/usuario/token/"+ user ,function (data) {
					callback(data);
				}
			);
		},
		getPrincipal : function (token,user,callback) {

			$.getJSON("/usuario/token/"+token+"/"+ user ,function (data) {
					callback(data);
				}
			);
		},

		logrosObtenidos : function (callback) {
			let user = window.localStorage.usuario;
			
			$.getJSON("/logrosObtenidos/"+ user,function (data) {
					
					callback(data);
				}
			);
		},
		getLogros : function (callback) {
			let user = window.localStorage.usuario;
			$.getJSON("/getLogros/"+ user ,function (data) {
					callback(data);
				}
			);
		},
		
		getMejoresJugadores : function (callback) {
			$.getJSON("/getMejoresJugadores" ,function (data) {
					callback(data);
				}
			);
		},
		
		 
		hacerPost : function(){
			let nickname=$("#nickname").val()
			let pass=$("#contrasena").val()
			
			const promise = $.post({
				url: "/auth/login",
				contentType: "application/json",
				data: "{\"nickname\":\"" + nickname + "\",\"contrasena\":\"" + pass + "\"}",
			});
			promise.then(function (data) {
			
	
				localStorage.removeItem("usuario")

				localStorage.setItem("usuario",data)

				window.location.href="/menu.html";
            }, function (error) {
                alert("El usuario o la contraseña son incorrectas")
            }
			
        );
		},

		postRegistro : 	function(){
			let nickname=$("#nickname").val()
			let pass=$("#contrasena1").val()
			let name=$("#nombre").val()
			let correo=$("#correo1").val()
		
			const promise = $.post({
				url: "/auth/register",
				contentType: "application/json",
				data: "{\"nickname\": \"" + nickname + "\",\"contrasena\":\"" + pass + "\",\"name\":\"" + name + "\",\"correo\":\"" + correo + "\"}",
			});
			promise.then(function (data) {
			
                if(data=="Usuario creado"){
					

					window.location.href="/bienvenido.html";					

				}
				else{
					$("#nickname").after('<small class="error">nickname ya existe.</small>');
					alert("No puede registrar a este usuario")
				}
            }, function (error) {

				$("#nickname").after('<small class="error">nickname ya existe.</small>');
				window.location.href="#nickname";
                alert("No se pudo crear el usuario")

            })
		},
		
		
		
		crearSala: function(usuario){
			let nickname=$("#name").val()
			let pass=$("#contrasena1").val()
			let cd=$("#cdinero").val()
			var datos={valorsala:cd, nombre:nickname,clave:pass,jugadores:[usuario]};
			datos = JSON.stringify(datos);
			const promise = $.post({
				url: "/salas",
				contentType: "application/json",
				data: datos
			});
			promise.then(function (data) {
				window.location.href="/salaDeEspera.html?name="+nickname;
				
                
            }, function (error) {
				alert("El nombre de la sala ya existe");
				
                

            })
		},
		
		
		
		anadirJugador: function(sala){
			
			let pass=$("#contrasena").val()
			let usuario = window.localStorage.usuario;
			var datos=usuario;
			const promise = $.post({
				url: "/nuevoJugador/"+sala+"/"+pass,
				contentType: "application/json",
				type:'PUT',
				data: datos
			});
			promise.then(function (data) {
				
				window.location.href="/salaDeEspera.html?name="+sala;
				
                
            }, function (data,error) {

				
                alert("Contraseña incorrecta o el dinero no es suficiente");

            })
		},
		
		
		investigarSala : function (sala,callback) {
			
			$.getJSON("/investigarSala/"+ sala ,function (data,error) {
					
					callback(data,error);
			});
		},
		
		getSalasPublicas : function(callback){
			$.getJSON("/salasPublicas" ,function (data) {
					
					callback(data);
			});
		},
		comprarLogo : function(recurso,user){
			let promise = new Promise( (resolve, reject) => {

	
				var datos={usuario:user, tienda : recurso};
				
				datos = JSON.stringify(datos);
				
				var putPromise = $.ajax({
				url: "/comprarLogo",
				type: 'POST',
				data: datos,
				contentType: "application/json"
				
			});
			resolve(putPromise);
			});
			
			promise.then((resultado) =>{
				
			}).catch((error) => {
				alert("No tiene el dinero suficiente");
			});
			
			return promise;
		},
		
		actualizarDatos : function(nombre,correo,nickname){
			let promise = new Promise( (resolve, reject) => {
				var datos={name:nombre, correo : correo};
				datos = JSON.stringify(datos);
				
				
				var putPromise = $.ajax({
				url: "/actualizarDatos/" + nickname,
				type: 'PUT',
				data: datos,
				contentType: "application/json"
			});
			resolve(putPromise);
			});
			
			return promise;
		},
		
		actualizarDatosPerfil : function(imagenP,nickname){
			let promise = new Promise( (resolve, reject) => {
				var datos={imagen_perfil:imagenP};
				datos = JSON.stringify(datos);
				
				
				var putPromise = $.ajax({
				url: "/actualizarDatosPerfil/" + nickname,
				type: 'PUT',
				data: datos,
				contentType: "application/json"
			});
			resolve(putPromise);
			});
			
			return promise;
		},
		
		privacidadSala : function(mesa){
			let promise = new Promise( (resolve, reject) => {
			
		
				
				var putPromise = $.ajax({
				url: "/privacidadSala/" + mesa,
				type: 'PUT',
				
				contentType: "application/json"
			});
			resolve(putPromise);
			});
			
			return promise;
		},
		
		eliminarJugador : function(sala,usuario){
			
			var datos=usuario;
			const promise = $.post({
				url: "/eliminarJugador/"+sala,
				contentType: "application/json",
				type:'PUT',
				data: datos
			});
			promise.then(function (data) {
				
				window.location.href="/salasPublicas.html";
				
                
            }, function (error) {

				
                alert("Error al   salir de la partida")
				window.location.href="/menu.html";

            })
		},
		comenzarJuego : function(sala){
			
			const promise = $.post({
				url: "/comenzarJuego/",
				contentType: "application/json",
				type:'POST',
				data: sala
			});
			promise.then(function (data) {
			

				
                
            }, function (error) {

				
                alert("No se pude comenzar juego")

            })
		},
		
		investigarPokerPlayer : function (sala,user,callback) {
	
			$.getJSON("/obtenerPlayer/"+ sala + "/" + user ,function (data) {
			
					callback(data);
					
				}
			);
		},
		obtenerMesa : function (sala,callback) {
	
			$.getJSON("/obtenerMesa/"+ sala ,function (data) {
			
					callback(data);
					
				}
			);
		},
		pasarJugador : function(sala){
			
			const promise = $.post({
				url: "/pasarJugador/"+sala+"/"+window.localStorage.usuario,
				contentType: "application/json",
				type:'PUT'
			});
			promise.then(function (data) {
			
                
            }, function (error) {

                alert("No es tu turno")

            })
		},
		apostar : function(sala,apuesta){
			
			var datos=apuesta;
			const promise = $.post({
				url: "/apostarJuego/"+sala+"/"+window.localStorage.usuario,
				contentType: "application/json",
				type:'PUT',
				data: apuesta
			});
			promise.then(function (data) {
			
				
                
            }, function (error) {

				
                alert("No tienes la cantidad suficiente o es muy poco dinero")

            })
		},
		abandonarJuego : function(sala){
			
			const promise = $.post({
				url: "/abandonarJuego/"+sala+"/"+window.localStorage.usuario,
				contentType: "application/json",
				type:'PUT'
			});
			promise.then(function (data) {
				
				
                
            }, function (error) {

				
                alert("No es tu turno")

            })
		},
		
		recibirMensaje : function (sala,user,callback) {
	
			$.getJSON("/recibirMensaje/"+ sala + "/" + user ,function (data) {
			
					callback(data);
					
				}
			);
		},
		
		nuevoMensaje : function(sala,mensaje){
			
			var datos=mensaje;
			const promise = $.post({
				url: "/nuevoMensaje/"+sala,
				contentType: "application/json",
				type:'PUT',
				data: datos
			});
			promise.then(function (data) {
            }, function (error) {

				
                alert("Error al enviar el mensaje")

            })
		}
		
		
		
		
	}
})();

