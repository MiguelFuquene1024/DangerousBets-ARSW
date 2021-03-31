var api = apiclient;
var dinero=api.getMonedas(function(plata){
$("#cantidadDeMonedas").html(plata);

//$("#nickname").html(localStorage.getItem("usuario"))
});
var logrosOb=api.logrosObtenidos(function(numeroLogros){
console.log("holaMundo");
console.log(numeroLogros);
$("#racha_trofeos").html(numeroLogros+"/10");
//$("#nickname").html(localStorage.getItem("usuario"))


//var nick=$("#nickname").html(sessionStorage.getItem("usuario"))

$(document).ready(function(){	
	//$("#crearSala").click(function(){
	//	alert("Crear Sala");
	//});
	//$("#unirseSala").click(function(){
	//	alert("unirse a Sala");
	//});
	//$("#ranking").click(function(){
		//alert("ranking");
	//});
	//$("#logros").click(function(){
		//alert("logros");
	//});
	//$("#tienda").click(function(){
		//alert("tienda");
	//});
});
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

