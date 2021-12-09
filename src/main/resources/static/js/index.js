$(document).ready(function (){
    $("#btn_save").click(function (){
        postingArticle();
    });
    getArticles();
    check();
})

function postingArticle() {
    var data = new FormData();
    data.append( "title", $("#title").val() );
    data.append( "content", $("#content").val() );
    if( typeof $("#image")[0].files[0] != 'undefined') data.append( "image", $("#image")[0].files[0] );

    $.ajax({
        type: "POST",
        url: `${apiUrl}/article`,
        processData: false,
        contentType: false,
        data: data,
        success: function (response) {
            alert("포스팅 성공!!");
            window.location.reload();
        }
    })
}

function getArticles() {
    $.ajax({
        type: "GET",
        url: `${apiUrl}/articles`,
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            $("#list-article").empty();
            for (let i = 0; i < response.length; i++) {
                makeListArticle(response[i]);
            }
        }
    })
}

function makeListArticle(article){
    let template = `<div class="col">
                    <div class="card shadow-sm">
                        <img src="${article['imageUrl']}"/> 
                        <div class="card-body">
                            <p class="card-text">${article['title']}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='view.html?idx=${article['idx']}' ">Chat</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>`
    $("#list-article").append(template);
}

function openCloseLogin() {
    $('#login-id').val('');
    $('#login-pwd').val('');
    $('#loginModal').modal('show');
}

function logout() {
    localStorage.removeItem('token');
    $('#btn-login').show();
    $('#btn-logout').hide();
    $('#btn-join').show();
}

function openCloseJoin() {
    $('#joinModal').modal('show');
}

function join() {
    let info = {
        username: $("#join-id").val(),
        password: $("#join-pwd").val()
    }

    $.ajax({
        type: "POST",
        url: `${apiUrl}/signup`,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(info),
        success: function (response) {
            alert("로그인 해주세요!!");
            $('#joinModal').modal('hide');
        }
    })
}

function login() {

    let info = {
        username: $("#login-id").val(),
        password: $("#login-pwd").val()
    }

    $.ajax({
        type: "POST",
        url: `${apiUrl}/login`,
        data: JSON.stringify(info),
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            localStorage.setItem('token', response['token']);
            alert("로그인 되었습니다!!");
            $('#loginModal').modal('hide');
            check();
        }
    })
}

function check() {
    if (localStorage.getItem('token')) {
        $('#btn-login').hide();
        $('#btn-logout').show();
        $('#btn-join').hide();
    }
}
