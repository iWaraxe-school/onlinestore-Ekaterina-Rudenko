package by.issoft.store.handler;

public class Runner {

    public void run(String request) {
        AbstractHandler quit = new QuitApp(null);
        AbstractHandler sort = new SortStore(quit);
        AbstractHandler top = new TopStore(sort);

        top.handleRequest(request);
    }
}
