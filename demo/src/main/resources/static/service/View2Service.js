appName.service("View2Service",function($http,$q,RESOURCES){
	
	var urlBase = RESOURCES.PROD_DOMAIN;
	
	this.savePost = function(post,profileId){		

		var defer = $q.defer();
		return $http.post(urlBase+'/post/'+profileId,JSON.stringify(post))		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};

})