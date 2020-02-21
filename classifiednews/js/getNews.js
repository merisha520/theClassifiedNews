function loadNews() {
    var url = "http://localhost:4567/news";

    var newsPanel = document.getElementById('NewsPanel');
    newsPanel.innerHTML = "";

    var newsBar = document.getElementById('newsBar');
    newsBar.innerHTML = "";

    fetch(url)
        .then(function (response) {
            response.json().then(
                function (data) {
                    console.log(data);
                    var i;
                    for (i = 0; i < data.length; i++) {
                        if (i == 0) {
                            var article = data[i];
                            document.getElementById('newsContents').innerHTML = article.contents;
                            document.getElementById('newsTitle').innerHTML = article.title;
                            document.getElementById('publishedDate').innerHTML = article.publishedDate;
                            document.getElementById('newsAuthor').innerHTML = article.writername;
                            document.getElementById('newsImage').innerHTML = "<img width='100%' src='" + article.imageUrl + "'>";
                        }
                        if (i > 0 && i < 5) {
                            var newsElement = "<div class='col-3 newspreview'>" +
                                "<div class='col-4 padding-2'>" +
                                "<div class='row'>" +
                                "<div class='col-12 padding-2'>" +
                                "<img width='100%'' src='" + data[i].imageUrl + "' alt='No preview'>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "<div class='col-8'>" +
                                "<div class='row'>" +
                                "<div class='col-12 padding-2'>" +
                                "<a href='#' id='" + data[i].newsId +
                                "' class='newsTitle' onclick='aClick(this.id)'>" + data[i].title + "</a>"
                            "</div>" +
                            "</div>" +
                            "<div class='row'>" +
                            "<div class='col-6 padding-2 smallText'>" + data[i].publishedDate + "</div>" +
                            "<div class='col-6 padding-2 smallText'>" + data[i].viewCount + " Views</div>" +
                            "</div>" +
                            "</div>" +
                            "</div>";

                            newsBar.innerHTML += newsElement;

                        }
                        if (i > 4 && i < 11) {
                            var newsElement = "<div class='row leftNews'>" +
                                "<div class='col-8'>" +
                                "<div class='row'>" +
                                "<div class='col-12 newsTitle padding-2'>" +
                                "<a href='#' id='" + data[i].newsId + "' onclick='aClick(this.id)'>" + data[i].title + "</a>" +
                                "</div>" +
                                "</div>" +
                                "<div class='row'>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].publishedDate + "</div>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].viewCount + " Views</div>" +
                                "</div>" +
                                "</div>" +
                                "<div class='col-4'>" +
                                "<div class='row padding-2'>" +
                                "<div class='col-12'>" +
                                "<img width='100%' src='" + data[i].imageUrl + "'></a>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>";

                            newsPanel.innerHTML += newsElement;

                        }
                    }
                }
            )
        })
        .catch(function (error) {
            console.log(error);
        });
    return false;
}


function loadNewsByCategory(category) {
    var dateUrl = 'http://localhost:4567/currentdate';
    var curdate = document.getElementById('currentdate');

    window.fetch(dateUrl, {
        method: 'GET'
    })
        .then(function (response) {
            response.json().then(
                function (data) {
                    curdate.innerHTML = data.day + " " + data.dayOfMonth + ", " + data.month + " " + data.year;
                })
        });

    var name = window.sessionStorage.getItem('name');
    if (name == "" || name == null || name == "undefined") {
        console.log("no Session");
    } else {
        document.getElementById('Welcome').innerHTML = "Welcome " + name;
        document.getElementById('Login').innerHTML = "<a href='createnews.html'>POST NEWS</a>";
        document.getElementById('SignUp').innerHTML = "";
    }

    var url = "http://localhost:4567/news?category=" + category;

    var newsPanel = document.getElementById('NewsPanel');
    newsPanel.innerHTML = "";

    fetch(url)
        .then(function (response) {
            response.json().then(
                function (data) {

                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            var article = data[i];
                            document.getElementById('newsContents').innerHTML = article.contents;
                            document.getElementById('newsTitle').innerHTML = article.title;
                            document.getElementById('publishedDate').innerHTML = article.publishedDate;
                            document.getElementById('newsAuthor').innerHTML = article.writername;
                            document.getElementById('newsImage').innerHTML = "<img width='100%' src='" + article.imageUrl + "'>";
                        }
                        if (i > 0 && i < 6) {
                            var newsElement = "<div class='row leftNews'>" +
                                "<div class='col-8'>" +
                                "<div class='row'>" +
                                "<div class='col-12 newsTitle padding-2'>" +
                                "<a href='#' id='" + data[i].newsId + "' onclick='aClick(this.id)'>" + data[i].title + "</a>" +
                                "</div>" +
                                "</div>" +
                                "<div class='row'>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].publishedDate + "</div>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].viewCount + " Views</div>" +
                                "</div>" +
                                "</div>" +
                                "<div class='col-4'>" +
                                "<div class='row padding-2'>" +
                                "<div class='col-12'>" +
                                "<img width='100%' src='" + data[i].imageUrl + "'></a>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>";

                            newsPanel.innerHTML += newsElement;
                        }
                    }
                }
            )
        })
        .catch(function (error) {
            console.log(error);
        });
    return false;
}


function loadNewsByWriter(writerid) {
    var url = "http://localhost:4567/news?writerid=" + writerid;

    var newsPanel = document.getElementById('NewsPanel');
    newsPanel.innerHTML = "";
    fetch(url)
        .then(function (response) {
            response.json().then(
                function (data) {

                    for (var i = 0; i < data.length; i++) {
                        if (i == 0) {
                            var article = data[i];
                            document.getElementById('newsContents').innerHTML = article.contents;
                            document.getElementById('newsTitle').innerHTML = article.title;
                            document.getElementById('publishedDate').innerHTML = article.publishedDate;
                            document.getElementById('newsAuthor').innerHTML = article.writername;
                            document.getElementById('newsImage').innerHTML = "<img width='100%' src='" + article.imageUrl + "'>";
                        }
                        if (i > 0 && i < 6) {
                            var newsElement = "<div class='row leftNews'>" +
                                "<div class='col-8'>" +
                                "<div class='row'>" +
                                "<div class='col-12 newsTitle padding-2'>" +
                                "<a href='#' id='" + data[i].newsId + "' onclick='aClick(this.id)'>" + data[i].title + "</a>" +
                                "</div>" +
                                "</div>" +
                                "<div class='row'>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].publishedDate + "</div>" +
                                "<div class='col-6 padding-2 smallText'>" + data[i].viewCount + " Views</div>" +
                                "</div>" +
                                "</div>" +
                                "<div class='col-4'>" +
                                "<div class='row padding-2'>" +
                                "<div class='col-12'>" +
                                "<img width='100%' src='" + data[i].imageUrl + "'></a>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>";

                            newsPanel.innerHTML += newsElement;

                        }
                    }
                }
            )
        })
        .catch(function (error) {
            console.log(error);
        });
    return false;
}


function searchNewsByTitle() {
    var searchItem = document.getElementById('searchText').value;
    if (searchItem == "" || searchItem == null || searchItem == "undefined") {
        console.log("nothing to search " + searchItem);
    } else {
        var url = "http://localhost:4567/news?search=" + searchItem;
        console.log('Searchdata ' + searchItem);
        var newsPanel = document.getElementById('NewsPanel');
        newsPanel.innerHTML = "";

        fetch(url)
            .then(function (response) {
                response.json().then(
                    function (data) {
                        console.log(data.length);
                        if (data.length > 0) {
                            for (var i = 0; i < data.length; i++) {
                                if (i == 0) {
                                    var article = data[i];
                                    document.getElementById('newsContents').innerHTML = article.contents;
                                    document.getElementById('newsTitle').innerHTML = article.title;
                                    document.getElementById('publishedDate').innerHTML = article.publishedDate;
                                    document.getElementById('newsAuthor').innerHTML = article.writername;
                                    document.getElementById('newsImage').innerHTML = "<img width='100%' src='" + article.imageUrl + "'>";
                                }
                                if (i > 0 && i < 6) {
                                    var newsElement = "<div class='row leftNews'>" +
                                        "<div class='col-8'>" +
                                        "<div class='row'>" +
                                        "<div class='col-12 newsTitle padding-2'>" +
                                        "<a href='#' id='" + data[i].newsId + "' onclick='aClick(this.id)'>" + data[i].title + "</a>" +
                                        "</div>" +
                                        "</div>" +
                                        "<div class='row'>" +
                                        "<div class='col-6 padding-2 smallText'>" + data[i].publishedDate + "</div>" +
                                        "<div class='col-6 padding-2 smallText'>" + data[i].viewCount + " Views</div>" +
                                        "</div>" +
                                        "</div>" +
                                        "<div class='col-4'>" +
                                        "<div class='row padding-2'>" +
                                        "<div class='col-12'>" +
                                        "<img width='100%' src='" + data[i].imageUrl + "'></a>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>" +
                                        "</div>";

                                    newsPanel.innerHTML += newsElement;

                                }
                            }
                        } else {
                            document.getElementById('newsContents').innerHTML = "";
                            document.getElementById('publishedDate').innerHTML = "";
                            document.getElementById('newsAuthor').innerHTML = "";
                            document.getElementById('newsImage').innerHTML = "<h1>!! SORRY !!</h1>";
                            document.getElementById('newsTitle').innerHTML = "Search Not Found !!";
                        }
                    }
                )
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    return false;
}

function aClick(idd) {
    var url = "http://localhost:4567/news?newsid=" + idd;

    fetch(url)
        .then(function (response) {
            response.json().then(
                function (data) {
                    var article = data[0];
                    document.getElementById('newsContents').innerHTML = article.contents;
                    document.getElementById('newsTitle').innerHTML = article.title;
                    document.getElementById('publishedDate').innerHTML = article.publishedDate;
                    document.getElementById('newsAuthor').innerHTML = article.writername;
                    document.getElementById('newsImage').innerHTML = "<img width='100%' src='" + article.imageUrl + "'>";
                }
            )
        })
        .catch(function (error) {
            console.log(error);
        });
    return false;
}