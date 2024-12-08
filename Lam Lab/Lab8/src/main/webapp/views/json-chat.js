var username = null;
var websocket = null;

function init() {
    while (username === null || username.trim() === "") {
        username = prompt("Enter username:");
    }

    websocket = new WebSocket(`ws://localhost:8080/Lab8/json/chat/${username}`);

    websocket.onopen = function (resp) {
        console.log("Connected to server", resp);
    };

    websocket.onmessage = function (resp) {
        var msg = JSON.parse(resp.data);
        var output = document.getElementById('messages');
        output.innerHTML = `${output.innerHTML}<p><b>${msg.sender || "System"}</b>: ${msg.text}</p>`;
        
        if (msg.type !== 2) {
            document.getElementById('client-count').innerHTML = `Chatters: ${msg.count}`;
        }
    };

    websocket.onerror = function (resp) {
        alert('An error occurred. Closing connection.');
        console.error("WebSocket error:", resp);
    };

    websocket.onclose = function (resp) {
        alert(resp.reason || 'Connection closed.');
        console.log("Connection closed", resp);
    };
}

function send() {
    var input = document.getElementById("message");
    var msg = { sender: username, text: input.value, type: 2 };
    websocket.send(JSON.stringify(msg));
    input.value = '';
}
