'use strict'
var webber = angular.module('webber', []);

webber.controller("mainCtl", function($scope, $http) {
	$scope.page = "createUser.html";
	$scope.tab = "inprocess.html";
	$scope.currlog = "Click on the file name to view the logs...";
	$scope.curfilename = "";
	
	if($scope.masterData == null) {
		$http({
			method : "GET",
			url : "/getConfig"
		}).then(function mySuccess(response) {
			$scope.masterData = response.data;
		}, function myError(response) {
			alert("Error loading the master data.");
		});
	}
	
	$scope.changeToPage = function(pageToChange) {
		if ( pageToChange == "dashboard.html" ) {
			$scope.getDashboardDetails();
		}
		$scope.page = pageToChange;
	};
	
	$scope.changeTab = function(tabToChange) {
		$scope.tab = tabToChange;
	};
	
	$scope.getInclude = function() {
		return "/views/" + $scope.page;
	};
	
	$scope.getLogTable = function() {
		return "/views/" + $scope.tab;
	};
	
	$scope.successmsg = function() {
		alert("Success. \nA process has been started to execute the task, please go to Dashboard tab for execution status.");
	};
	
	$scope.errormsg = function() {
		alert("Error. \n Unable to process your request due to an internal error. Please investigate logs or contact administrator !!!");
	};
	
	$scope.createUser = function(host, db, uname, passwd) {
		var testData = {host:host,
						db:db,
						uname:uname,
						passwd:passwd};
		$http({
			method : "POST",
			url : "/createUser",
			data: testData
		}).then(function mySuccess(response) {
			if(response.data.status == 'success') {
				$scope.successmsg();
			} else {
				$scope.errormsg();
			}
			resetForm("createUserForm");
		}, function myError(response) {
			$scope.errormsg();
		});
	};
	
	$scope.createDB = function(host, db, uname, passwd) {
		var testData = {host:host,
						db:db,
						uname:uname};
		$http({
			method : "POST",
			url : "/createDB",
			data: testData
		}).then(function mySuccess(response) {
			if(response.data.status == 'success') {
				$scope.successmsg();
			} else {
				$scope.errormsg();
			}
			resetForm("createDBForm");
		}, function myError(response) {
			$scope.errormsg();
		});
	};
	
	$scope.getDashboardDetails = function() {
		$http({
			method : "GET",
			url : "/getDashboardDetails"
		}).then(function mySuccess(response) {
			if(response.data.status == 'success') {
				$scope.dashboard = response.data;
			} else {
				$scope.errormsg();
			}
		}, function myError(response) {
			$scope.errormsg();
		});
	};
	
	$scope.getLog = function(index, state) {
		$scope.curfilename = $scope.dashboard[state][index].filename;
		var query = "filename=" + $scope.curfilename + "&pstate=" + state;
		$http({
			method : "GET",
			url : "/getLog?" + query
		}).then(function mySuccess(response) {
			if(response.data.status == 'success') {
				$scope.currlog = response.data.log;
			} else {
				$scope.errormsg();
			}
		}, function myError(response) {
			$scope.errormsg();
		});
	};
	
});