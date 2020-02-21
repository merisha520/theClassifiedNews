function demo() {
    var errormsg = document.getElementById("errormsg");

    var params = {
        demoText: document.DemoForm.DemoText.value
    };

    var url = 'http://localhost:4567/demo';

    window.fetch(url, {
        method: 'POST',
        body: JSON.stringify(params),
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    })
        .then(function (response) {
            response.json().then(
                function (data) {
                    console.log(data);
                    document.getElementById("cleantext").innerHTML = data.cleanedText.toString();
                    document.getElementById("stopword").innerHTML = data.stopWordsRemoved.toString();
                    document.getElementById("porter").innerHTML = data.porterStemmed.toString();

                    var featureTable = "";
                    Object.keys(data.keyWordCounts).forEach(function (key) {
                        featureTable += "<tr><td>" + key + "</td>" +
                            "<td>" + data.keyWordCounts[key] + "</td></tr>";
                    });
                    document.getElementById("featuretable").innerHTML = featureTable;

                    var probabilityTable = "";
                    Object.keys(data.predictedProbabilities).forEach(function (key) {
                        probabilityTable += "<tr><td>" + key + "</td>" +
                            "<td>" + data.predictedProbabilities[key] + "</td></tr>";
                    });
                    document.getElementById("probabilitytable").innerHTML = probabilityTable;

                    document.getElementById("predicted").innerHTML = data.predictedCategory;
                }
            )
        })
        .catch(function (error) {
            console.log(error);
            errormsg.innerHTML = "Error: Parsing data";
        });

    return false;
}