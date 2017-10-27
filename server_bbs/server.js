var http = require('http');
var router = require('./a_router');
var test = require('./test');

/**
 * 1. 서버에서 라우팅
 * 2. 라우터에서 URL 분석 -> pathname, method 분석
 * 3. 컨트롤러가 모든 내부 로직 처리
 * 4. dao에 쿼리, 콜백 보내주기
 */
var server = http.createServer((request, response)=>{
    // 경로 라우팅
    router.process(request, response);
});

server.listen(8090, ()=>{
    console.log('[1.서버] 시작');
    
});