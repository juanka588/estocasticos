/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import GUI.Movable;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author JuanCamilo
 */
public class ServiceArea implements Movable {

    private List<Agent> bots;
    private int type;
    private Queue<Call> pendingCalls;
    private List<Agent> visitors;
    //center coordenates and ratio
    private int x, y, ratio;
    private int callsCounter;

    public ServiceArea(List<Agent> bots, int type, int x, int y, int ratio) {
        this.bots = bots;
        this.type = type;
        this.pendingCalls = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.ratio = ratio;
        this.visitors = new ArrayList<>();
        this.callsCounter = 0;
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
                bot.allocateService(pendingCalls.poll());
            }
        }
    }

    public void checkInnerHelp() {
        if (pendingCalls.isEmpty()) {
            System.out.println("no need to help");
            return;
        } else {
            List<Agent> busys = getOcupedAgents();
            if (busys.isEmpty()) {
                System.out.println("nobody is busy");
                return;
            }
            Agent temp;
            for (Agent bot : getFreeAgents()) {
                boolean help = makeDecision();
                if (help) {
                    temp = getRandomElement(busys);
                    if (temp.getCurrentCall() != null) {
                        bot.allocateService(temp.getCurrentCall());
                        bot.setState(Agent.HELPING);
                    }
                }
            }
        }
    }

    public void checkOuterHelp(ServiceArea mostBusy) {
        if (mostBusy == null) {
            System.out.println("No area is busy right now");
            return;
        }
        for (Agent bot : getFreeAgents()) {
            boolean help = makeDecision();
            if (help) {
                bot.setState(Agent.HELPING);
                bot.startMoving(mostBusy);
                visitors.add(bot);
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

    @Override
    public String toString() {
        return "calls: " + pendingCalls.size() + " bots: " + bots.size() + " type: " + Constants.getTypeString(type);
    }

    @Override
    public Point getPosition() {
        return new Point(x, y);
    }

    public List<Agent> getFreeAgents() {
        List<Agent> free = new ArrayList<>();
        for (Agent ag : bots) {
            if (!ag.isBusy()) {
                free.add(ag);
            }
        }
        return free;
    }

    private List<Agent> getOcupedAgents() {
        List<Agent> busy = new ArrayList<>();
        for (Agent ag : bots) {
            if (ag.isBusy()) {
                busy.add(ag);
            }
        }
        return busy;
    }

    private static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int pos = (int) (Math.random() * list.size());
        return list.get(pos);
    }

    public void checkVisitors() {
        if (visitors.isEmpty()) {
            System.out.println("no visitors yet");
            return;
        }
        List<Agent> removed = new ArrayList<>();
        Agent temp;
        for (int i = 0; i < visitors.size(); i++) {
            temp = visitors.get(i);
            if (!temp.isMoving()) {
                Queue<Call> pc = temp.getDest().getPendingCalls();
                if (pc.isEmpty()) {
                    if (!temp.getOrigin().getPendingCalls().isEmpty()) {
                        temp.startMoving(temp.getOrigin());
                        removed.add(temp);
                    }
                }
            }
        }
        visitors.removeAll(removed);
    }

    public void add(Call call) {
        callsCounter++;
        pendingCalls.add(call);
    }

    public int getCallsCounter() {
        return callsCounter;
    }

}
