function validate() {
    var errormsg = document.getElementById("errormsg");

    if (document.SignupForm.Email.value == "") {
        errormsg.innerHTML = "Error: Please enter valid email address";
        document.SignupForm.Email.focus();
        return false;
    }

    if (document.SignupForm.FullName.value == "") {
        errormsg.innerHTML = "Error: Please enter your full name";
        document.SignupForm.FullName.focus();
        return false;
    }

    if (document.SignupForm.Company.value == "") {
        errormsg.innerHTML = "Error: Please enter your company name";
        document.SignupForm.Company.focus();
        return false;
    }

    if (document.SignupForm.Password.value != "" && document.SignupForm.Password.value == document.SignupForm.ConfirmPassword.value) {
        if (document.SignupForm.Password.value.length < 8) {
            errormsg.innerHTML = "Error: Password must contain at least eight characters!";
            document.SignupForm.Password.focus();
            return false;
        }
        var re = /[0-9]/;
        if (!re.test(document.SignupForm.Password.value)) {
            errormsg.innerHTML = "Error: password must contain at least one number (0-9)!";
            document.SignupForm.Password.focus();
            return false;
        }
        re = /[a-z]/;
        if (!re.test(document.SignupForm.Password.value)) {
            errormsg.innerHTML = "Error: password must contain at least one lowercase letter (a-z)!";
            document.SignupForm.Password.focus();
            return false;
        }
        re = /[A-Z]/;
        if (!re.test(document.SignupForm.Password.value)) {
            errormsg.innerHTML = "Error: password must contain at least one uppercase letter (A-Z)!";
            document.SignupForm.Password.focus();
            return false;
        }
    } else {
        errormsg.innerHTML = "Error: Please check that you've entered and confirmed your password!";
        document.SignupForm.ConfirmPassword.focus();
        return false;
    }

    var params = {
        fullName: document.SignupForm.FullName.value,
        email: document.SignupForm.Email.value,
        authenticationKey: document.SignupForm.Password.value,
        companyName: document.SignupForm.Company.value
    };

    var url = 'http://localhost:4567/signup';

    window.fetch(url, {
        method: 'POST',
        body: JSON.stringify(params),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        redirect: 'follow'
    })
        .then(function (response) {
            document.location.replace('login.html');
        })
        .catch(function (error) {
            console.log(error);
            errormsg.innerHTML = "Error: User already exists";
        });

    return false;
}

function indexLoad() {

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
        document.getElementById('Login').innerHTML = "<a href='webpages/createnews.html'>POST NEWS</a>";
        document.getElementById('SignUp').innerHTML = "";
    }

    loadNews();
}

function login() {
    var errormsg = document.getElementById('errormsg');

    var params = {
        email: document.LoginForm.Email.value,
        authenticationKey: document.LoginForm.Password.value
    };

    var url = 'http://localhost:4567/login';

    window.fetch(url, {
        method: 'POST',
        body: JSON.stringify(params),
        headers: new Headers({
            'Content-Type': 'application/json'
        }),
        redirect: 'follow'
    })
        .then(function (response) {
            response.json().then(
                function (data) {
                    console.log(data);
                    if (data.loginStatus) {
                        sessionStorage.setItem('name', data.fullName);
                        sessionStorage.setItem('id', data.id);
                        document.location.replace('../index.html');
                    } else {
                        errormsg.innerHTML = "Error: Email/Password incorrect";
                    }
                })
        })
        .catch(function (error) {
            console.log(error);
            errormsg.innerHTML = "Error: Email incorrect";
        });

    return false;
}