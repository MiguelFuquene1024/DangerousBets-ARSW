var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
function cambio(){

	api.investigarSala($("#sala").val(),function(data){
	
		api.getPerfilToken(window.localStorage.usuario,function(perfil){
			if(data!=null){
				$("#valorSala").html(data.valorsala);
				if(data.publico){
					$("#privacidadSala").html("publica");
				}else{
					$("#privacidadSala").html("privada");
				}
				if(perfil.moneda<data.valorsala){
					$(".error").remove();
					$("#valorSala").after('<small class="error">No tienes el dinero suficiente</small>');
				}
			}else{
				$("#privacidadSala").html("No existe Sala");	
			}				
			
		});		
	});		
}
	
$(document).ready(function(){
	$("#restablecer").click(function(){
		$('input[type="text"]').val('');
		$('input[type="password"]').val('');
		$('input[type="number"]').val('');
	});
	$("#submit2").click(function(){
		let salas=$("#sala").val();
		api.anadirJugador(salas);
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

		api.getPerfilToken( window.localStorage.usuario,function(data){
		
			if(cd>data.moneda){
				
				$("#cdinero").after('<small class="error">No tienes suficiente dinero.</small>');
				
				flag = false;
			}if(flag){
				api.getPerfilToken(window.localStorage.usuario,function(data){
					api.crearSala(data.nickname);
				});
			}
		
		
		
		});
	
	
	});
});
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

