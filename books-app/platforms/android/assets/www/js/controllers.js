angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope,$rootScope,$ionicModal,$timeout,UserService) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);
    var username = $scope.loginData.username;
    var password = $scope.loginData.password;
    if(username == '' && password == ''){
        return;
    }
    //username/password
    UserService.login(username,password).success(function(res){
        if(res.code==0){
            $scope.user = res.user;
            $scope.closeLogin();
        }else{
            $scope.loginData.msg = res.msg;
        }
    });
    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    
  };
})
.controller('BooksCtrl',function($scope,$http,$state){
            
})
.controller('IndexCtrl', function($scope,$http,$state,ApiEndpoint,BookService,BookTypeService) {
  $scope.hasmore = true;
  $scope.next = 1;
  $scope.list = [];
  var run = false;
  BookService.top().success(function(res){
    if(res.code==0){
        for(var i=0;i<res.books.length;i++){
            var picture = res.books[i].picture;
            res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
        }
        $scope.books = res.books;
    }
  });
  BookTypeService.types().success(function(res){
    if(res.code==0){
        $scope.types = res.types;
        loadtypes(0);
    }
  });
 
  function loadtypes(index){
    if(!run){
        var types = $scope.types;
        if(types == undefined){
            return;
        }
        if(index >= types.length){
            return;
        }
        var typeId = types[index].id;
        var obj = {
            type:types[index],
            books:null
            };
        var t = index % 5;
        obj.type.picture = "img/types/"+(t+1)+".jpg";
        obj.type.imgName = (t+1);
        BookService.mainbooks(typeId).success(function(res){
            if(res.code==0){
                for(var i=0;i<res.books.length;i++){
                    var picture = res.books[i].picture;
                    res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
                }
                obj.books = res.books;
                $scope.list = $scope.list.concat(obj);
                $scope.next = $scope.next + 1;
            }
            $scope.$broadcast('scroll.infiniteScrollComplete');
        });
    }
    
  }
  $scope.loadtypes = function(index){
    loadtypes(index);
  };
  
  $scope.search = function(){
    $state.go("app.search");
  };
            
})

.controller('PlaylistCtrl', function($scope,$http,$stateParams,ApiEndpoint,Book,BookService) {
    var bookId = $stateParams.bookId;
    BookService.select(bookId).success(function(res){
        if(res.code==0){
            var picture = res.book.picture;
            res.book.picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
            $scope.book = res.book;
            var promise = Book.get(bookId);
            promise.then(function(data){
                if(data == null){
                   Book.insert(res.book);
                }
            });
            
        }
    });
})

.controller('SearchCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookService){
    $scope.searchCont = {};
    $scope.books = [];
    $scope.search = function(){
        var key = $scope.searchCont.key;
        if(key != ""){
            BookService.search(key).success(function(res){
                if(res.code==0){
                    for(var i=0;i<res.books.length;i++){
                        var picture = res.books[i].picture;
                        res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
                    }
                    $scope.books = res.books;
                }
            });
        }
    };
    $scope.clearSearch = function(){
        $scope.searchCont = {};
        $scope.books = [];
    };
})
.controller('TypesCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookTypeService,BookService){
    $scope.next = 1;
    var run = false;
    $scope.hasmore = true;
    $scope.books = [];
    var typeId = $stateParams.typeId;
    var imgName = $stateParams.imgName;
    BookTypeService.select(typeId).success(function(res){
        if(res.code==0){
            res.type.picture = "img/types/"+imgName+".jpg";
            $scope.type = res.type;
        }
    });
    loadbooks(1);
            
    $scope.loadbooks = function loadbooks(page){
        loadbooks(page);
    };
    
    function loadbooks(page){
        if(!run){
            BookService.getBooksByTypeId(typeId,$scope.next).success(function(res){
              if(res.code == 0 && res.books.length > 0){
                for(var i=0;i<res.books.length;i++){
                    var picture = res.books[i].picture;
                    res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
                }
                $scope.books = $scope.books.concat(res.books);
                $scope.next = $scope.next + 1;
              }
              $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }
    }
            
})
.controller('BookTypeCtrl',function($scope,$http,$stateParams,BookTypeService){
    var width = $(window).width()/2 + 2;
    $scope.typestyle = {
        "float":"left",
        "padding":0+"px",
        "width":width+"px"
    };
        
    BookTypeService.parents().success(function(res){
        if(res.code==0){
            for(var i = 0;i<res.parents.length;i++){
                res.parents[i].img = "img/types/"+res.parents[i].id+".png";
            }
            $scope.types = res.parents;
        }
    });
})
.controller('TypelistCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookTypeService,BookService){
    $scope.next = 1;
    var run = false;
    $scope.hasmore = true;
    $scope.books = [];
    $scope.tid = 0;
    var typeId = $stateParams.typeId;
    BookTypeService.getTypesByParentId(typeId).success(function(res){
        if(res.code==0){
            $scope.types = res.types;
        }
    });
    $scope.loadall = function(tid){
        $scope.next = 1;
        $scope.books = [];
        $scope.hasmore = false;
        loadlist(tid,1);
    };
    $scope.loadlist = function(tid,page){
        loadlist(tid,page);
    };
    
    function loadlist(tid,page){
        if(!run){
            var id = tid;
            $scope.tid = tid;
            //选择全部查询parentId下Books，其他查询子typeId
            BookService.getBooksByParentIdOrTypeId(tid,typeId,page).success(function(res){
                if(res.code==0){
                    for(var i=0;i<res.books.length;i++){
                        var picture = res.books[i].picture;
                        res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
                    }
                    if(res.books.length==0){
                        $scope.hasmore = false;
                    }else{
                        $scope.hasmore = true;
                    }
                    $scope.books = $scope.books.concat(res.books);
                    $scope.next = $scope.next + 1;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }
    }
            
})
.controller('ReaderCtrl',function($scope,$rootScope,$http,$stateParams,$timeout,$ionicSlideBoxDelegate,ApiEndpoint,BookService,BookFileService,Book,lzTxt){

    var bookId = $stateParams.bookId;
    $scope.show = false;
   
    var winSize = {
        width: $(window).width(),
        height : $(window).height()
    };
    /*字体尺寸*/
    var font = {
        size: 18
    };
            
    /*背景和字体配色方案*/
    var arr_bg = [{
        bg: "blanchedalmond",
        txt: "#333333"
    },{
        bg: "white",
        txt: "#333333"
    }];
    var arr_bg_index = 0;//当前使用的配色方案序列
            
    var canvas = document.getElementById("canvas");
    canvas.width = $(window).width();
    canvas.height = $(window).height();
    console.log(canvas.width);
    console.log(canvas.height);
    $("#canvas").css("background",arr_bg[arr_bg_index].bg);
            
    var ctx = canvas.getContext("2d");
            
    ctx.fillStyle = "black";
    ctx.font = font.size + "px 微软雅黑";
            
    //reader config
    $scope.init = function(){
        $rootScope.$broadcast('loading:show');
        BookService.select(bookId).success(function(res){
            if(res.code==0){
                var picture = res.book.picture;
                res.book.picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
                $scope.book = res.book;
            }
        });
        var promise = Book.get(bookId);
        promise.then(function(data){
            if(data.contents == ''){
                BookFileService.select(bookId).success(function(res){
                    if(res.code==0){
                        //res.contents = replaceAll(res.contents);
                        var book = {id : 0,contents : "",page : 1};
                        book.id = res.bookId;
                        book.contents = res.contents;                          
                        book.page = data.page;
                                                       
                        Book.updateContent(book);
                        $scope.content = res.contents;
                        init($scope.content, book.id, book.page);
                        $rootScope.$broadcast('loading:hide');
                    }
                });
            }else{
                $scope.content = data.contents;
                init($scope.content,data.id,data.page);
                $rootScope.$broadcast('loading:hide');
            }
        });
        
    };
            
    $scope.init();
    
            
            
    function getStyle(dom,styleName){
        if(dom.currentStyle){//ie
            return dom.currentStyle[styleName];
        }else{ //ff
            var $arr=dom.ownerDocument.defaultView.getComputedStyle(dom, null);
            return $arr[styleName];
        }
    }
  
            
    /*为配色方案选择添加事件*/
    var span_list = document.querySelectorAll(".color-container span");
    for(var i = 0 ; i < span_list.length ; ++i){
        var elem = span_list[i];
        elem.addEventListener("click",function(e){
            e.preventDefault();
            e.stopPropagation();
                                  
            var index = this.getAttribute("data-size");
            arr_bg_index = index;
            lzTxt.re_render();//调用重绘
        },false);
    }
    function init(contents,bookId,page){
        var result = contents;
        //console.log(result);
        console.time("init");
        lzTxt.init({
            id : "canvas",
            bookId : bookId,
            page : page,
            origin_txt : result,
            panel: {
                width : winSize.width - 30,
                height : winSize.height - 30
            },
            fontSize : font.size,
            //hook_page_start: start_loading,//开始分页计算
            hook_page_end: function(){
                lzTxt.render(page);
                //end_loading();//结束loading动画
            },//结束分页计算
                       
            page_draw_start: function(){
                ctx.fillStyle = arr_bg[arr_bg_index].bg;
                ctx.fillRect(0,0,canvas.width,canvas.height);
                ctx.fillStyle = arr_bg[arr_bg_index].txt;
                ctx.font = lzTxt.get_font_size() + 'px 微软雅黑';
                $("#canvas").css("background",arr_bg[arr_bg_index].bg);
            }
        });
        console.timeEnd("init");
       
            
        var current = page;
        //var pop_show = false;
        /*添加全局点击事件处理*/
        $scope.page_click = function($event){
            var e = $event;
            var x = e.pageX;
            var y = e.pageY;
            if($scope.show == true){
                $scope.show = false;
                return;
            }
            if(x < winSize.width * 0.8 && x > winSize.width * 0.2 && y > winSize.height * 0.4 && y < winSize.height * 0.6){
                $scope.show = true;
                var obj = lzTxt.get_progress();
                $scope.page = obj.page;
                $scope.total = obj.total;
                $scope.progress_top_style = {
                    width:(obj.page / obj.total * 100) + "%"
                };
                return;
            }
            var page = current;
            
            if(x > winSize.width / 2){
                page++;
                //drawImage(1);
            }else{
                page--;
                // if(page > 0) drawImage(3);
            }
         
            lzTxt.render(page) && (current = page);
            
            
        };
            
        $scope.onSwipeLeft = function(){
            var page = current;
            page++;
            //drawImage(1);
            lzTxt.render(page) && (current = page);
        };
        $scope.onSwipeRight = function(){
            var page = current;
            page--;
            // if(page > 0) drawImage(3);
            lzTxt.render(page) && (current = page);
        };
        
        /*
        document.addEventListener("click",function(e){
            e.preventDefault();
            var x = e.pageX;
            var y = e.pageY;
            var pop = document.getElementById("pop");
                                  
            if($scope.show == true){
                $scope.show = false;
                return;
            }
         
            if(getStyle(pop,"display") == "block"){
                pop.style.display = "none";
                $scope.show = false;
                return ;
            }
                                      
            
            if(x < winSize.width * 0.8 && x > winSize.width * 0.2 && y > winSize.height * 0.4 && y < winSize.height * 0.6){
                $scope.show = true;
                
                
                //pop.style.display = "block";
                //var page = document.getElementById("page");
                //var total = document.getElementById("total");
                var obj = lzTxt.get_progress();
                //page.innerHTML = obj.page;
                //total.innerHTML = obj.total;
                $scope.page = obj.page;
                $scope.total = obj.total;
                //var progress_top = document.getElementById("progress_top");
                $scope.progress_top_style = {
                    width:(obj.page / obj.total * 100) + "%"
                };
               
                return ;
            }
                                      
            
            var page = current;
            if(x > winSize.width / 2){
                page ++;
                drawImage(1);
            }else{
                page --;
                if(page > 0)
                    drawImage(3);
            }
            lzTxt.render(page) && (current = page);
        },false);
        */
            
        /*为字体方案选择添加事件*/
        var span_list = document.querySelectorAll(".font-container span");
        for(var i = 0 ; i < span_list.length ; ++i){
            var elem = span_list[i];
            elem.addEventListener("click",function(e){
                e.preventDefault();
                //e.stopPropagation();
                                  
                for(var j = 0 ; j < span_list.length ; ++j){
                    var elem1 = span_list[j];
                    elem1.className = "";
                }
                                  
                this.className = "active";
                var index = this.getAttribute("data-size");
                lzTxt.reRender(index);
            },false);
        };
        //reader.abort();
        //lzTxt.render(1);
            
    }
            
            

    //分页算法
    /*
    function page(contents){
        //var cs = [];
        var pageheight = $scope.pageheight;
        var br = "<BR/>";
        var index = 0;
        for(var i = 0;i < contents.length;i++){
            var c = contents[i];
            if(c == '<'){
                c = br;
                i += br.length - 1;
            }else{
                // c不变
            }
            $("#content").append(c);
            var height = $("#content").height();
            if(i < contents.length - 1){
                if(height > pageheight){
                    var html = $("#content").html();
                    $("#content").html(html.substr(0,html.length - 1));
                    break;
                }else if(height <= pageheight){
                    //不动
                }
            }else if(i == contents.length - 1){
                //$("#content").append(c);
                //cs.push($("#content").html());
                //$("#content").empty();
            }
        }
        //$("#content").remove();
        //return cs;
    }
    */
    /*
    * 翻页动画
    */
    function drawImage(direction){
        var src = canvas.toDataURL("image/png");
        var img = document.createElement("img");
        img.src = src;
        img.width = winSize.width;
        img.height = winSize.height;
        img.style.position = "absolute";
        img.style.top = "0px";
        img.style.left = "0px";
        img.style.background = "white";
        document.body.appendChild(img);
        var draw_inteval;
        draw_inteval = setInterval(function(){
            var left = img.offsetLeft;
            if(direction == 1){
                if(left < - winSize.width){
                    clearInterval(draw_inteval);
                }
                img.style.left = (left - canvas.width / 24) + "px";
            }else if(direction == 3){
                if(left > winSize.width){
                    clearInterval(draw_inteval);
                }
                img.style.left = (left + canvas.width / 24) + "px";
            }
        },1000 / 45);
    }
            
    /*绘制加载中动画*/
    function draw_loading(start_angle){
        ctx.clearRect(0,0,canvas.width,canvas.height);
            
        ctx.beginPath();
        ctx.fillStyle = "aquamarine";
        ctx.moveTo(canvas.width / 2,canvas.height / 2);
        ctx.arc(canvas.width / 2,canvas.height / 2,60,start_angle,2  * Math.PI);
        ctx.fill();
            
        ctx.beginPath();
        ctx.fillStyle = "white";
        ctx.moveTo(canvas.width / 2,canvas.height / 2);
        ctx.arc(canvas.width / 2,canvas.height / 2,50,start_angle,2  * Math.PI);
        ctx.fill();
    }
            
    var angle_invterval;
    function start_loading(){
        var start_ang = 0;
        angle_invterval = setInterval(function(){
            var angle = Math.PI * 2 * ((90 - start_ang) / 90);
                                
            draw_loading(angle);
            ++start_ang;
            start_ang %= 90;
        },1000 / 60);
        draw_loading(start_ang);
    }
            
    function end_loading(){
        window.clearInterval(angle_invterval);
    }
    
    window.start_loading = start_loading;
    window.end_loading = end_loading;
    
    //替换所有的回车换行
    function replaceAll(content){
        var string = content;
        try{
            string=string.replace(/\r\n/g,"<BR/>")
            string=string.replace(/\n/g,"<BR/>");
        }catch(e) {
            
        }
        return string;
    }
            
    /*
    $scope.loadpage = function loadpage(index){
        var length = $scope.contents.length;
        $scope.page = (index+1) + "/" + (length+1);
    };
     */
    
})
.controller('RecommendCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookService){
    BookService.recommend().success(function(res){
        if(res.code==0){
            for(var i=0;i<res.books.length;i++){
                var picture = res.books[i].picture;
                res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
            }
            $scope.books = res.books;
        }
    });
    
            
})
.controller('FreeCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookService){
    BookService.free().success(function(res){
        if(res.code==0){
            for(var i=0;i<res.books.length;i++){
                var picture = res.books[i].picture;
                res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
            }
            $scope.books = res.books;
        }
    });
})
.controller('NewBooksCtrl',function($scope,$http,$stateParams,ApiEndpoint,BookService){
    BookService.newbooks().success(function(res){
        if(res.code==0){
            for(var i=0;i<res.books.length;i++){
                var picture = res.books[i].picture;
                res.books[i].picture = picture == null ? "" : ApiEndpoint.url+"/cms/"+picture;
            }
            $scope.books = res.books;
        }
    });
})
.controller('BookShelvesCtrl',function($scope,$http,Book){
    var width = $(window).width()/3 + 3;
    $scope.bookstyle = {
        "float":"left",
        "padding-left":20+"px",
        "width":width+"px",
        "border":"none"
    };
    var promise = Book.selectall();
    promise.then(function(data){
        if(data != null){
            $scope.books = data;
        }
    });
            
})
.controller('DevelopmentCtrl',function($scope,$http,$stateParams){
    var screenheight = $(window).height();
    $scope.dstyle = {
        "padding-top":(screenheight/2 - 150)+"px"
    };
});
