var dao = require('../c_dao/bbs');

function setLog(){
    console.log('[3.컨트롤러] 라우팅 되어 컨트롤러에 들어옴');
} 


exports.read = function(request, response, search){
    setLog();
    var query = {};
    if(search.type === 'all'){
        query = {};
    } else if(search.type === 'no'){
        // query = {no:-1};
        // // query = {no:search.no};
        // query.no = parseInt(search.no);
        query = { _id : -1};
		query._id = parseInt(search.no)
    }
    dao.read(query, (dataSet, err)=>{

        if(err){
            var result = {
                code : 400,
                msg : "오류",
            };
            response.writeHead(200, {'Content-Type':'application/json; charset=utf8'})
            response.end(JSON.stringify(result));
        } else {
            console.log(dataSet);
            var result = {
                code : 200,
                msg : "정상처리",
                data : dataSet
            };
            response.writeHead(200, {'Content-Type':'application/json; charset=utf8'})
            response.end(JSON.stringify(result));
        }

    });
}

exports.create = function(request, response, bbsObj){
    setLog();
    dao.create(bbsObj, (result_code)=>{
        var result = {
            code : result_code,
            msg : '입력완료'
        }
        response.end(JSON.stringify(result));
    });
}

exports.update = function(request, response, bbsObj){
    
}

exports.remove = function(request, response, bbsObj){
    
}
