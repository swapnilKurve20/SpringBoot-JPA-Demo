appName.service("View3Service", function($http,$q,RESOURCES){
	
	var urlBase = RESOURCES.PROD_DOMAIN;

	this.getUserById = function(loggedInUserId){		

		var defer = $q.defer();
		return $http.get(urlBase+'/users?id='+loggedInUserId)		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};
})