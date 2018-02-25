package scanner.handler;

import java.io.File;
import java.util.List;

public class HandlerDecorator<T> implements Handler<T>{

    private List<Handler> handlers;

    public HandlerDecorator(List<Handler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void handle(T t) {
        handlers.forEach(handler -> handler.handle(t));
    }
}
