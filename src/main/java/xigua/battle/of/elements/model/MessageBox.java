package xigua.battle.of.elements.model;

import xigua.battle.of.elements.logic.statemachine.GameState;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MessageBox {
    private final Map<Class<? extends GameState>, MessageQueue> messageQueueMap = new HashMap<>();

    public void sendMessage(Message message) {
        ensureKeyExists(message.getReceiver());
        messageQueueMap.get(message.getReceiver()).putMessage(message);
    }

    public Message peekMessage(Class<? extends GameState> receiver) {
        ensureKeyExists(receiver);
        return messageQueueMap.get(receiver).peekMessage();
    }

    public Message pollMessage(Class<? extends GameState> receiver) {
        ensureKeyExists(receiver);
        return messageQueueMap.get(receiver).pollMessage();
    }

    private void ensureKeyExists(Class<? extends GameState> receiver) {
        if (!messageQueueMap.containsKey(receiver)) {
            messageQueueMap.put(receiver, new MessageQueue());
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