app.controller('signinController', ['$scope', '$state',function($scope,$state) {
      $scope.login = function(){
          $state.go('app.home');
      }
    }
]);
