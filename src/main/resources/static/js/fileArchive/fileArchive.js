
function upload(){
    const selectedFile = document.getElementById("fileToUpload").files[0];
    let formData = new FormData();
    formData.append("file",selectedFile);

    let postRequest ={
        method: "POST",
        body: formData
    }

    fetch('/uploadFiles',postRequest)
        .then(data => responseAlert(data));

}

function responseAlert(data){
    if(data.status===200){
        alert("Din fil er blevet uploadet!");
        document.getElementById("fileToUpload").value=null;
    } else if(document.getElementById("fileToUpload").value===""){
        alert("Vælg venligst en fil");
    }
    else{
        alert("Der skete en fejl ved upload");
    }
}