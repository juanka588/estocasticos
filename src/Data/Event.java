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
public class Event {
    
    public static int CALL_IN=0,CALL_END=1;
    private int type;
    private long startTime;
    private Call call;

    public Event(int type, long startTime, Call call) {
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
    
    
}
