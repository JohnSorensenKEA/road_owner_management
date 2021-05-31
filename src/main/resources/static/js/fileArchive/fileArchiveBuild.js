
let getRequest ={
    method: "GET",
    headers: {
        "content-type": "application/json"
    }
}

function fetchFiles(){
    fetch('/getAllFiles',getRequest)
        //.then(response => console.log(response.json()))
        .then(response => response.json())
        .then(data => ListFileNamesTest(data));

}

function ListFileNamesTest(data){
    data.forEach(file => makeHTML(file));
}

async function makeHTML(file){
    let masterdiv = document.getElementById("test");
    let tempdiv = document.createElement("div");
    let tempA = document.createElement("a");
    tempA.innerHTML = file;
    tempA.href = "/file/"+file;
    tempA.className = "fileA";
    tempdiv.appendChild(tempA);
    masterdiv.appendChild(tempdiv);
    console.log("forloop: "+file);
}

fetchFiles();