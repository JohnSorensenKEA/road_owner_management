
let getRequestblyat ={
    method: "GET",
    headers: {
        "content-type": "application/json"
    }
}

async function loggedIn(){
   await fetch("/loggedIn",getRequestblyat)
        .then(response => response.json())
        .then(data => buildHTMLnavLogin(data));
}


async function buildHTMLnavLogin(loggedIn){
    let li = document.getElementById("login-out");
    if(!loggedIn){
        li.innerText = "Log ind";
        li.href = "/login";
    } else if(loggedIn){
        li.innerText = "Log ud";
        li.href = "/logout";

        //run who is logged in and then show appropriate content
        fetch("/loggedInRole",getRequestblyat)
            .then(response => response.text())
            .then(data => buildHTMLnav(data))
            .catch(error => console.log(error));

    }
}
function buildHTMLnav(role){
    let fileArchiveLi = document.getElementById("fileArchiveLi");
    let fileArchiveA = document.getElementById("fileArchiveA");
    let sendEmailLi = document.getElementById("sendEmailLi");
    let sendEmailA = document.getElementById("sendEmailA");
    let membersLi = document.getElementById("membersLi")
    let membersA = document.getElementById("membersA")
    if(role==="[ADMIN]"){
        fileArchiveLi.style.display = "inherit";
        fileArchiveA.href="/files";
        fileArchiveA.innerText="A: Filarkiv";

        sendEmailLi.style.display = "inherit";
        sendEmailA.href="/sendEmail";
        sendEmailA.innerText="A: Send email";

        membersLi.style.display = "inherit";
        membersA.href="/admin/medlemmer";
        membersA.innerText="A: Medlemmer";

    }
}


loggedIn();
