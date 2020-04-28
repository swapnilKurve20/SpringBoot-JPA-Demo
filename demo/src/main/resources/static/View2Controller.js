var view2Controller = function($scope,$rootScope, View2Service,$state) {

	$scope.firstName="";
	$scope.lastName = "";
	$scope.profileId = "";
	$scope.post={};
	
	
	$scope.getUserDetails = function() {
		
		
		console.log($rootScope.loggedInUserId);
		console.log($rootScope.profileId);
		/*debugger;*/
		$scope.firstName = $rootScope.loggedInUser.firstName;
		$scope.lastName =  $rootScope.loggedInUser.lastName;
		$scope.profileId =  $rootScope.profileId;
		
	}
	$scope.getUserDetails();
	$scope.savePost = function(postForm) {
		if(postForm.$valid) {
		$scope.post;
		console.log($scope.post);
		var string = $scope.post.tags;
		/*var arr = string.split(',');*/
		var arr = string.split(/#| #/);
		var filtered = arr.filter(function (el) {
			  return el != "";
			});
		console.log(filtered);
		var pluginArrayArg = new Array();
		filtered.forEach((value) => 
		/*$scope.post.tags['name'] = value*/
		{
			var jsonArg1 = new Object();
			jsonArg1.name = value;
			pluginArrayArg.push(jsonArg1);
		}
		);
		$scope.post.tags = pluginArrayArg;
		console.log($scope.post);
		debugger;
		View2Service.savePost($scope.post,$scope.profileId).then(function(success) {
			console.log(success);
			$scope.post='';
			$state.go('posts');
		},function(failure) {
			alert("Post Failure...");
			console.log(failure);
		});	
		}
		else
			{
			alert("Enter Valid Details");
			}
	}
	
}
appName.controller("View2Controller", view2Controller);
