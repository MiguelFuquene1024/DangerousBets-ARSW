var api = apiclient;
var nombreFlag=false;
var nicknameFlag=false;
var correo1Flag=false;
var correo2Flag=false;
var contrasena1Flag=false;
var contrasena2Flag=false;
if (window.localStorage.usuario!=undefined){
	window.location.href="/menu.html";
}
function verificar_nombre(){
	var nombre = $("#nombre").val();
	$(".error_nombre").remove();
	if(nombre.length<5){
		$("#nombre").after('<small class="error error_nombre">Nombre demasiado corto</small>');
		nombreFlag=false;
	}else{
		nombreFlag=true;
	}
}

function verificar_nickname(){
	var nickname = $("#nickname").val();
	$(".error_nickname").remove();
	if(nickname.length<3 && nickname.length>25){
		$("#nickname").after('<small class="error error_nickname">nickname no cumple con los requisitos</small>');
		nicknameFlag=false
	}else{
		nicknameFlag=true;
	}
}
function verificar_correo(){
	var correo1 = $("#correo1").val();
	$(".error_correo1").remove();
	re=/^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
	if(!re.exec(correo1)){
		$("#correo1").after('<small class="error error_correo1">Correo es invalido</small>');
		correo1Flag=false;
	}else{
		correo1Flag=true;
	}
	verificar_confirmacion_correo();
}
function verificar_confirmacion_correo(){
	var correo1 = $("#correo1").val();
	var correo2 = $("#correo2").val();
	$(".error_correo2").remove();
	if(correo1!=correo2){
		$("#correo2").after('<small class="error error_correo2">No coinciden los correos</small>');
		correo2Flag=false;
	}else{
		correo2Flag=true;
	}
}
function verificar_clave(){
	var contrasena1 = $("#contrasena1").val();
	$(".error_clave").remove();
	if(contrasena1.length<8){
		$("#contrasena1").after('<small class="error error_clave">contrasena insegura</small>');
		contrasena1Flag=false;
	}else{
		contrasena1Flag=true;
	}
	verificar_confirmacion_clave();
}
function verificar_confirmacion_clave(){
	var contrasena1 = $("#contrasena1").val();
	var contrasena2 = $("#contrasena2").val();
	$(".error_clave2").remove();
	if(contrasena1!=contrasena2){
		$("#contrasena2").after('<small class="error error_clave2">Las contrasenas no coinciden</small>');
		contrasena2Flag=false;
	}else{
		contrasena2Flag=true;
	}
}

$(document).ready(function(){	
	$("#restablecer").click(function(){
		$('input[type="text"]').val('');
		$('input[type="password"]').val('');
	});
	
	$("#submit").click(function(){
		var falg=true;
		$(".error").remove();
		if(falg){
			api.postRegistro(nickname,contrasena1,name,correo1);
		}


	});
	
	setInterval(function(){
		console.log(nombreFlag);
		console.log(nicknameFlag);
		console.log(correo1Flag);
		console.log(correo2Flag);
		console.log(contrasena1Flag);
		console.log(contrasena2Flag);
		console.log("==================================================");
		if(nombreFlag && nicknameFlag && correo1Flag && correo2Flag && contrasena1Flag && contrasena2Flag){
			$("#submit").removeAttr("disabled");
		}else{
			$("#submit").attr("disabled","true");
		}
	},1000);
	
});
	
