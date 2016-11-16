package org.jw.vprc.clock;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.jw.vprc.TestClock;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.jw.vprc.clock.DateTimeHelper.getEndOfMonth;
import static org.jw.vprc.clock.DateTimeHelper.getNumericMonth;
import static org.jw.vprc.clock.DateTimeHelper.getStartOfMonthAtStartOfDay;

public class DateTimeHelperTest {
    private Clock clock;

    private DateTime customisedRealTime;

    @Before
    public void setUp() throws Exception {
        customisedRealTime = DateTime.now().withMonthOfYear(2).withDayOfMonth(1);//Always 1st February
        clock = new TestClock(customisedRealTime);
    }

    @Test
    public void verifyGetNumericMonth() throws Exception {
        assertEquals(1, getNumericMonth("January"));
        assertEquals(2, getNumericMonth("February"));
        assertEquals(3, getNumericMonth("March"));
        assertEquals(4, getNumericMonth("April"));
        assertEquals(5, getNumericMonth("May"));
        assertEquals(6, getNumericMonth("June"));
        assertEquals(7, getNumericMonth("July"));
        assertEquals(8, getNumericMonth("August"));
        assertEquals(9, getNumericMonth("September"));
        assertEquals(10, getNumericMonth("October"));
        assertEquals(11, getNumericMonth("November"));
        assertEquals(12, getNumericMonth("December"));

        assertEquals(1, getNumericMonth("Jan"));
        assertEquals(2, getNumericMonth("Feb"));
        assertEquals(3, getNumericMonth("Mar"));
        assertEquals(4, getNumericMonth("Apr"));
        assertEquals(6, getNumericMonth("Jun"));
        assertEquals(7, getNumericMonth("Jul"));
        assertEquals(8, getNumericMonth("Aug"));
        assertEquals(9, getNumericMonth("Sep"));
        assertEquals(10, getNumericMonth("Oct"));
        assertEquals(11, getNumericMonth("Nov"));
        assertEquals(12, getNumericMonth("Dec"));
    }

    @Test
    public void verifyCalculatedHours() throws Exception {
        assertThat(DateTimeHelper.getTotalHours(300), equalTo("5"));
        assertThat(DateTimeHelper.getTotalHours(299), equalTo("4.59"));
        assertThat(DateTimeHelper.getTotalHours(240), equalTo("4"));
        assertThat(DateTimeHelper.getTotalHours(245), equalTo("4.5"));
    }

    @Test
    public void verifyTotalMinutes() throws Exception {
        assertThat(DateTimeHelper.getTotalMinutes("4.60"), equalTo(300));
        assertThat(DateTimeHelper.getTotalMinutes("4.59"), equalTo(299));
        assertThat(DateTimeHelper.getTotalMinutes("4"), equalTo(240));
        assertThat(DateTimeHelper.getTotalMinutes("4.05"), equalTo(245));
    }

    @Test
    public void verifyGetStartOfMonthAtStartOfDay() throws Exception {
        DateTime expectedStartOfMonthOfCustomisedRealTime = customisedRealTime.withTimeAtStartOfDay();
        assertEquals(expectedStartOfMonthOfCustomisedRealTime, getStartOfMonthAtStartOfDay(clock, "february"));
    }

    @Test
    public void verifyGetEndOfMonth() throws Exception {
        DateTime startOfMonthOfCustomiseRealTime = customisedRealTime.withTimeAtStartOfDay();
        DateTime expectedEndOfMonthOfCustomisedRealTime =
                customisedRealTime.withTimeAtStartOfDay().plusMonths(1).withDayOfMonth(1).minusDays(1).withTime(23,59,59,999);
        assertEquals(expectedEndOfMonthOfCustomisedRealTime, getEndOfMonth(startOfMonthOfCustomiseRealTime));
    }
}