var api = apiclient;
if (window.localStorage.usuario!=undefined){
	window.location.href="/menu.html";
}

$(document).ready(function(){	
	$("#restablecer").click(function(){
		$('input[type="text"]').val('');
		$('input[type="password"]').val('');
	});
	$("#submit").click(function(){
		$(".error").remove();
		var nombre = $("#nombre").val();
		var nickname = $("#nickname").val();
		var correo1 = $("#correo1").val();
		var correo2 = $("#correo2").val();
		var contrasena1 = $("#contrasena1").val();
		var contrasena2 = $("#contrasena2").val();
		re=/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
		let falg=true;
	
		if(nombre.length<5){
			$("#nombre").after('<small class="error">Nombre demasiado corto</small>');
			falg=false;
		}
		if(!re.exec(correo1)){
			$("#correo1").after('<small class="error">Correo es invalido</small>');
			falg=false;
		}
		if(correo1!=correo2){
			$("#correo2").after('<small class="error">No coinciden los correos</small>');
			falg=false;
	
		}
		if(contrasena1!=contrasena2){
			$("#contrasena2").after('<small class="error">Las contrasenas no coinciden</small>');
			falg=false;
		
		}
		if(contrasena1.length<8){
			$("#contrasena1").after('<small class="error">contrasena insegura</small>');
			falg=false;
		
		}

		if(nickname.length<3 || nickname.length>25){
			$("#nickname").after('<small class="error">nickname no cumple con los requisitos</small>');
			falg=false;
	
		}
		if(falg){
			api.postRegistro(nickname,contrasena1,name,correo1);
		}


	});
});
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

