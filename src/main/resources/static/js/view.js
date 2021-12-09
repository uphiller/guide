let stompClient = null;
let idx;
$(document).ready(function () {
    idx = getParam('idx');
    onSocket(idx);

    $('#btn_send').on("click",send)
});

function getParam(name) {
    var results = new RegExp('[\?&amp;]' + name + '=([^&amp;#]*)').exec(window.location.href);
    return results[1] || 0;
}

function onSocket(idx) {
    let socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(  `/topic/article/${idx}`, function (greeting) {
            console.log(greeting)
        });
    });
}

function send(){
    stompClient.send(`/pub/msg/${idx}`, {'Authorization': 'Bearer '+localStorage.getItem('token')}, JSON.stringify({'msg': '123'}));
}
