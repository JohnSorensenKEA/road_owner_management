
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
    let downloadA = document.createElement("a");
    let deleteA = document.createElement("a");
    deleteA.innerHTML = " X ";
    deleteA.className = "fileAdel";
    deleteA.href = "/delFile/"+file;
    downloadA.innerHTML = file;
    downloadA.href = "/file/"+file;
    downloadA.className = "fileA";
    tempdiv.appendChild(deleteA);
    tempdiv.appendChild(downloadA);
    masterdiv.appendChild(tempdiv);
    console.log("forloop: "+file);
}

fetchFiles();