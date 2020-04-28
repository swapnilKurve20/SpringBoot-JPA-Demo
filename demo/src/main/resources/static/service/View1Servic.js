appName.service("View1Service", function($http,$q,RESOURCES){
	
	var urlBase = RESOURCES.PROD_DOMAIN;

	this.saveUserSubscription = function(user){		

		var defer = $q.defer();
		return $http.post(urlBase+'/saveusersubscription',JSON.stringify(user))		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
})