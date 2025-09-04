package model;

import static model.Language.*;

public enum State {
    INI(false), DIG(true),
    PTO(false), SIG(false),
    EXP(false), DIGPTO(true),
    PTODIG(true), EXPDIG(true),
    EXPSIG(false), ERROR(false);

    private final boolean acceptationState;


    State(boolean acceptationState) {
        this.acceptationState = acceptationState;
    }

    public boolean isAcceptationState() {
        return acceptationState;
    }

    public State getInitialState(){
        return INI;
    }

    public State nextState(char c) {
        return switch (this){
            case INI -> nextStateFromINI(c);
            case DIG -> nextStateFromDIG(c);
            case PTO, DIGPTO -> nextStateFromPTO(c);
            case SIG -> nextStateFromSIG(c);
            case EXP -> nextStateFromEXP(c);
            case PTODIG -> nextStateFromPTODIG(c);
            case EXPDIG, EXPSIG -> nextStateFromEXPDIG(c);
            default -> ERROR;
        };
    }

    private State nextStateFromINI(char c) {
        if(Character.isDigit(c)) return DIG;

        return switch (c){
            case PLUS_CHAR, MINUS_CHAR -> SIG;
            case POINT -> PTO;
            default -> ERROR;
        };
    }

    private State nextStateFromDIG(char c) {
        if(Character.isDigit(c)) return DIG;

        return switch (c){
            case POINT -> DIGPTO;
            case EXPO -> EXP;
            default -> ERROR;
        };
    }

    private State nextStateFromEXP(char c) {
        if(Character.isDigit(c)) return EXPDIG;
        else if(c == MINUS_CHAR || c == PLUS_CHAR) return EXPSIG;
        return ERROR;
    }

    private State nextStateFromPTO(char c) {
        if(Character.isDigit(c)) return PTODIG;
        return ERROR;
    }

    private State nextStateFromSIG(char c) {
        if(Character.isDigit(c)) return DIG;
        if(c == POINT) return PTO;
        return ERROR;
    }

    private State nextStateFromPTODIG(char c) {
        if(Character.isDigit(c)) return PTODIG;
        if(c == EXPO) return EXP;
        return ERROR;
    }

    private State nextStateFromEXPDIG(char c) {
        if(Character.isDigit(c)) return EXPDIG;
        return ERROR;
    }
}
