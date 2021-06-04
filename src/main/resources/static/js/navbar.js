
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
    let usersLi = document.getElementById("usersLi");
    let usersA = document.getElementById("usersA");
    let eventsLi = document.getElementById("eventsLi");
    let eventsA = document.getElementById("eventsA");
    let dashboardLi = document.getElementById("dashboardLi");
    let dashboardA = document.getElementById("dashboardA");
    let suggestionsLi = document.getElementById("suggestionsLi");
    let suggestionsA = document.getElementById("suggestionsA");



    if(role==="[ADMIN]"){
        suggestionsLi.style.display = "inherit";
        suggestionsA.href="/suggestions";
        suggestionsA.innerText="M: Forslag";

        fileArchiveLi.style.display = "inherit";
        fileArchiveA.href="/files";
        fileArchiveA.innerText="M: Filarkiv";

        sendEmailLi.style.display = "inherit";
        sendEmailA.href="/sendEmail";
        sendEmailA.innerText="A: Send email";

        membersLi.style.display = "inherit";
        membersA.href="/admin/medlemmer";
        membersA.innerText="A: Medlemmer";

        usersLi.style.display = "inherit";
        usersA.href="/admin/users";
        usersA.innerText = "A: Brugere";

        eventsLi.style.display = "inherit";
        eventsA.href ="/admin/events";
        eventsA.innerText = "A: Begivenheder";

    } else if(role==="[USER]"){
        suggestionsLi.style.display = "inherit";
        suggestionsA.href="/suggestions";
        suggestionsA.innerText="M: Forslag";

        dashboardLi.style.display = "inherit";
        dashboardA.href="/dashboard";
        dashboardA.innerText="M: Begivenheder";

        fileArchiveLi.style.display = "inherit";
        fileArchiveA.href="/files";
        fileArchiveA.innerText="M: Filarkiv";
    }
}


loggedIn();
