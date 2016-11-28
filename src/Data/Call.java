/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

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

    public double getComplexity() {
        return duration;
    }

    public void setComplexity(double duration) {
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
        String typeS = "";
        switch (type) {
            case Constants.QYR:
                typeS = "Queja";
                break;
            case Constants.REQUEST:
                typeS = "Peticion";
                break;
            case Constants.TECHNICAL_ASSITENCE:
                typeS = "Asistencia T";
                break;
        }
        return "type: " + typeS + " complexity: " + duration;
    }

    public boolean isFinish() {
        return finish;
    }

    public void progress() {
        int discount;
        for (Agent agent : allocatedAgents) {
            discount = (int) (1 / agent.getExpertices().get(this.type));
            this.remaining = remaining - discount;
        }
        if (remaining <= 0.0) {
            finish = true;
            //TODO: do some statistics

            //apply learned stuff
            for (Agent agent : allocatedAgents) {
                Double exp = agent.getExpertices().get(this.type);
                exp = exp - LEARNING_RATE;
                agent.getExpertices().put(this.type, exp);
            }
            //remove helping agents
            
        }

    }

}
