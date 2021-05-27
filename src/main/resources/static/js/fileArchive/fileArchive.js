
let masterDiv = document.getElementById("masterDiv");

function test(){
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
        alert("Successful");
        document.getElementById("fileToUpload").value=null;
    } else{
        alert("failure");
    }
}