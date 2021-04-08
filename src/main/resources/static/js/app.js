var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
console.log(window.localStorage.usuario);
var dinero=api.getMonedas(window.localStorage.usuario,function(plata){

	$("#cantidadDeMonedas").html(plata);
});
var logrosOb=api.logrosObtenidos(function(numeroLogros){

	$("#racha_trofeos").html(numeroLogros[0]+"/"+numeroLogros[1]);

});

//var nick=$("#nickname").html(sessionStorage.getItem("usuario"))

$(document).ready(function(){
	

	$("#cerrarSesion").click(function(){
			window.localStorage.clear();
			window.location.href="/login.html";
	});
	
	
});
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

