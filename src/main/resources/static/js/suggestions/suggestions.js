let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
};

let body1;

let postRequest = {
    method: "POST",
    headers: {
        "content-type": "application/json"
    },
    body: body1
}

let deleteRequest = {
    method: "DELETE",
    headers: {
        "content-type": "application/json"
    }
}

let suggestionJson = {
    "suggestion": ""
}

let getUrl = "http://localhost:8080/allSuggestions";
let downloadUrl = "http://localhost:8080/suggestions/download"
let getLoggedInUrl = "http://localhost:8080/loggedInRole"
let postUrl = "http://localhost:8080/newSuggestion";
let deleteUrl ="http://localhost:8080/deleteAllSuggestions";


let suggestions = [];

async function f() {
    await fetch(getUrl, getRequest).
        then(response => response.json()).
        then(data => data.forEach(suggestion => suggestions.push(suggestion)))

    let suggestionsList = document.getElementById("suggestions-div");



    for(let i = 0; i<suggestions.length; i++){
            let suggestion = document.createElement("div");
            let author = document.createElement("p");
            let suggestionText = document.createElement("p");

            suggestion.style.borderBottom = "solid grey 2px";

            author.innerHTML = suggestions[i].author;
            suggestionText.innerHTML = suggestions[i].suggestion;
            suggestion.style.margin = "2px";
            let br = document.createElement("br");

            suggestion.appendChild(author);
            suggestion.appendChild(suggestionText);
            suggestionsList.appendChild(suggestion);
            suggestionsList.appendChild(br);

    }

    await fetch(getLoggedInUrl, getRequest)
        .then(response => response.text())
        .then(data => createDeleteButton(data))
}

function createDeleteButton(data){
    if(data==="[ADMIN]"){
        let buttonDiv = document.getElementById("button-div");
        let downloadSuggestionsButton = document.createElement("button");
        downloadSuggestionsButton.innerHTML = "Download alle forslag";
        downloadSuggestionsButton.className = "btn";
        downloadSuggestionsButton.style.marginRight = "5px"
        downloadSuggestionsButton.onclick = function(){
            // fetch(downloadUrl, getRequest);
            window.location.replace(downloadUrl);
        }

        let deleteAllSuggestionsButton = document.createElement("button");
        deleteAllSuggestionsButton.innerHTML = "Slet alle forslag";
        deleteAllSuggestionsButton.className = "btn";
        deleteAllSuggestionsButton.onclick = function(){
            if(confirm("Skal alle forslag slettes?")){
                fetch(deleteUrl, deleteRequest);
                location.reload();
            }
        }
        buttonDiv.appendChild(downloadSuggestionsButton);
        buttonDiv.appendChild(deleteAllSuggestionsButton);
    }
}

async function postSuggestion(){
    suggestionJson.suggestion = document.getElementById("suggestion-textarea").value;

    body1 = JSON.stringify(suggestionJson);
    postRequest.body = body1;
    await fetch(postUrl, postRequest)
        .catch((error) => {
            console.error('Error:', error);
        });
    location.reload();
}

f();