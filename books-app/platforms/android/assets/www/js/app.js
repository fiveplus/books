// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic','ngCordova', 'starter.controllers','starter.services','starter.sql'])
.run(function($ionicPlatform,$rootScope,$cordovaSQLite ,$location,$timeout,$ionicHistory,$state,$http,$ionicLoading) {
  $rootScope.$on('loading:show', function() {
    $ionicLoading.show({template: 'loading...'});
  });
  $rootScope.$on('loading:hide', function() {
    $ionicLoading.hide();
  });
     
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
                      
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
    var db = null;
    //数据表创建 primary key
    var sql_book = "CREATE TABLE IF NOT EXISTS tbl_book (id integer, book_name text, contents text,create_time text, picture text, page integer );";
    var sql_bookshelves = "CREATE TABLE IF NOT EXISTS tbl_bookshelves (id integer primary key , book_name text,create_time text, picture text);";
    
    if(window.cordova){
        //app是否运行
        //var isActive = notification.notification;
        if(ionic.Platform.isIOS()){
            //window.alert(notification);
            db = $cordovaSQLite.openDB({ name: "books.db",iosDatabaseLocation:"default" });
        }else{
            //Android
            db = $cordovaSQLite.openDB({ name: "books.db",location:"default" });
        }
                       
        $cordovaSQLite.execute(db, sql_book);
        $cordovaSQLite.execute(db, sql_bookshelves);
    }else{
        db = window.openDatabase("books.db", '1', 'books', 1024 * 1024 * 100); // browser
        db.transaction(function(tx){
            tx.executeSql(sql_book);
            tx.executeSql(sql_bookshelves);
        });
    }
    
    //双击退出
    $ionicPlatform.registerBackButtonAction(function(e){
    //判断哪个页面退出
    //$rootScope.backButtonPressedOnceToExit ＝ false;
    if($location.path()=='/app/playlists'){
        if($rootScope.backButtonPressedOnceToExit){
            ionic.Platform.exitApp();
        }else{
            $rootScope.backButtonPressedOnceToExit = true;
            $cordovaToast.showShortBottom('再按一次退出系统');
            setTimeout(function(){
              $rootScope.backButtonPressedOnceToExit = false;
            }, 2000);
        }
    }else if($ionicHistory.backView()){
        $ionicHistory.goBack();
    }else{
        $rootScope.backButtonPressedOnceToExit = true;
        $cordovaToast.showShortBottom('再按一次退出系统');
        setTimeout(function () {
            $rootScope.backButtonPressedOnceToExit = false;
        }, 2000);
    }
        e.preventDefault();
        return false;
    },101);
                       
    setTimeout(function () {
        navigator.splashscreen.hide();
    }, 1000);

  });
     
})
.config(function($httpProvider){
    //设置跨域访问
    //$httpProvider.defaults.withCredentials = true;
    //$httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    //禁用页面缓存
    //$httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    //$httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
    // $httpProvider.defaults.withCredentials = true;
    //转换序列格式
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data){
    /**
      * The workhorse; converts an object to x-www-form-urlencoded serialization.
      * @param {Object} obj
      * @return {String}
      */
        var param = function(obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;
                                                   
            for (name in obj) {
                value = obj[name];
                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
                }
            }
            return query.length ? query.substr(0, query.length - 1) : query;
        };
                                                   
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
                                                   
    }];

    $(function($rootScope) {
        return {
            request: function(config) {
                $rootScope.$broadcast('loading:show');
                return config;
            },
            response: function(response) {
                $rootScope.$broadcast('loading:hide');
                return response;
            }
        };
    });
})
.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
  //resolve事件函数- 路由用
    
  $stateProvider
  .state('app',{
    url:'/app',
    abstract:true,
    templateUrl:'templates/menu.html',
    controller:'AppCtrl'
  })
  .state('app.index',{
    url: '/index',
    views: {
        'menuContent': {
            templateUrl: 'templates/index.html',
            controller: 'IndexCtrl'
        }
    }
  })
  .state('app.books',{
    url: '/books',
    views: {
        'menuContent': {
            templateUrl: 'templates/books.html',
            controller: 'BooksCtrl'
        }
    }
  })
  .state('app.search', {
    url: '/search',
    views: {
      'menuContent': {
        templateUrl: 'templates/search.html',
        controller: 'SearchCtrl'
      }
    }
  })
  .state('app.browse', {
      url: '/browse',
      views: {
        'menuContent': {
          templateUrl: 'templates/browse.html'
        }
      }
    })
  .state('app.types',{
    url: '/types/:typeId/:imgName',
    views:{
      'menuContent': {
         templateUrl: 'templates/types.html',
         controller: 'TypesCtrl'
      }
    }
  })
  .state('app.single', {
    url: '/playlists/:bookId',
    views: {
      'menuContent': {
        templateUrl: 'templates/playlist.html',
        controller: 'PlaylistCtrl'
      }
    }
  })
  .state('app.booktypes',{
    url:'/booktypes',
    views:{
      'menuContent':{
        templateUrl:'templates/booktypes.html',
        controller:'BookTypeCtrl'
      }
    }
  })
  .state('app.typelist',{
    url:'/typelist/:typeId',
    views:{
      'menuContent':{
         templateUrl:'templates/typelist.html',
         controller:'TypelistCtrl'
      }
    }
  })
  .state('app.reader',{
    cache: false,
    url:'/reader/:bookId',
    views:{
      'menuContent':{
         templateUrl:'templates/reader.html',
         controller:'ReaderCtrl'
      }
    }
  })
  .state('app.recommend',{
    url:'/recommend',
    views:{
      'menuContent':{
         templateUrl:'templates/recommend.html',
         controller:'RecommendCtrl'
      }
    }
  })
  .state('app.free',{
    url:'/free',
    views:{
      'menuContent':{
         templateUrl:'templates/free.html',
         controller:'FreeCtrl'
      }
    }
  })
  .state('app.newbooks',{
    url:'/newbooks',
    views:{
        'menuContent':{
          templateUrl:'templates/newbooks.html',
          controller:'NewBooksCtrl'
        }
    }
  })
  .state('app.bookshelves',{
    url:'/bookshelves',
    views:{
      'menuContent':{
         templateUrl:'templates/bookshelves.html',
         controller:'BookShelvesCtrl'
      }
    }
  })
  .state('app.development',{
    url:'/development',
    views:{
      'menuContent':{
         templateUrl:'templates/development.html',
         controller:'DevelopmentCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/index');
});
