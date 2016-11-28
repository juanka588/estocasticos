/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import static Data.Constants.MAX_EXP;
import static LN.Simulator.LEARNING_RATE;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanCamilo
 */
public class Call {
    
    private double duration;
    private double remaining;
    private int type;
    private int id;
    private boolean finish;
    private List<Agent> allocatedAgents;
    
    public Call(double duration, int type, int id) {
        this.duration = duration;
        this.type = type;
        this.id = id;
        remaining = duration;
        allocatedAgents = new ArrayList<>();
        this.finish = false;
    }
    
    public double getDuration() {
        return duration;
    }
    
    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Agent> getAllocatedAgents() {
        return allocatedAgents;
    }
    
    public void setAllocatedAgents(List<Agent> allocatedAgents) {
        this.allocatedAgents = allocatedAgents;
    }
    
    @Override
    public String toString() {
        return "type: " + Constants.getTypeString(type) + " complexity: " + duration;
    }
    
    public boolean isFinish() {
        return finish;
    }
    
    public void progress() {
        double discount;
        for (Agent agent : allocatedAgents) {
            discount = (1 / agent.getExpertices().get(this.type));
            this.remaining = remaining - discount;
        }
        if (remaining <= 0.0) {
            finish = true;
            //TODO: do some statistics

            //apply learned stuff
            for (Agent agent : allocatedAgents) {
                Double exp = agent.getExpertices().get(this.type);
                exp = exp - LEARNING_RATE;
                if (exp <= MAX_EXP) {
                    exp = MAX_EXP;
                }
                agent.getExpertices().put(this.type, exp);
                agent.setCurrentCall(null);
                agent.setCallsCount(agent.getCallsCount() + 1);
            }
            //remove helping agents
            for (Agent agent : allocatedAgents) {
                if (agent.getState() == Agent.HELPING) {
                    agent.startMoving(agent.getOrigin());
                } else {
                    agent.setState(Agent.FREE);
                }
            }
            
        }
    }
    
}
