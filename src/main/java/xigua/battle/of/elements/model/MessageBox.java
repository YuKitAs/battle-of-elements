package xigua.battle.of.elements.model;

import xigua.battle.of.elements.logic.GameState;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MessageBox {
    private final Map<Class<? extends GameState>, MessageQueue> messageQueueMap = new HashMap<>();

    public void sendMessage(Message message) {
        ensureKeyExists(message.getReciever());
        messageQueueMap.get(message.getReciever()).putMessage(message);
    }

    public Message peekMessage(Class<? extends GameState> reciever) {
        ensureKeyExists(reciever);
        return messageQueueMap.get(reciever).peekMessage();
    }

    public Message pollMessage(Class<? extends GameState> reciever) {
        ensureKeyExists(reciever);
        return messageQueueMap.get(reciever).pollMessage();
    }

    private void ensureKeyExists(Class<? extends GameState> reciever) {
        if (!messageQueueMap.containsKey(reciever)) {
            messageQueueMap.put(reciever, new MessageQueue());
        }
    }

    private class MessageQueue {
        private final Queue<Message> messageQueue = new LinkedList<>();

        private void putMessage(Message message) {
            messageQueue.offer(message);
        }

        private Message peekMessage() {
            return messageQueue.peek();
        }

        private Message pollMessage() {
            return messageQueue.poll();
        }
    }
}