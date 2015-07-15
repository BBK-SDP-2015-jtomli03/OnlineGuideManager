package com.bond.sky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Schedule is a singleton
public class Schedule {
    //Static initializer to make Schedule thread safe
    // -> instance used frequently, therefore overhead reduced compared to use of synchronized.
    private static Schedule schedule = new Schedule();
    private static List<Programme> seanChannel = new ArrayList<>();
    private static List<Programme> georgeChannel = new ArrayList<>();
    private static List<Programme> rogerChannel = new ArrayList<>();
    private static List<Programme> timothyChannel = new ArrayList<>();
    private static List<Programme> pierceChannel = new ArrayList<>();
    private static List<Programme> danielChannel = new ArrayList<>();

    private Schedule(){
        // don't delete - empty constructor used for JSON
    }

    public static Schedule getSchedule(){
        return schedule;
    }

    //need to include a function to check that programmes don't overlap
    public static void add(String channel, Programme programme){
        if(channel.equals("sean_channel")){
            seanChannel.add(programme);
            Collections.sort(seanChannel);
        }else if(channel.equals("george_channel")){
            georgeChannel.add(programme);
            Collections.sort(georgeChannel);
        }else if(channel.equals("roger_channel")){
            rogerChannel.add(programme);
            Collections.sort(rogerChannel);
        }else if(channel.equals("timothy_channel")){
            timothyChannel.add(programme);
            Collections.sort(timothyChannel);
        }else if(channel.equals("pierce_channel")){
            pierceChannel.add(programme);
            Collections.sort(pierceChannel);
        }else if(channel.equals("daniel_channel")){
            danielChannel.add(programme);
            Collections.sort(danielChannel);
        }else{
            throw new UnknownError("Channel not recognised in xml file");
        }
    }

    public List<Programme> getSeanChannel() { return seanChannel;}

    public List<Programme> getGeorgeChannel() {
        return georgeChannel;
    }

    public List<Programme> getRogerChannel() {
        return rogerChannel;
    }

    public List<Programme> getTimothyChannel() {
        return timothyChannel;
    }

    public List<Programme> getPierceChannel() {
        return pierceChannel;
    }

    public List<Programme> getDanielChannel() {
        return danielChannel;
    }
}

