let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
};

let email = {
    "subject": "",
    "message": "",
    "recipients": ""
}

let body1;

let postRequest = {
    method: "POST",
    headers: {
        "content-type": "application/json"
    },
    body: body1
}

let getUrl = "http://localhost:8080/api/admin/member/allMembers";
let postUrl = "http://localhost:8080/sendtheemail"

let members = [];

async function f() {
    await fetch(getUrl, getRequest).
    then(response => response.json()).
    then(data => data.forEach(member => members.push(member)))

    let membersList2 = document.getElementById("members-div");



    for(let i = 0; i<members.length; i++){
        if(members[i].user != null) {
            let option = document.createElement("button");
            option.className = "btn"
            option.innerHTML = members[i].ownerName;
            option.style.margin = "2px";
            let br = document.createElement("br");
            let temp = false;
            option.onclick = function(){
                if(!temp) {
                    addToSelected(members[i].user.email);
                    option.style.backgroundColor = "darkgrey";
                    temp = true;
                }
                else if(temp){
                    option.style.backgroundColor = "";
                    selected.splice(selected.indexOf(members[i].user.email), 1);
                    temp = false;
                }
            };
            membersList2.appendChild(option);
            membersList2.appendChild(br);
        }
    }
}

let selected = [];

function addToSelected(email){
    selected.push(email);

}

async function sendEmail(){
    let temp;
    email.message = document.getElementById("message-textarea").value;
    email.subject = document.getElementById("subject-input").value;
    email.recipients = selected;


    body1 = JSON.stringify(email);
    postRequest.body = body1;
    await fetch(postUrl, postRequest).
    then(response => temp = response);

    if(temp.ok){
        alert("Email er sendt");
        location.reload();
    }else{
        alert("Email er ikke sendt");
    }

}



f();


