/**
 * Created by Administrator on 2017/5/26.
 */

    'use strict';
    app.controller('dataLinkCtrl', [
        '$scope', '$filter', function($scope, $filter) {
            var init;
            $scope.links = [
                {
                    name: '商品数据分析',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '商品数据分析2',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '商品数据分析3',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '电商数据1',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '电商数据2',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '电商数据3',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }, {
                    name: '电商数据4',
                    type: 'MySQL',
                    createTime: '2017-05-26 11:20:10',
                    modifyTime: '2017-05-26 18:20:10'
                }
            ];
            $scope.searchKeywords = '';
            $scope.filteredLinks = [];
            $scope.row = '';
            $scope.select = function(page) {
                var end, start;
                start = (page - 1) * $scope.numPerPage;
                end = start + $scope.numPerPage;
                return $scope.currentPageLinks = $scope.filteredLinks.slice(start, end);
            };
            $scope.onFilterChange = function() {
                $scope.select(1);
                $scope.currentPage = 1;
                return $scope.row = '';
            };
            $scope.onNumPerPageChange = function() {
                $scope.select(1);
                return $scope.currentPage = 1;
            };
            $scope.onOrderChange = function() {
                $scope.select(1);
                return $scope.currentPage = 1;
            };
            $scope.search = function() {
                $scope.filteredLinks = $filter('filter')($scope.links, $scope.searchKeywords);
                return $scope.onFilterChange();
            };
            $scope.order = function(rowName) {
                if ($scope.row === rowName) {
                    return;
                }
                $scope.row = rowName;
                $scope.filteredLinks = $filter('orderBy')($scope.links, rowName);
                return $scope.onOrderChange();
            };
            $scope.numPerPageOpt = [3, 5, 10, 20];
            $scope.numPerPage = $scope.numPerPageOpt[2];
            $scope.currentPage = 1;
            $scope.currentPageLinks = [];
            init = function() {
                $scope.search();
                return $scope.select($scope.currentPage);
            };
            return init();
        }
    ]);

