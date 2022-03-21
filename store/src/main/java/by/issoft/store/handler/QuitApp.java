package by.issoft.store.handler;

public class QuitApp extends AbstractHandler {

    public QuitApp(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String request) {
        if (request.equals("quit")) {
            System.exit(0);
        } else super.handleRequest(request);
    }
}
