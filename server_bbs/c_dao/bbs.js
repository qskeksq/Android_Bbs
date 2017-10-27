// db CRUD 함수 
var mongo = require('mongodb').MongoClient;
var dbName = 'bbsdb'
var dbUrl = "mongodb://localhost:27017/"+dbName;
var table = 'bbs';

/*
    bbs = {
        id : 12,
        title : '제목'
        content : 'content'
        data : '2017/10/26'
        user_id : 'root'
    }

    search = {
        type : // all=전체검색, no=글 한 개 검색, title=제목검색
        no : 122;
        title : 제목검색
        content : 내용검색

    }
*/

// exports.Bbs = function(){
//     this.no = -1,
//     this.title = "";
//     this.content="";
//     this.data = "";
//     this.user_id = "";

//     this.toQuery = function(){
//         var bbs = {
//             no:-1,
//             title:'제목',
//             content:'내용',
//             data:'2017/10/28',
//             user_id:'root'
//         }
//         bbs.no = this.no;
//         bbs.title = this.title;

//         return bbs;
//     }
// }

function setLog() {
    console.log('[4.dao] 컨트롤러에서 dao로 넘어옴')
}

exports.read = function(search, callback){
    setLog();
    mongo.connect(dbUrl, (err, db)=>{
        // var query = {};
        // if(search.type === 'all'){
        //     query = {};
        // } else if(search.type == 'no'){
        //     query = {no:-1};
        //     query = {no:search.no};
        // }
        console.log(search);
        // var projection = {title:'1'};
        // var projection = {_id:1} // 1이면 가져오고 0이면 가져오지 않는다.
        var sort = {
            _id : -1 // 1:내림차순, -1:오름차순
        }
        var cursor = db.collection(table).find(search /*, projection*/).sort(sort);    
        cursor.toArray((err, documents)=>{
            if(err){
                callback(documents, err);
                console.log(err);
            } else {
                callback(documents, err);
            }
        });
        db.close();
    });
}

exports.readOne = function(search, callback){
    mongo.connect(dbUrl, (err, db)=>{
        var query = {};
        if(search.type === 'all'){
            query = {};
        } else if(search.type == 'no'){
            query = {no:-1};
            query = {no:search.no};
        }
        var cursor = db.collection(table).find(query);    
        cursor.toArray((err, documents)=>{
            if(err){
                console.log(err);
            } else {
                callback(documents);
            }
        });
        db.close();
    });
}

exports.create = function(bbs, callback){
    setLog();
    mongo.connect(dbUrl, (err, db)=>{
        db.collection(table).insert(bbs); // NoSql이라 insert가 실패할 수가 없다
                                          // 자동으로 database와 table이 생성된다
        if(err){
            callback(400);
        } else {
            callback(200);
            console.log(bbs);
        }
        db.close();
    });
}


exports.update = function(bbs){
    mongo.connect(dbUrl, (err, database)=>{
        // 1. 수정대상쿼리
        var query = {_id : -1};
        // 2. 데이터 수정명령 - 칼럼이름과 값
        query._id = bbs._id
        var operator = bbs;
        delete operator._id;


        // 3. 수정옵션
        var option = {upsert : true};

        db.collection(table).update(query, operator, option, (err, upserted)=>{
            if(err){

            } else {

            }
        });        
    });
}

exports.remove = function(bbs){
    mongo.connect(dbUrl, (err, database)=>{
        // 1. 수정대상쿼리
        var query = {no : 123};
        // 2. 데이터 수정명령 - 칼럼이름과 값
        var operator = {title : '타이틀', data:'2018-11-15 11:13:23'}
        // 3. 수정옵션
        var option = {upsert : true};

        db.collection(table).remove(query, (err, removed)=>{
            if(err){

            } else {

            }
        });        
    });
}