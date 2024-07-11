function sendMessage() {
    var chatInput = document.getElementById('chatInput');
    var message = chatInput.value.trim();
    if (message) {
        var chatContainer = document.querySelector('.chat-container');

        // Create user message bubble
        var userBubble = document.createElement('div');
        userBubble.classList.add('chat-bubble', 'user');
        userBubble.innerText = message;
        chatContainer.appendChild(userBubble);

        // Clear chat input
        chatInput.value = '';

        // Simulate bot response after 1 second (replace with actual bot response logic)
        setTimeout(function () {
            var botBubble = document.createElement('div');
            botBubble.classList.add('chat-bubble', 'bot');
            botBubble.innerText = '챗봇 응답 예시: ' + message;
            chatContainer.appendChild(botBubble);

            // Scroll to the bottom of the chat container
            chatContainer.scrollTop = chatContainer.scrollHeight;
        }, 1000);

        // Scroll to the bottom of the chat container
        chatContainer.scrollTop = chatContainer.scrollHeight;
    }
}

document.getElementById('sendBtn').addEventListener('click', sendMessage);

document.getElementById('chatInput').addEventListener('keydown', function(event) {
    if (event.key === 'Enter' && !event.shiftKey) {
        event.preventDefault();
        sendMessage();
    }
});