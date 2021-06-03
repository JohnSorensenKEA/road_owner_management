const overview = document.querySelector(".overview");

function getMemberCons () {
    return document.querySelectorAll(".member-con");
}

const inputs = document.querySelectorAll(".chosen-input");
const chosenBtns = document.querySelectorAll(".chosen-btn");

const saveBtn = document.querySelector(".save-btn");
const deleteBtn = document.querySelector(".delete-btn");
const resetBtn = document.querySelector(".reset-btn");

const passwordBox = document.querySelector(".password-box");

const userId = document.querySelector(".member-id");
const email = document.querySelector(".member-number");
const telephoneNumber = document.querySelector(".road-name");

const createWindow = document.querySelector(".create-window");
const createBtn = document.querySelector(".create-btn");
const cancelBtn = document.querySelector(".cancel-btn");
const newMemberBtn = document.querySelector(".new-member-btn");

function getNewInput () {
    return document.querySelectorAll(".new-input");
}
const newEmail = document.querySelector(".new-member-number");
const newTelephoneNumber = document.querySelector(".new-road-name");

let users = [];
let selectedUser = -1;

//-----------------------------------------------------------

const getRequestOptions = {
    headers: {
        "content-type": 'application/json'
    },
    method: 'GET',
    redirect: 'follow'
};

let body1 = null;

const deleteRequestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'DELETE',
    body: body1
}

const putRequestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'PUT',
    body: body1
}

const postRequestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: body1
}

let user = {
    id: null,
    email: null,
    telephoneNumber: null
}

const allNonAdminUsersUrl = "/api/admin/user/nonAdminUsers";
const deleteUserUrl = "/api/admin/user/deleteUser";
const updateUserUrl = "/api/admin/user/updateUser";
const newUserUrl = "/api/admin/user/newUser";
const passwordResetUrl = "/api/admin/user/resetPassword";
//-----------------------------------------------------------

function setChosen(id){
    selectedUser = id;
    const user = users[selectedUser];

    userId.value = user.id;
    email.value = user.email;
    telephoneNumber.value = user.telephoneNumber;
}

function deselect(el){
    el.className = "member-con clickable";
    inputs.forEach(i => {i.value = ""; i.disabled = true});
    chosenBtns.forEach(b => b.disabled = true);
}

function select(el){
    getMemberCons().forEach(con => con.className = "member-con clickable");
    inputs.forEach(i => {i.disabled = false});
    chosenBtns.forEach(b => b.disabled = false);

    el.className = "member-con clickable member-con-selected";
    setChosen(el.getAttribute("id"));
}

function classSearch(element, term){
    if (element === null){
        return element;
    } else if (element === document){
        return null;
    }

    if (element.classList.contains(term)){
        return element;
    } else {
        return classSearch(element.parentNode, term);
    }
}

function clickable(ev){
    const el = ev.target;
    const fEl = classSearch(el, "clickable");

    if (fEl === null){

    } else if (fEl.classList.contains("member-con-selected")){
        deselect(fEl);
    } else if (fEl.classList.contains("member-con")){
        select(fEl);
    }
}

function deleteUser(){
    user = users[selectedUser];
    body1 = JSON.stringify(user);
    deleteRequestOptions.body = body1;
    fetch(deleteUserUrl, deleteRequestOptions)
        .then(response => location.reload());
}

function saveUser(){
    let user1 = {
        id: users[selectedUser].id,
        telephoneNumber: telephoneNumber.value,
        email: email.value
    }

    body1 = JSON.stringify(user1);
    putRequestOptions.body = body1;

    fetch(updateUserUrl, putRequestOptions)
        .then(response => {location.reload();});
}

function resetUser(){
    let user1 = users[selectedUser];

    body1 = JSON.stringify(user1);
    putRequestOptions.body = body1;

    fetch(passwordResetUrl, putRequestOptions)
        .then(response => response.text())
        .then(data => passwordBox.innerHTML = data);
}

document.addEventListener("click", ev => clickable(ev))
deleteBtn.addEventListener("click", deleteUser);
saveBtn.addEventListener("click", saveUser);
resetBtn.addEventListener("click", resetUser);

function newUser(){
    let user1 = {
        email: newEmail.value,
        telephoneNumber: newTelephoneNumber.value,
    }

    body1 = JSON.stringify(user1);
    postRequestOptions.body = body1;
    fetch(newUserUrl, postRequestOptions)
        .then(response => {location.reload();});
}
createBtn.addEventListener("click", ev => newUser());
newMemberBtn.addEventListener("click", ev => {createWindow.hidden = false});
cancelBtn.addEventListener("click", ev => {
    createWindow.hidden = true; getNewInput().forEach(i => i.value = "")
});

function addUser(user, number){
    let container = document.createElement("div");
    container.className = "member-con clickable";
    container.setAttribute("id", number);

    let memberNumber = document.createElement("p");
    memberNumber.innerHTML = "Email: " + user.email;
    memberNumber.className = "member-number info-text";
    container.appendChild(memberNumber);

    let memberName = document.createElement("p");
    memberName.innerHTML = "Telefonnummer: " + user.telephoneNumber;
    memberName.className = "member-name info-text";
    container.appendChild(memberName);

    overview.appendChild(container);
}

function setUsers(data){
    users = data;
    for (let i = 0; i < data.length; i++){
        addUser(data[i], i);
    }
}

function getUsers(){
    fetch(allNonAdminUsersUrl, getRequestOptions)
        .then(response => response.json())
        .then(data => setUsers(data))
}

function init(){
    getUsers();
}

init();