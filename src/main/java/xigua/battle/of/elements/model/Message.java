package xigua.battle.of.elements.model;

import xigua.battle.of.elements.logic.statemachine.GameState;

public class Message {
    private final Class<? extends GameState> sender;
    private final Class<? extends GameState> receiver;
    private final String title;
    private final Object content;

    public Message(Class<? extends GameState> sender, Class<? extends GameState> receiver, String title, Object
            content) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
    }

    public Class<? extends GameState> getSender() {
        return sender;
    }

    public Class<? extends GameState> getReceiver() {
        return receiver;
    }

    public String getTitle() {
        return title;
    }

    public Object getContent() {
        return content;
    }
}
