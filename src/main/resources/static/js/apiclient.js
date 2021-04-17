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
				console.log(data.nombreUsuario)
				console.log(data.authorities[0].authority)
				localStorage.removeItem("usuario")
				localStorage.removeItem("token")
				localStorage.removeItem("authority")
				localStorage.setItem("usuario",data.nombreUsuario)
				localStorage.setItem("token",data.token)
				localStorage.setItem("authority",data.authorities[0].authority)
				window.location.href="/menu.html";
            }, function (error) {
                alert("No se pudo conectar")
            }
			
        );
		},

		postRegistro : 	function(){
			let nickname=$("#nickname").val()
			let pass=$("#contrasena").val()
			let name=$("#nombre").val()
			let correo=$("#correo1").val()
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
			const promise = $.post({
				url: "/salas",
				contentType: "application/json",
				data: "{\"valorsala\": \"" + cd +  "\",\"nombre\": \"" + nickname + "\",\"clave\":\"" + pass + "\"}",
			});
			promise.then(function (data) {
				window.location.href="/salaDeEspera.html?name="+nickname;
				console.log(data)
                
            }, function (error) {

				
                alert("No se puede crear sala")

            })
		},
		
		
		
		anadirJugador: function(nickname){
			
			let pass=$("#contrasena").val()
			let usuario = window.localStorage.usuario;
			const promise = $.post({
				url: "/nuevoJugador/"+name,
				contentType: "application/json",
				
				data: "{\"nombreSala\": \"" + nickname + "\",\"nickname\":\"" + usuario + "\",\"contrasena\":\"" + pass + "\"}",
			});
			promise.then(function (data) {
				console.log(data)
				window.location.href="/salaDeEspera.html?name="+nickname;
				
                
            }, function (error) {

				
                alert("No se pude anadir jugador")

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
			
			return promise;
		}
		
		
		
	}
})();