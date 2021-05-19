/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var api = apiclient;
var lista={};
if (window.localStorage.usuario==undefined){
	window.location.href="/login.html";
}


async function noComprados(logos){
	lista={};
	$(".imagen").remove();
	console.log(logos);
	await api.getPerfil(window.localStorage.usuario,function(perfil){
		
		for(numero in logos){
			lista[logos[numero].id] = logos[numero];
			if(perfil.moneda>=logos[numero].valor){
				$("#menu").append('<div class="imagen"><img src="/estilos/imagenes/' + logos[numero].recurso +'" width="100%" height="100%"><button id="'+ logos[numero].id +'" type="button" class="btn btn-success boton_imagen comprar">$'+ logos[numero].valor +'</button></div>');
			}else{
				$("#menu").append('<div class="imagen"><img src="/estilos/imagenes/' + logos[numero].recurso +'" width="100%" height="100%"><button id="'+ logos[numero].id +'" type="button" class="btn btn-success boton_imagen">$'+ logos[numero].valor +'</button></div>');
				$("#"+logos[numero].id).hover(function(){
				$(this).css("background-color", "#dc3545");
				$(this).html("No se puede comprar");
					}, function(){
						$(this).css("background-color", "#28a745");
						$(this).html(lista[$(this).attr("id")].valor);
					
				  });
			}
			
		}
		
		console.log(lista);
		
		$(".comprar").click(async function(){
				let promise=await api.comprarLogo(lista[$(this).attr("id")].recurso,window.localStorage.usuario);
				api.logosNoComprados(window.localStorage.usuario,noComprados);

		});
		
		
	});
	
		
}


var usuario = api.logosNoComprados(window.localStorage.usuario,noComprados);
		
		




