$(document).ready(function (){
    $("#btn_save").click(function (){
        postingArticle();
    });
    getArticles();
})

function postingArticle() {
    var data = new FormData();
    data.append( "title", $("#title").val() );
    data.append( "content", $("#content").val() );
    data.append( "image", $("#image")[0].files[0] );

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
                                    <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                </div>
                                <small class="text-muted">9 mins</small>
                            </div>
                        </div>
                    </div>
                </div>`
    $("#list-article").append(template);
}
