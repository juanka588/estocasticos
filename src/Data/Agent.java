/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Map;

/**
 *
 * @author JuanCamilo
 */
public class Agent {

    public static final int BUSY = 101, FREE = 102, HELPING = 103;
    private Map<Integer, Double> expertices;
    private ServiceArea origin;
    private Map<Integer, ServiceArea> commonAreas;
    private int state;
    private int velocity;
    private int x, y;
    private int destX, destY;
    private boolean moving;

    public Agent(Map<Integer, Double> expertices, ServiceArea origin, Map<Integer, ServiceArea> commonAreas, int x, int y) {
        this(expertices, origin, commonAreas, FREE, Constants.VELOCITY, x, y);
    }

    public Agent(Map<Integer, Double> expertices, ServiceArea origin, Map<Integer, ServiceArea> commonAreas, int state, int velocity, int x, int y) {
        this.expertices = expertices;
        this.origin = origin;
        this.commonAreas = commonAreas;
        this.state = state;
        this.velocity = velocity;
        this.x = x;
        this.y = y;
        this.moving = false;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<Integer, Double> getExpertices() {
        return expertices;
    }

    public void setExpertices(Map<Integer, Double> expertices) {
        this.expertices = expertices;
    }

    public ServiceArea getOrigin() {
        return origin;
    }

    public void setOrigin(ServiceArea origin) {
        this.origin = origin;
    }

    public Map<Integer, ServiceArea> getCommonAreas() {
        return commonAreas;
    }

    public void setCommonAreas(Map<Integer, ServiceArea> commonAreas) {
        this.commonAreas = commonAreas;
    }

    public void allocateService(Call call) {
        if (origin.getType() == call.getType()) {
            call.getAllocatedAgents().add(this);
        } else {
            //añadimos la llamada y en la siguiente iteracion se mirara la cola
            ServiceArea redirect = commonAreas.get(call.getType());
            redirect.getPendingCalls().add(call);
        }

    }

    @Override
    public String toString() {
        String stateS = "";
        switch (state) {
            case FREE:
                stateS = "Free";
                break;
            case BUSY:
                stateS = "Busy";
                break;
            case HELPING:
                stateS = "Helping";
                break;
        }
        return origin.toString() + " state " + stateS;
    }

    public boolean isBusy() {
        return state != FREE;
    }

    public void startMoving(ServiceArea mostBusy) {
        moving = true;
        //TODO: make destination point
    }

    public void move() {
        if (moving) {
         //   this.x =
        } else {
            //Move randomly
        }

    }

}
