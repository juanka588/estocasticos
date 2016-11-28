/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author JuanCamilo
 */
public class ServiceArea {

    private List<Agent> bots;
    private int type;
    private Queue<Call> pendingCalls;
    //center coordenates and ratio
    private int x, y, ratio;

    public ServiceArea(List<Agent> bots, int type, int x, int y, int ratio) {
        this.bots = bots;
        this.type = type;
        this.pendingCalls = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.ratio = ratio;
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

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
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

    public void checkQueue() {
        if (pendingCalls.isEmpty()) {
            return;
        }
        for (Agent bot : bots) {
            if (!pendingCalls.isEmpty() && !bot.isBusy()) {
                bot.setState(Agent.BUSY);
                bot.allocateService(pendingCalls.poll());
            }
        }
    }

    public void checkInnerHelp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void checkOuterHelp(ServiceArea mostBusy) {
        if (mostBusy == null) {
            System.out.println("No area is busy right now");
            return;
        }
        for (Agent bot : bots) {
            if (!bot.isBusy()) {
                boolean help = makeDecision();
                if (help) {
                    mostBusy.getBots().add(bot);
                    bot.setState(Agent.HELPING);
                    bot.startMoving(mostBusy);
                }
            }
        }
    }

    public void moveAgents() {
        for (Agent bot : bots) {
            bot.move();
        }
    }

    private boolean makeDecision() {
        return 0 == (int) (Math.random() * 2);
    }

}
