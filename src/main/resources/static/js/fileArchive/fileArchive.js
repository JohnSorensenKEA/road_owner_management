
function upload(){
    const selectedFile = document.getElementById("fileToUpload").files[0];
    let formData = new FormData();
    formData.append("file",selectedFile);

    let postRequest ={
        method: "POST",
        body: formData
    }

    if(document.getElementById("fileToUpload").value===""){
        alert("Vælg venligst en fil");
    } else{
        fetch('/uploadFiles',postRequest)
            .then(data => responseAlert(data));
    }


}

function responseAlert(data){
    if(data.status===200){
        alert("Din fil er blevet uploadet!");
        document.getElementById("fileToUpload").value=null;
        document.getElementById("test").innerHTML = "";
        fetchFiles();
    } else if(document.getElementById("fileToUpload").value===""){
        alert("Vælg venligst en fil");
    }
    else{
        alert("Der skete en fejl ved upload");
    }
}