/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GUI.Movable;
import java.awt.Point;
import java.util.Map;

/**
 *
 * @author JuanCamilo
 */
public class Agent implements Movable {

    public static final int BUSY = 101, FREE = 102, HELPING = 103;
    private Map<Integer, Double> expertices;
    private ServiceArea origin;
    private Map<Integer, ServiceArea> commonAreas;
    private int state;
    private int velocity;
    private int x, y;
    private ServiceArea dest;
    private boolean moving;
    private Call currentCall;
    private long callsCount;
    private int busyTime;
    private int freeTime;

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
        this.currentCall = null;
        this.dest = null;
        this.busyTime = 0;
        this.freeTime = 0;
        this.callsCount = 0;
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

    public Call getCurrentCall() {
        return currentCall;
    }

    public void setCurrentCall(Call currentCall) {
        this.currentCall = currentCall;
    }

    public long getCallsCount() {
        return callsCount;
    }

    public void setCallsCount(long callsCount) {
        this.callsCount = callsCount;
    }

    public void allocateService(Call call) {
        if (origin.getType() == call.getType()) {
            setState(Agent.BUSY);
            call.getAllocatedAgents().add(this);
            setCurrentCall(call);
        } else {
            //añadimos la llamada y en la siguiente iteracion se mirara la cola
            ServiceArea redirect = commonAreas.get(call.getType());
            redirect.add(call);
        }

    }

    @Override
    public String toString() {
        String stateS = getStateString(state);
        return " state " + stateS + " origin: " + Constants.getTypeString(origin.getType());
    }

    public boolean isBusy() {
        return state != FREE;
    }

    public void startMoving(ServiceArea mostBusy) {
        moving = true;
        dest = mostBusy;
        state = HELPING;
    }

    public ServiceArea getDest() {
        return dest;
    }

    public boolean isMoving() {
        return moving;
    }

    public int getBusyTime() {
        return busyTime;
    }

    public int getFreeTime() {
        return freeTime;
    }
    

    public void move() {
        boolean arrive = true;
        int destX, destY;
        if (state == FREE) {
            freeTime++;
        } else {
            busyTime++;
        }
        if (moving) {
            destX = dest.getX();
            destY = dest.getY();
            if (destX > x) {
                this.x += velocity;
            } else {
                this.x -= velocity;
            }
            if (Math.abs(destX - x) <= velocity) {
                x = destX;
            } else {
                arrive = false;
            }
            if (destY > y) {
                this.y += velocity;
            } else {
                this.y -= velocity;
            }
            if (Math.abs(destY - x) <= velocity) {
                y = destY;
            } else {
                arrive = false;
            }
            if (arrive) {
                //finally arrived
                moving = false;
                state = Agent.FREE;
                if (dest.getPendingCalls().isEmpty()) {
                    //teletransport
                    startMoving(origin);
                } else {
                    Call call = dest.getPendingCalls().poll();
                    allocateService(call);
                }
                // dest = null;
            }
        } else {
            //Move randomly
            this.x = x + 2;
            this.y = y + 2;
        }

    }

    @Override
    public Point getPosition() {
        return new Point(x, y);
    }

    public static String getStateString(int state) {
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
        return stateS;
    }
}
