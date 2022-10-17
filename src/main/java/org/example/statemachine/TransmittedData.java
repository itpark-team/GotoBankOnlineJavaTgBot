package org.example.statemachine;

public class TransmittedData {

    private State state;
    private DataStorage dataStorage;

    public TransmittedData() {
        state = State.WaitingCommandStart;
        dataStorage = new DataStorage();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }
}
