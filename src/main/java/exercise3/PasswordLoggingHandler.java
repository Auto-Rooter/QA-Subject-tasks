package main.java.exercise3;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PasswordLoggingHandler extends Handler {

    private final StringBuilder stringBuffer = new StringBuilder();

    @Override
    public void publish(final LogRecord record) {
        this.stringBuffer.append(record.getMessage());
    }

    @Override
    public void flush() {
        // No thing to Override
    }

    @Override
    public void close() throws SecurityException {
        // We need to clean the buffer
        reset();
    }

    public String getLogCapturedData(){
        return this.stringBuffer.toString();
    }

    public void reset(){
        this.stringBuffer.setLength(0);
    }


}
