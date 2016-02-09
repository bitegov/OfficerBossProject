angular.module('starter.controllers')
.controller('DocumentDetailCtrl', function($scope, $stateParams,$ionicHistory, $http,$window, FileService) {
  $ionicHistory.nextViewOptions({
    disableBack: true
  });

  $http.get('http://localhost:8083/getreviewbydocumentid?documentid='+$stateParams.docId)
    .success(function(data){
      $scope.review = data;
      
      

    })
    .error(function(data){
      console.log('cannot reach review-service port 8083')
    });

    $scope.download = function(){
          FileService.download($stateParams.docId);
    }

  $http.get('http://localhost:8081/getdocument?documentid='+$stateParams.docId)
    .success(function(data){
      $scope.doc = data;
      
      $http.get('http://localhost:8082/getuser?userid='+$scope.doc.creator)
        .success(function(data){
          $scope.creator = data;
            
        })
        .error(function(data){
          console.log('cannot reach user-service port 8082')
        });
      $http.get('http://localhost:8082/getuser?userid='+$scope.doc.approver)
        .success(function(data){
          $scope.approver = data;
            
        })
        .error(function(data){
          console.log('cannot reach user-service port 8082')
        });

       // TODO Complete Refactor this code 
        // $scope.filename = FileService.getFileDetail($scope.doc.documentId);

      $http.get('http://localhost:8084/filedetail?documentId='+$scope.doc.documentId)
        .success(function(data){
          $scope.filename = data;
            
        })
        .error(function(data){
          console.log('cannot reach file-service port 8084')
          console.log(data)
        });


      

    })
    .error(function(data){
      console.log('cannot reach document-service port 8081')
    });
  

    $scope.publish = function(id){
      $http.get('http://localhost:8081/publish?documentid='+id)
        .success(function(data){
          console.log('successfully publish document');
          $window.location.href=('#/app/doclist');

        })
        .error(function(data){
          console.log('cannot reach document-service port 8081')
        });
    }

    $scope.delete = function(id){
      $http.get('http://localhost:8081/delete?documentid='+id)
        .success(function(data){
          console.log('successfully delete document');
          $window.location.href=('#/app/doclist');

        })
        .error(function(data){
          console.log('cannot reach document-service port 8081')
        });
    }

    $scope.submit = function(docid){
      $http.get('http://localhost:8081/submit?documentid='+docid)
        .success(function(data){
          $scope.savedoc = data;
          console.log('successfully submit document: change from draft to waiting for approval');
          $window.location.href=('#/app/doclist');

        })
        .error(function(data){
          console.log('cannot reach document-service port 8081')
        });

    }

    

})