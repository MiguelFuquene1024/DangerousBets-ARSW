
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
		logosComprados : function (user,callback) {
	
			$.getJSON("/logosComprados/"+ user ,function (data) {
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
		logrosObtenidos : function (callback) {
			let user = window.localStorage.usuario;
			console.log(user);
			$.getJSON("/logrosObtenidos/"+ user,function (data) {
					console.log("Empezando a creer");
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
				console.log(data)
				//console.log(data.nombreUsuario)
				//console.log(data.authorities[0].authority)
				localStorage.removeItem("usuario")
				//localStorage.removeItem("token")
				//localStorage.removeItem("authority")
				localStorage.setItem("usuario",data)
				//localStorage.setItem("token",data.token)
				//localStorage.setItem("authority",data.authorities[0].authority)
				window.location.href="/menu.html";
            }, function (error) {
                alert("No se pudo conectar")
            }
			
        );
		},

		postRegistro : 	function(){
			let nickname=$("#nickname").val()
			let pass=$("#contrasena1").val()
			let name=$("#nombre").val()
			let correo=$("#correo1").val()
			console.log(pass);
			const promise = $.post({
				url: "/auth/register",
				contentType: "application/json",
				data: "{\"nickname\": \"" + nickname + "\",\"contrasena\":\"" + pass + "\",\"name\":\"" + name + "\",\"correo\":\"" + correo + "\"}",
			});
			promise.then(function (data) {
				console.log(data)
                if(data=="Usuario creado"){
					console.log(data)

					window.location.href="/bienvenido.html";					

				}
				else{
					$("#nickname").after('<small class="error">nickname ya existe.</small>');
					alert("No puede registrar a este usuario")
				}
            }, function (error) {

				$("#nickname").after('<small class="error">nickname ya existe.</small>');
                alert("No se pudo crear el usuario")

            })
		},
		
		
		
		crearSala: function(){
			let nickname=$("#name").val()
			let pass=$("#contrasena1").val()
			let cd=$("#cdinero").val()
			var datos={valorsala:cd, nombre:nickname,clave:pass,jugadores:[window.localStorage.usuario]};
			datos = JSON.stringify(datos);
			const promise = $.post({
				url: "/salas",
				contentType: "application/json",
				data: datos
			});
			promise.then(function (data) {
				window.location.href="/salaDeEspera.html?name="+nickname;
				console.log(data)
                
            }, function (error) {

				
                alert("El nombre de la sala ya existe")

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
				console.log(data)
				window.location.href="/salaDeEspera.html?name="+sala;
				
                
            }, function (data,error) {

				
                alert("No se pudo acceder a la sala");

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
				alert("No se puede comprar");
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
				console.log(data)
				window.location.href="/salasPublicas.html";
				
                
            }, function (error) {

				
                alert("No se pude anadir jugador")

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
				console.log(data)

				
                
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
				url: "/pasarJugador/"+sala,
				contentType: "application/json",
				type:'PUT'
			});
			promise.then(function (data) {
				console.log(data)
                
            }, function (error) {

                alert("No se pude anadir jugador")

            })
		},
		apostar : function(sala,apuesta){
			
			var datos=apuesta;
			const promise = $.post({
				url: "/apostarJuego/"+sala,
				contentType: "application/json",
				type:'PUT',
				data: apuesta
			});
			promise.then(function (data) {
				console.log(data)
				
                
            }, function (error) {

				
                alert("No se pude anadir jugador")

            })
		},
		abandonarJuego : function(sala){
			
			const promise = $.post({
				url: "/abandonarJuego/"+sala,
				contentType: "application/json",
				type:'PUT'
			});
			promise.then(function (data) {
				console.log(data)
				
                
            }, function (error) {

				
                alert("No se pude anadir jugador")

            })
		}
		
		
		
		
	}
})();