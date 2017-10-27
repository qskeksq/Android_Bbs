var u = require('url');
var qs = require('querystring');
var bbs = require('../b_controller/bbs');

exports.process = function(request, response){
    console.log('[2.라우터] 프로세스로 들어옴')
    var url = u.parse(request.url);
    var cmds = url.pathname.split('/');
    var method = request.method.toLowerCase();
    if(cmds[1] == 'bbs'){
        if(method == 'get'){
            var query = qs.parse(url.query);
            bbs.read(request, response, query);
        } else {
            var body = "";
            request.on('data', (data)=>{
                body += data;
            });
            request.on('end', ()=>{
                var bbsObj = JSON.parse(body);
                if(method == 'post'){
                    bbs.create(request, response, bbsObj);
                } else if(method == 'put'){
                    bbs.update(request, response, bbsObj);
                } else if(mtehod == 'delete'){
                    bbs.remove(request, response, bbsObj);
                }
            });
        }
    }
}