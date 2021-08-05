public class Factory {
    public Command getCommand(CommandWord command, String secondWord){
        switch (command) {
            case GO:
                return new GOcommand(command, secondWord);
            case BACK:
                return new BACKcommand(command, secondWord);
            case LOOK:
                return new LOOKcommand(command, secondWord);
            case TAKE:
                return new TAKEcommand(command, secondWord);
            case DROP:
                return new DROPcommand(command, secondWord);
            case OPEN:
                return new OPENcommand(command, secondWord);
            case EAT:
                return new EATcommand(command, secondWord);
            case DRINK:
                return new DRINKcommand(command, secondWord);
            case LIGHT:
                return new LIGHTcommand(command, secondWord);
            case FIGHT:
                return new FIGHTcommand(command, secondWord);
            case RUN:
                return new RUNcommand(command, secondWord);
            case UNLOCK:
                return new UNLOCKcommand(command, secondWord);
            case QUIT:
                return new QUITcommand(command, secondWord);
            case HELP:
                return new HELPcommand(command, secondWord);
            case CHANGE:
                return new CHANGEcommand(command, secondWord);
            case READ:
                return new READcommand(command, secondWord);
        }
        return new UNKNOWNcommand(command, secondWord);
    }
}
