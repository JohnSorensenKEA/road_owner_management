
let getRequest ={
    method: "GET",
    headers: {
        "content-type": "application/json"
    }
}

function loggedIn(){
    let isLoggedIn;
    fetch("/loggedIn",getRequest)
        .then(response => response.json())
        .then(data => buildHTMLnav(data));
}

function buildHTMLnav(loggedIn){
    let li = document.getElementById("login-out");
    if(!loggedIn){
        li.innerText = "Log ind";
        li.href = "/login";
    } else if(loggedIn){
        li.innerText = "Log ud";
        li.href = "/logout";
    }
}


loggedIn();