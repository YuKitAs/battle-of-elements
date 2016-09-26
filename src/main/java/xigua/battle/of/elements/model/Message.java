package xigua.battle.of.elements.model;

import xigua.battle.of.elements.logic.statemachine.GameState;

public class Message {
    private final Class<? extends GameState> sender;
    private final Class<? extends GameState> reciever;
    private final String title;
    private final Object content;

    public Message(Class<? extends GameState> sender, Class<? extends GameState> reciever, String title, Object
            content) {
        this.sender = sender;
        this.reciever = reciever;
        this.title = title;
        this.content = content;
    }

    public Class<? extends GameState> getSender() {
        return sender;
    }

    public Class<? extends GameState> getReciever() {
        return reciever;
    }

    public String getTitle() {
        return title;
    }

    public Object getContent() {
        return content;
    }
}
