var api = apiclient;

if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}
console.log(window.localStorage.usuario);
var dinero=api.getPerfilToken(window.localStorage.usuario,function(plata){
	console.log(plata);
	$("#cantidadDeMonedas").html(plata.moneda);
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

