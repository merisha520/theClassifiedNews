window.onload = function () {
    nicEditors.allTextAreas();
    var name = sessionStorage.getItem('name');
    if (name == null || name == "" || name == "undefined") {
        document.location.replace('login.html');
    } else {
        document.getElementById('Welcome').innerHTML = 'Welcome ' + name;
    }
}

function postnews() {
    editor = nicEditors.findEditor('newsarticle');
    article = editor.getContent();

    errormsg = document.getElementById('errormsg');

    if (article == "" || article == null || article == undefined || article == " ") {
        errormsg.innerHTML = "You can only submit with news article";
        return false;
    }

    if (document.PostNews.NewsTitle.value == "") {
        errormsg.innerHTML = "Please write a title for the article";
        document.PostNews.NewsTitle.focus();
        return false;
    }

    var url = "http://localhost:4567/postnews"

    var formData = new FormData();
    formData.append('image', document.PostNews.NewsImage.files[0]);
    formData.append('writername', sessionStorage.getItem('name'));
    formData.append('writerId', sessionStorage.getItem('id'));
    formData.append('title', document.PostNews.NewsTitle.value);
    formData.append('contents', article);

    window.fetch(url, {
        method: 'POST',
        body: formData,
        contentType: false,
        processData: false,
        redirect: 'follow'
    })
        .then(function (response) {
            response.json().then(
                function (data) {
                    sessionStorage.setItem('newsId', data.newsId);
                    if (data.status != null) {
                        errormsg.innerHTML = "Error: cannot upload";
                    } else {
                        var category = data.category;
                        switch (category) {
                            case 'tech':
                                document.location.replace('tech.html');
                                break;
                            case 'business':
                                document.location.replace('business.html');
                                break;
                            case 'politics':
                                document.location.replace('politics.html');
                                break;
                            case 'sport':
                                document.location.replace('sport.html');
                                break;
                            case 'entertainment':
                                document.location.replace('entertainment.html');
                                break;
                            default:
                                document.location.replace('../index.html');
                        }
                    }
                })
        })
        .catch(function (error) {
            console.log(error);
            errormsg.innerHTML = "Error:Couldn't submit";
        });

    return false;
}