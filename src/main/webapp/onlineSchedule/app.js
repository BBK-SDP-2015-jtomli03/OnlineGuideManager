'use strict';

var app = angular.module('myApp', ['ngDialog']);

app.controller('GuideController', function($scope, $http, $interval, ngDialog){

    // default schedule if no programmes have been uploaded yet
    var defaultMessage = "No programmes available";
    var defaultSchedule = [{
        "programme": defaultMessage,
        "width": 98,
        "align": "left",
        "paddingL": 2
    }];

    // channels initialized to the default schedule
    $scope.sean = defaultSchedule;
    $scope.george = defaultSchedule;
    $scope.roger = defaultSchedule;
    $scope.timothy = defaultSchedule;
    $scope.pierce = defaultSchedule;
    $scope.daniel = defaultSchedule;

    // periodically checks the server for updates to the schedule and update channels accordingly
    $interval(function(){
        $http.get('http://localhost:8080/programmes/bond')
            .success(function(response) {
                if(response.seanChannel.length > 0) {
                    $scope.sean = response.seanChannel;
                }
                if(response.georgeChannel.length > 0){
                    $scope.george = response.georgeChannel;
                }
                if(response.rogerChannel.length > 0){
                    $scope.roger = response.rogerChannel;
                }
                if(response.timothyChannel.length > 0){
                    $scope.timothy = response.timothyChannel;
                }
                if(response.pierceChannel.length > 0){
                    $scope.pierce = response.pierceChannel;
                }
                if(response.danielChannel.length > 0){
                    $scope.daniel = response.danielChannel;
                }
            })
            .error(function(data, status, headers, config) {
                console.log("Error - please refresh the page.")
            });
    },2000);

    // opens a dialog box on clicking a movie to confirm remote record
    $scope.record = function(programme) {
        if(programme != "No programmes available") {
            ngDialog.open({
                class: 'ngDialog-theme-default',
                template: 'recordingMessage.html',
                scope: $scope,
                data: programme
            })
        }
    };

});
