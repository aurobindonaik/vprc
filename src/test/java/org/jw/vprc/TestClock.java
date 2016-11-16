package org.jw.vprc;

import org.joda.time.DateTime;
import org.jw.vprc.clock.Clock;

public class TestClock implements Clock {
    private DateTime now;

    public TestClock() {
        this.now = DateTime.now();
    }

    public TestClock(DateTime now) {
        this.now = now;
    }

    @Override
    public DateTime now() {
        return now;
    }

    public DateTime tick(){
        return now.plusMillis(1);
    }
}
