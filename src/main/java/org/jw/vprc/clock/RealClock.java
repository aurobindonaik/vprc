package org.jw.vprc.clock;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class RealClock implements Clock {
    @Override
    public DateTime now() {
        return DateTime.now();
    }
}
