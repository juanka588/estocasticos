 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author JuanCamilo
 */
public class Event implements Comparable<Event> {

    public static final int CALL_IN = 0, CALL_END = 1;
    private int type;
    private double startTime;
    private Call call;

    public Event(int type, double startTime, Call call) {
        this.type = type;
        this.startTime = startTime;
        this.call = call;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    @Override
    public int compareTo(Event o) {
        return Double.compare(startTime, o.startTime);
    }

    @Override
    public String toString() {
        String typeS = "";
        switch (type) {
            case CALL_IN:
                typeS = "call in";
                break;
            case CALL_END:
                typeS = "call end";
                break;
        }
        return "type: " + typeS + " call " + call.toString() + " time: " + startTime;
    }

}
