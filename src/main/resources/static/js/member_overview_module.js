const overview = document.querySelector(".overview");
//const chosen = document.querySelector(".Chosen");

function getMemberCons () {
    return document.querySelectorAll(".member-con");
}

const inputs = document.querySelectorAll(".chosen-input");
const chosenBtns = document.querySelectorAll(".chosen-btn");

const userSelect = document.querySelector(".user-select");
const userRemoveBtn = document.querySelector(".user-remove-btn");
const saveBtn = document.querySelector(".save-btn");
const deleteBtn = document.querySelector(".delete-btn");

const memberId = document.querySelector(".member-id");
const memberNumber = document.querySelector(".member-number");
const roadName = document.querySelector(".road-name");
const roadNumber = document.querySelector(".road-number");
const ownerName = document.querySelector(".owner-name");

const createWindow = document.querySelector(".create-window");
const createBtn = document.querySelector(".create-btn");
const cancelBtn = document.querySelector(".cancel-btn");
const newMemberBtn = document.querySelector(".new-member-btn");

function getNewInput () {
    return document.querySelectorAll(".new-input");
}
const newMemberNumber = document.querySelector(".new-member-number");
const newRoadName = document.querySelector(".new-road-name");
const newRoadNumber = document.querySelector(".new-member-number");
const newOwnerName = document.querySelector(".new-owner-name");

let members = [];
let selectedMember = -1;

let users = [];

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

let member = {
    id: null,
    roadName: null,
    roadNumber: null,
    memberNumber: null,
    ownerName: null,
    user: {
        id: null
    }
}

const allMembersUrl = "/api/admin/member/allMembers";
const availableUsersUrl = "/api/admin/user/allAvailableUsers";
const deleteMemberUrl = "/api/admin/member/deleteMember";
const removeUserUrl = "/api/admin/member/removeUser";
const updateMemberUrl = "/api/admin/member/updateMember"
const newMemberUrl = "/api/admin/member/newMember";
//-----------------------------------------------------------

function setUsers(data){
    users = data;

    for (let i = 0; i < data.length; i++){
        const userOption = document.createElement("option");
        userOption.value = i;
        userOption.innerHTML = data[i].email;
        userSelect.appendChild(userOption);
    }
}

function getUsers(){
    fetch(availableUsersUrl, getRequestOptions)
        .then(response => response.json())
        .then(data => setUsers(data));
}

function setChosen(id){
    selectedMember = id;
    const member = members[selectedMember];

    memberId.value = member.id;
    memberNumber.value = member.memberNumber;
    roadName.value = member.roadName;
    roadNumber.value = member.roadNumber;
    ownerName.value = member.ownerName;

    if (member.user === null){
        userRemoveBtn.disabled = true;
        userSelect.disabled = false;
        const unselect = document.createElement("option");
        unselect.innerHTML = "Intet valgt";
        unselect.value = -1;
        userSelect.appendChild(unselect);

        getUsers();
    } else {
        userRemoveBtn.disabled = false;
        const email = document.createElement("option");
        email.innerHTML = member.user.email;
        userSelect.appendChild(email);
        userSelect.disabled = true;
    }
}

function deselect(el){
    el.className = "member-con clickable";
    inputs.forEach(i => {i.value = ""; i.disabled = true});
    chosenBtns.forEach(b => b.disabled = true);
    userSelect.innerHTML = "";

}

function select(el){
    getMemberCons().forEach(con => con.className = "member-con clickable");
    inputs.forEach(i => {i.disabled = false});
    chosenBtns.forEach(b => b.disabled = false);
    userSelect.innerHTML = "";

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

function deleteMember(){
    member = members[selectedMember];
    body1 = JSON.stringify(member);
    deleteRequestOptions.body = body1;
    fetch(deleteMemberUrl, deleteRequestOptions)
        .then(response => location.reload());
}

function removeUser(){
    member = members[selectedMember];
    body1 = JSON.stringify(member);
    putRequestOptions.body = body1;
    fetch(removeUserUrl, putRequestOptions)
        .then(response => location.reload());
}

function saveMember(){
    let member1 = {
        id: members[selectedMember].id,
        roadName: roadName.value,
        roadNumber: roadNumber.value,
        memberNumber: memberNumber.value,
        ownerName: ownerName.value,
        user: null
    }

    if (userSelect.value > -1){
        member1.user = users[userSelect.value];
    }

    body1 = JSON.stringify(member1);
    putRequestOptions.body = body1;

    fetch(updateMemberUrl, putRequestOptions)
        .then(response => {location.reload();});
}

document.addEventListener("click", ev => clickable(ev))
deleteBtn.addEventListener("click", deleteMember);
saveBtn.addEventListener("click", saveMember);
userRemoveBtn.addEventListener("click", removeUser);

function newMember(){
    let member1 = {
        roadName: newRoadName.value,
        roadNumber: newRoadNumber.value,
        memberNumber: newMemberNumber.value,
        ownerName: newOwnerName.value,
    }

    body1 = JSON.stringify(member1);
    postRequestOptions.body = body1;
    fetch(newMemberUrl, postRequestOptions)
        .then(response => {location.reload();});
}
createBtn.addEventListener("click", ev => newMember());
newMemberBtn.addEventListener("click", ev => {createWindow.hidden = false});
cancelBtn.addEventListener("click", ev => {
    createWindow.hidden = true; getNewInput().forEach(i => i.value = "")
});

function addMember(member, number){
    let container = document.createElement("div");
    container.className = "member-con clickable";
    container.setAttribute("id", number);

    let memberNumber = document.createElement("p");
    memberNumber.innerHTML = "Medlemsnummer: " + member.memberNumber;
    memberNumber.className = "member-number info-text";
    container.appendChild(memberNumber);

    let memberName = document.createElement("p");
    memberName.innerHTML = "Ejer navn: " + member.ownerName;
    memberName.className = "member-name info-text";
    container.appendChild(memberName);

    let memberAddress = document.createElement("p");
    memberAddress.innerHTML = member.roadName + " " + member.roadNumber;
    memberAddress.className = "member-address info-text";
    container.appendChild(memberAddress);

    let id = document.createElement("input");
    id.innerHTML = member.id;
    id.hidden = true;
    id.className = "member-id";
    container.appendChild(id);

    overview.appendChild(container);
}

function setMembers(data){
    members = data;
    for (let i = 0; i < data.length; i++){
        addMember(data[i], i);
    }
}

function getMembers(){
    fetch(allMembersUrl, getRequestOptions)
        .then(response => response.json())
        .then(data => setMembers(data))
}

function init(){
    getMembers();
}

init();