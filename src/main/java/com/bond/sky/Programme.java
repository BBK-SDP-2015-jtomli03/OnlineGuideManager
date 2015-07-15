package com.bond.sky;

public class Programme implements Comparable {
    //values required by online schedule
    private String programme;
    private double width;
    private String align = "center";
    private int paddingL = 0;
    //not required by schedule
    private double startTime; //stored as minutes in 24hr clock (ie. 0.00 = midnight, 540.00 = 9am etc)
    private double endTime; //stored as minutes
    private double durationInMinutes;

    public Programme(String programme, String startTime, String endTime){
        this.programme = programme;
        this.startTime = getTime(startTime);
        this.endTime = getTime(endTime);
        this.durationInMinutes = setDuration();
        this.width = setWidth();
    }

    public Programme(){
        //empty constructor required for JSON return
    }

    //time is based on the day starting at 9am as per the online programme schedule
    private double getTime(String timeIn){
        double mins = Integer.parseInt(timeIn.substring(timeIn.length() - 4, timeIn.length() - 2)); //initialise with minutes
        double hours = Integer.parseInt(timeIn.substring(0, timeIn.indexOf('.'))); // hours eg 9
        if(timeIn.substring(timeIn.length() - 2).equals("am")){
            if(hours > 8 && hours < 12){ // ie 9am to 11am
                return (hours * 60) + mins;
            }
            else if(hours < 9){ //ie 1am (midnight) to 8am
                return (hours * 60) + mins + 1440; // add 1440 to make into 24hr clock (24 * 60)
            }
            else{ // time == 12am (midnight) = 24 * 60 minutes = 1440
                return 1440 + mins;
            }
        }else{ // time is "pm"
            if(hours < 12){ // ie 1pm to 11pm
                return (hours * 60) + mins + 720; // add 720 = (12hrs * 60mins)
            }
            else{ // time == 12pm (midday) = 12 * 60 minutes = 720
                return (hours * 60) + mins;
            }
        }
    }

    private double setDuration(){
        if(endTime < startTime){ //means we've looped into a new day so startTime is 1440 minutes higher than endTime
            return endTime - (startTime - 1440); //minus startTime by 1440 to compare accurately to endTime
        }
        return endTime - startTime;
    }

    //css for programme guide means 0.06944444% width per minute of time
    private double setWidth(){
        return durationInMinutes * 0.06944444;
    }

    /* For Ascending order*/
    @Override
    public int compareTo(Object prog) {
        if(this.startTime == startTime){
            return 0;
        }
        else if(this.startTime > startTime){
            return 1;
        }
        return - 1;
    }

    //getters required for JSON return
    public String getProgramme(){ return programme; }

    public double getWidth() {
        return width;
    }

    public String getAlign() { return align; }

    public int getPaddingL() { return paddingL; }

    //for testing
    public double getStartTime(){
        return startTime;
    }

    public double getEndTime(){
        return endTime;
    }

    public double getDurationInMinutes() {
        return durationInMinutes;
    }
}
