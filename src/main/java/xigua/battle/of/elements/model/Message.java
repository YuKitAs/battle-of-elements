package xigua.battle.of.elements.model;

import xigua.battle.of.elements.logic.statemachine.GameState;

public class Message {
    private final Class<? extends GameState> sender;
    private final Class<? extends GameState> reciever;
    private final String title;
    private final String message;

    public Message(Class<? extends GameState> sender, Class<? extends GameState> reciever, String title, String
            message) {
        this.sender = sender;
        this.reciever = reciever;
        this.title = title;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message other = (Message) o;

        if (!sender.equals(other.sender)) return false;
        if (!reciever.equals(other.reciever)) return false;
        if (!title.equals(other.title)) return false;
        return message.equals(other.message);

    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + reciever.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
