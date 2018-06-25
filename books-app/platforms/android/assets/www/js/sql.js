angular.module('starter.sql',[])
.factory('Book',function($cordovaSQLite,$q){
    var db = null;
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
    }else{
        db = window.openDatabase("books.db", '1', 'books', 1024 * 1024 * 100); // browser
    }
       
    return{
        insert:function(book){
            var contents = "";
            var query = "INSERT INTO tbl_book(id,book_name,contents,create_time,picture,page) VALUES (?,?,?,?,?,?) ";
            $cordovaSQLite.execute(db, query, [book.id,book.name,contents,book.createTime,book.picture,1]).then(function(res) {
                console.log("INSERT ID -> " + res.insertId);
            }, function (err) {
                console.error(err);
            });
        },
        updateContent:function(book){
            var query = "update tbl_book set contents = ? where id = ? ";
            $cordovaSQLite.execute(db,query,[book.contents,book.id]).then(function(res){
                    console.log("UPDATE ID -> ?");
                },function(err){
                    console.log(err.message);
                });
        },
        updatePage:function(page,bookId){
            var query = "update tbl_book set page = ? where id = ? ";
            $cordovaSQLite.execute(db,query,[page,bookId]).then(function(res){
                console.log("UPDATE ID -> ?");
            },function(err){
                console.log(err.message);
            });
        },
        selectall:function(){
            var books = null;
            var deferred = $q.defer();
            var query = "SELECT * FROM tbl_book ";
            $cordovaSQLite.execute(db,query).then(function(res){
                var books = new Array();
                if(res.rows.length > 0){
                    for(var i = 0;i<res.rows.item.length;i++){
                        books.push(res.rows.item(i));
                    }
                }
                deferred.resolve(books);
            },function(err){
                console.error(err);
            });
            return deferred.promise;
        },
        get:function(id){
            var book = null;
            var deferred = $q.defer();
            var query = "SELECT * FROM tbl_book WHERE id = ? ";
            $cordovaSQLite.execute(db, query, [id]).then(function(res) {
                if(res.rows.length > 0){
                    book = res.rows.item(0);
                }
                deferred.resolve(book);
            },function(err){
                console.error(err);
            });
            return deferred.promise;
        }
    };
})
.factory('BookShelves',function($cordovaSQLite,$q){
    var db = null;
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
    }else{
        db = window.openDatabase("books.db", '1', 'books', 1024 * 1024 * 100); // browser
    }
         
    return{
         insert:function(bookShelves){
            var query = "INSERT INTO tbl_bookshelves(book_name,create_time, picture) VALUES (?,?,?,?,?)";
            $cordovaSQLite.execute(db, query, [bookShelves.name,bookShelves.createTime,bookShelves.picture]).then(function(res) {
                console.log("INSERT ID -> " + res.insertId);
            }, function (err) {
                console.error(err);
            });
         }
    };
         
});
