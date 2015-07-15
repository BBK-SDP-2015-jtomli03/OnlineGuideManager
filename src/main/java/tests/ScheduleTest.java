package tests;

import com.bond.sky.Programme;
import com.bond.sky.Schedule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ScheduleTest {

    @Test
    public void checkAddsChannelCorrectly(){
        Schedule.add("sean_channel", new Programme("movie", "09.00am", "10.00am"));
        assertEquals("movie", Schedule.getSchedule().getSeanChannel().get(0).getProgramme());
    }

    //unknown channel
    @Test (expected = UnknownError.class)
    public void checkDoesntAddIncorrectChannel(){
        Schedule.add("bob_channel", new Programme("movie", "09.00am", "10.00am"));
    }
}
