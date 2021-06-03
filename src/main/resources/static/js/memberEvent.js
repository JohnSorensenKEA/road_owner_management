const eventList = document.querySelector(".event-list");
//--------------------------------------------------------------------------------------------------------
const getRequestOptions = {
    headers: {
        "content-type": 'application/json'
    },
    method: 'GET',
    redirect: 'follow'
};

const getComingEventsUrl = "/api/member/event/comingEvents";
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

        eventList.appendChild(con);
    }
}

function getEvents(){
    fetch(getComingEventsUrl, getRequestOptions)
        .then(response => response.json())
        .then(data => setEvents(data))
}

function init(){
    getEvents();
}

init();