/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.List;
import java.util.Queue;

/**
 *
 * @author JuanCamilo
 */
public class ServiceArea {

    private List<Agent> bots;
    private int type;
    private Queue<Call> pendingCalls;

    public ServiceArea(List<Agent> bots, int type, Queue<Call> pendingCalls) {
        this.bots = bots;
        this.type = type;
        this.pendingCalls = pendingCalls;
    }

    public List<Agent> getBots() {
        return bots;
    }

    public void setBots(List<Agent> bots) {
        this.bots = bots;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Queue<Call> getPendingCalls() {
        return pendingCalls;
    }

    public void setPendingCalls(Queue<Call> pendingCalls) {
        this.pendingCalls = pendingCalls;
    }
    
    
}
