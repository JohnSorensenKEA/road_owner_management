const titleInp = document.querySelector(".title-inp");
const startInp = document.querySelector(".start-inp");
const endInp = document.querySelector(".end-inp");
const msgInp = document.querySelector(".msg-inp");

const sendBtn = document.querySelector(".send-btn");

const inputs = document.querySelectorAll(".inp");

const eventList = document.querySelector(".event-list");

let events = [];

//--------------------------------------------------------------------------------------------------------
let body1 = null;

const getRequestOptions = {
    headers: {
        "content-type": 'application/json'
    },
    method: 'GET',
    redirect: 'follow'
};

const deleteRequestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'DELETE',
    body: body1
};

const postRequestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: body1
};

let event = {
    id: -1,
    title: "",
    message: "",
    startTime: null,
    endTime: null
};

const allEventsUrl = "/api/admin/event/all";
const newEventUrl = "/api/admin/event/newEvent";
const deleteEventUrl = "/api/admin/event/deleteEvent";
//--------------------------------------------------------------------------------------------------------

function setEvents(data){
    events = data;

    for (let i = 0; i < events.length; i++){
        let event = events[i];

        let con = document.createElement("div");
        con.className = "event";
        con.id = i;

        let title = document.createElement("h2");
        title.innerHTML = event.title;
        title.className = "event-title";
        con.appendChild(title);

        let dates = document.createElement("p");
        dates.innerHTML = event.startTime + " - " + event.endTime;
        dates.className = "event-dates";
        con.appendChild(dates);

        let msg = document.createElement("p");
        msg.innerHTML = event.message;
        msg.className = "event-msg";
        con.appendChild(msg);

        let delBtn = document.createElement("input");
        delBtn.value = "slet";
        delBtn.type = "button";
        delBtn.className = "event-delete-btn";
        delBtn.id = i;
        con.appendChild(delBtn);

        eventList.appendChild(con);
    }
}

function getEvents(){
    fetch(allEventsUrl, getRequestOptions)
        .then(response => response.json())
        .then(data => setEvents(data))
}

function deleteEvent(id){
    event = events[id];
    body1 = JSON.stringify(event);
    deleteRequestOptions.body = body1;
    fetch(deleteEventUrl, deleteRequestOptions)
        .then(response => response.status)
        .then(code => {eventList.innerHTML = ""; getEvents()});
}

function newEvent(){
    let event1 = {
        title: titleInp.value,
        message: msgInp.value,
        startTime: startInp.value,
        endTime: endInp.value
    };
    body1 = JSON.stringify(event1);
    postRequestOptions.body = body1;
    fetch(newEventUrl, postRequestOptions)
        .then(response => response.json())
        .then(data => {eventList.innerHTML = "";
            getEvents();
            inputs.forEach(i => i.value = "");
        });
}

function eventCheck(ev){
    let el = ev.target;
    if (el.classList.contains("event-delete-btn")){
        deleteEvent(el.id);
    }
}

document.addEventListener("click", ev => eventCheck(ev));

sendBtn.addEventListener("click", newEvent);

function init(){
    getEvents();
}

init();