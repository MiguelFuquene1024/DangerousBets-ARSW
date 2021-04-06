var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function cambio(){

		api.investigarSala($("#sala").val(),function(data){

					$("#valorSala").html(data.valorsala);
					if(data.publico){
						$("#privacidadSala").html("publica");
					}else{
						$("#privacidadSala").html("privada");
					}
			
		});
		
		
	};
	
$(document).ready(function(){
	$("#restablecer").click(function(){
		$('input[type="text"]').val('');
		$('input[type="password"]').val('');
		$('input[type="number"]').val('');
	});
	$("#submit2").click(function(){
		let nickname=$("#sala").val();
		api.anadirJugador(nickname);
	});
	$("#submit").click(function(){
		$(".error").remove();
		let na = $("#name").val();
		let pass = $("#contrasena1").val();
		let cd = $("#cdinero").val();
		var flag = true;

		if(na.length<4){
			$("#name").after('<small class="error">Nombre debe ser mínimo de 4 letras.</small>');
			flag = false;
		}
		if(pass.length<4){
			$("#contrasena1").after('<small class="error">la clave debe ser mínimo de 4 letras.</small>');
			flag = false;
		}
		
		if(cd<0){
			$("#cdinero").after('<small class="error">La cantidad de dinero debe ser positiva.</small>');
			flag = false;
		}

		api.getMonedas( window.localStorage.usuario,function(data){

			if(cd>data){
				
				$("#cdinero").after('<small class="error">No tienes suficiente dinero.</small>');
				
				flag = false;
			}if(flag){
				api.crearSala();
			}
		})
		
		
	});
	
	
});
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

