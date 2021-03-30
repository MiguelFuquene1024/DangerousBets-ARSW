var apiclient = (function () {
  
	return {
		getMonedas : function (callback) {
			$.getJSON("/monedas" ,function (data) {
					callback(data);
				}
			);
		},

		getLogros : function (callback) {

			$.getJSON("/getLogros" ,function (data) {
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
			let name=$("#name").val()
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
					window.location.href="/login.html";					
				}
				else{
					alert("No puede registrar a este usuario")
				}
            }, function (error) {
                alert("No se pudo crear el usuario")
            }
			
        )		

	}
}
})();