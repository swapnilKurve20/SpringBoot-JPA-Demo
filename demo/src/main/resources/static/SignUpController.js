var SignUpController = function($scope,$rootScope,SignUpService,$state) {

	$scope.user={};
	$scope.profileId="";	
	
	$scope.saveDetails = function(userForm) {
		if(userForm.$valid) {
		$scope.user;
		/*debugger;*/
		SignUpService.saveUser($scope.user).then(function(success) {
			console.log("registered USER ID "+success);
			//userId in response				
			$rootScope.loggedInUser = $scope.user
			$rootScope.loggedInUserId = success
			$scope.getUserById(success);
			
			
		},function(failure) {
			alert("Sign Up Failed...");
			console.log(failure);
		});	
		}
		else
			{
			alert("Enter Valid Details");
			}
	}
	$scope.getUserById = function(userId) {
		
		SignUpService.getUserById(userId).then(function(response) {
			/*
			var key = "profileId";
			var value = response.gender;
			$scope.user[key] = value;*/
			console.log("find by id == "+response);
			console.log("find by id profilesDto ID== "+response.profilesDto.id);
			$rootScope.profileId = response.profilesDto.id;
			alert("Sign Up Successful...");
			$state.go('profile');
			},function(failure) {
			console.log(failure);
		});	
		
	}
}
appName.controller("SignUpController", SignUpController);


