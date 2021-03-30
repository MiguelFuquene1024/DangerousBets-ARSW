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
				url: "/login",
				contentType: "application/json",
				data: "{\"nickname\": \"" + nickname + "\",\"contrasena\":\"" + pass + "\"}",
			});
			promise.then(function (data) {
				console.log(data)
                if(data){
					window.location.href="/menu.html";
					sessionStorage.setItem("usuario",nickname)
				}
				else{
					alert("Clave Incorrecta")
				}
            }, function (error) {
                alert("No se pudo crear el blueprint")
            }
			
        );
		},

		postRegistro : 	function(){
			let nickname=$("#nickname").val()
			let pass=$("#contrasena").val()
			let name=$("#name").val()
			let correo=$("#correo1").val()
			const promise = $.post({
				url: "/registro",
				contentType: "application/json",
				data: "{\"nickname\": \"" + nickname + "\",\"contrasena\":\"" + pass + "\",\"name\":\"" + name + "\",\"correo\":\"" + correo + "\"}",
			});
			promise.then(function (data) {
				console.log(data)
                if(data){
					window.location.href="/login.html";					
				}
				else{
					alert("No puede registrar a este usuario")
				}
            }, function (error) {
                alert("No se pudo crear el blueprint")
            }
			
        )		

	}
}
})();