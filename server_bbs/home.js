var db = require('./db');

var bbs = {
    no : 2,
    title : '제목입니다2',
    content : '내용입니다',
    date : '2017/10/26 11:12:33',
    user_id : 'root'
}

search = {
    type : 'no',// all=전체검색, no=글 한 개 검색, title=제목검색
    no : 2,
    title : '제목검색',
    content : '내용검색'
}

db.create(bbs, (answer)=>{
    console.log('create');
    console.log(answer);
});

db.read(search, (dataSet)=>{
    // dataSet.forEach((index, item)=>{
    //     console.log(item);
    // });
    var count = 0;
    for(key in dataSet){
        count++;
        console.log(dataSet[key]);
    }
    console.log(count);
    
});

db.readOne(search, (dataSet)=>{
    if(dataSet.length > 0){
        // 서버에서 수정할 데이터 조회
        var bbs = dataSet[0];
        var json = JSON.stringify(bbs);

        // 안드로이드...
        var modified = JSON.parse(json); 
        modified.title = "수정했습니다"
        var mod_json = JSON.stringify(modified);

        // 서버
        var completed = JSON.parse(mod_json);
        db.update(completed);
    }
});