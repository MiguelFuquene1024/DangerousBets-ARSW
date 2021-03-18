var apiclient = (function () {
  
	return {
		cerrarSesion : function(){
		let promise = new Promise( (resolve, reject) => {
			var putPromise = $.ajax({
				url: "http://localhost:8080/logout",
				type: 'POST',
				contentType: "application/json"
			});
			resolve(putPromise);				 
			});
			return promise;
				
		}

	}	

})();