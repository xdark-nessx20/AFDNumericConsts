package model;

public class Automaton {
    private State currentState;

    public Automaton() {
        currentState = State.INI;
    }

    public void resetCurrentState() {
        currentState = currentState.getInitialState();
    }

    public String verify(String number){
        number = number.trim().replace(" ", "").toUpperCase();

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            currentState = currentState.nextState(c);
            if (currentState == State.ERROR) break;
        }

        String answer = formattedAnswer(number, currentState.isAcceptationState());
        resetCurrentState();

        return answer;
    }

    private String formattedAnswer(String number, boolean isAcceptationState) {
        return String.format(
            ("'%s' is " + (isAcceptationState? "a valid":"an invalid")
                + " const"), number
        );
    }
}
