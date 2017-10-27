
// 기본 데이터 300만 건 넣어보기
var mongo = require('mongodb').MongoClient;

var dbName = 'bbsdb'
var dbUrl = "mongodb://localhost:27017/"+dbName;
var table = 'bbs';

var users = ["aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii", "jjj"]

mongo.connect(dbUrl, (err, db)=>{

    for(var index=0; index<10; index++){
        var array = [];
        for(j=0; j<100; j++){
            var bbs = {
                title : "", // 랜덤한 텍스트를 조합해서 입력
                content : "내용입니다",
                user_id : users[10%j],   // 10개를 미리 정해놓고 random 입력
                date : new Date()+""
            }
            array[j] = bbs;
        }
        db.collection(table).insert(array, function(err, inserted){
            if(err){
                console.log(err);
            } else {
                console.log('성공');
            }
        });
    }
});
