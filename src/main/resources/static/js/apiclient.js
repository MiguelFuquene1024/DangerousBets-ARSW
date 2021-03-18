var apiclient = (function () {
  
	return {
		getMonedas : function (callback) {
			$.getJSON("/monedas" ,function (data) {
					callback(data);
				}
			);
		}

	};

})();