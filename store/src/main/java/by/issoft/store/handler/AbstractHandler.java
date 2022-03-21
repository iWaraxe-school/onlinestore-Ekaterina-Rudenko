package by.issoft.store.handler;

public abstract class AbstractHandler {
    protected AbstractHandler nextHandler;

    public AbstractHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String request){
        if(nextHandler != null){
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Invalid request");
        }
    }

}
