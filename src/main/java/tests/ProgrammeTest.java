package tests;

import com.bond.sky.Programme;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProgrammeTest {

    private static final double DELTA = 1e-8;
    //both am
    Programme prog1 = new Programme("Test", "9.00am", "10.00am");
    //both pm
    Programme prog2 = new Programme("Test", "9.00pm", "10.30pm");
    //am to pm - check handles switch correctly
    Programme prog3 = new Programme("Test", "11.00am", "1.00pm");
    //pm to am - check handles switch correctly so that times between 12am and 9am have higher "start times"
    //than 9am which is when the online schedule starts, ie so that they are ordered correctly in the online scehdule
    Programme prog4 = new Programme("Test", "11.00pm", "1.00am");
    //12pm handled correctly
    Programme prog5 = new Programme("Test", "12.00pm", "10.30pm");
    //12am handled correctly
    Programme prog6 = new Programme("Test", "12.00am", "1.00pm");

    @Test
    public void testConstructorSetsProgrammeAM(){
        assertEquals("Test", prog1.getProgramme());
    }

    @Test
    public void testConstructorSetsWidthAM(){
        assertEquals((60 * 0.06944444), prog1.getWidth(), DELTA);
    }

    // 9 * 60 = 540.00 = 9am
    @Test
    public void testConstructorSetsStartTimeAM(){
        assertEquals(540.00, prog1.getStartTime(), DELTA);
    }

    // 10 * 60 = 600.00 = 10am
    @Test
    public void testConstructorSetsEndTimeAM(){
        assertEquals(600.00, prog1.getEndTime(), DELTA);
    }

    @Test
    public void testConstructorSetsDurationInMinutesAM(){
        assertEquals(60.00, prog1.getDurationInMinutes(), DELTA);
    }

    @Test
    public void testConstructorSetsWidthPM(){
        assertEquals((90 * 0.06944444), prog2.getWidth(), DELTA);
    }

    // 9 * 60 + 720 = 1260.00 = 9pm
    @Test
    public void testConstructorSetsStartTimePM(){
        assertEquals(1260.00, prog2.getStartTime(), DELTA);
    }

    // 10 * 60 + 720 + 30 =  = 10:30pm
    @Test
    public void testConstructorSetsEndTimePM(){
        assertEquals(1350.00, prog2.getEndTime(), DELTA);
    }

    @Test
    public void testConstructorSetsDurationInMinutesPM(){
        assertEquals(90.00, prog2.getDurationInMinutes(), DELTA);
    }

    @Test
    public void testConstructorSetsWidthAMtoPM(){
        assertEquals((120 * 0.06944444), prog3.getWidth(), DELTA);
    }

    // 11 * 60 = 660.00 = 11am
    @Test
    public void testConstructorSetsStartTimeAMtoPM(){
        assertEquals(660.00, prog3.getStartTime(), DELTA);
    }

    // 1 * 60 + 720 =  = 1:00pm
    @Test
    public void testConstructorSetsEndTimeAMtoPM(){
        assertEquals(780.00, prog3.getEndTime(), DELTA);
    }

    @Test
    public void testConstructorSetsDurationInMinutesAMtoPM(){
        assertEquals(120.00, prog3.getDurationInMinutes(), DELTA);
    }

    @Test
    public void testConstructorSetsWidthPMtoAM(){
        assertEquals((120 * 0.06944444), prog4.getWidth(), DELTA);
    }

    // 11 * 60 + 720 = 1380.00 = 11pm
    @Test
    public void testConstructorSetsStartTimePMtoAM(){
        assertEquals(1380.00, prog4.getStartTime(), DELTA);
    }

    // 1 * 60 + (24 * 60) =  = 1:00am
    @Test
    public void testConstructorSetsEndTimePMtoAM(){
        assertEquals(1500.00, prog4.getEndTime(), DELTA);
    }

    @Test
    public void testConstructorSetsDurationInMinutesPMtoAM(){
        assertEquals(120.00, prog4.getDurationInMinutes(), DELTA);
    }

    // 12 * 60 = 720.00 = 12pm (midday)
    @Test
    public void testConstructorSetsStartTime12PM(){
        assertEquals(720.00, prog5.getStartTime(), DELTA);
    }

    // 24 * 60 = 1440.00 = 12am (midnight)
    @Test
    public void testConstructorSetsStartTime12AM(){
        assertEquals(1440.00, prog6.getStartTime(), DELTA);
    }

    @Test
    public void testCompareToForEqualStartTimes(){
        int result = prog1.compareTo(new Programme("another", "9.00am", "11.00am"));
        assertEquals(0, result);
    }

    @Test
    public void testCompareToForLowerCallingStartTimes(){
        int result = prog1.compareTo(prog2);
        assertEquals(-1, result);
    }

    @Test
    public void testCompareToForHigherCallingStartTimes(){
        int result = prog4.compareTo(prog2);
        assertEquals(1, result);
    }

    //test that programmes are ordered correctly according to their start time, with 9am being the earliest
    //start time according to the online schedule
    @Test
    public void testProgrammesOrderedCorrectly(){
        List<Programme> programmes = new ArrayList<>();
        programmes.add(prog1);
        programmes.add(prog2);
        programmes.add(prog3);
        programmes.add(prog4);
        programmes.add(prog5);
        programmes.add(prog6);
        Collections.sort(programmes);
        assertEquals(prog1, programmes.get(0)); //"9.00am"
        assertEquals(prog3, programmes.get(1)); //"11.00am"
        assertEquals(prog5, programmes.get(2)); //"12.00pm"
        assertEquals(prog2, programmes.get(3)); //"9.00pm"
        assertEquals(prog4, programmes.get(4)); //"11.00pm"
        assertEquals(prog6, programmes.get(5)); //"12.00am"
    }

    //start time after end time
    @Test (expected = Exception.class)
    public void testConstructorDoesntSetImpossibleStartAndEndTime(){
        new Programme("Test", "11.00am", "10.00am");
    }

    //wrong format for start time
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void testConstructorHandlesWronglyFormattedStartTime(){
        new Programme("Test", "11:00am", "10.00am");
    }

    //wrong format for end time
    @Test (expected = NumberFormatException.class)
    public void testConstructorHandlesWronglyFormattedEndTime(){
        new Programme("Test", "11.00am", "10.00");
    }

}

