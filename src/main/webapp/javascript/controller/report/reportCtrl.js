/**
 * Created by yfyuan on 2016/8/2.
 */
'use strict';
app.controller('reportCtrl', function ($scope, $http, $filter, $timeout) {
    $('body').on('click',function(e){
        if( e.target !== $('.m-overlay') &&  e.target !==$('#addItem')){
            $('.m-overlay').addClass('hide');
        }
    });
    $scope.treeData = [{"id":"root","parent":"#","text":"Root","state":{"opened":true}},{"id":"parent1","parent":"root","text":"我板"},{"id":"1","parent":"parent1","text":"图表门户","icon":"glyphicon glyphicon-file"}];
    $scope.treeConfig =jsTreeConfig;
    $scope.reCreateTree = function() {
        this.treeConfig.version++;
    }
    var  originalData  =[];
    $scope.loadReportList = function(){
        $http.get("/rest/dashboard/getBoardList").success(function (response) {
            $scope.boardList = response;
            originalData = jstree_CvtVPath2TreeData(
                $scope.boardList.map(function(ds) {
                    var categoryName = ds.categoryName == null ? translate('CONFIG.DASHBOARD.MY_DASHBOARD') : ds.categoryName;
                    return {
                        "id": ds.id,
                        "name": ds.name,
                        "categoryName": categoryName
                    };
                })
            );
            $scope.treeData = originalData;
            $scope.treeConfig.version++;
        });
    }
});