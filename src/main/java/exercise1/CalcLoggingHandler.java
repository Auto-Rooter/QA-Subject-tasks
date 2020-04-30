package main.java.exercise1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class CalcLoggingHandler extends Handler {

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
