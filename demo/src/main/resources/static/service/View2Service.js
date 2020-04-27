appName.service("View2Service",function($http,$q){
	
	this.savePost = function(post,profileId){		

		var defer = $q.defer();
		return $http.post('http://localhost:8080/post/'+profileId,JSON.stringify(post))		
			.then(function(response) {
				defer.resolve(response.data);
				return defer.promise;
			}, function(response) {
				defer.reject(response);
				return defer.promise;
			});
	};

})