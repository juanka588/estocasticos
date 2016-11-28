/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LN;

import Data.Agent;
import Data.Call;
import Data.Constants;
import Data.Event;
import Data.ServiceArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author JuanCamilo
 */
public class Simulator {

    public static int BOTS_NUMBER = 20;
    public static int MAX_TIME = 100;
    public static int RATIO = 20;
    public static final double CALL_AVG = 10.0;
    public static final double CALL_DESVIATION = 3.0;
    public static final double LAMDA = 2;
    public static final double LEARNING_RATE = 0.01;
    private static Random rand = new Random();
    private static int maxID = 0;
    private Map<Integer, ServiceArea> commonsAreas;
    private ServiceArea area1;
    private ServiceArea area2;
    private ServiceArea area3;
    private Map<Integer, List<Event>> events;
    private List<Call> calls;

    public void init() {
        //init
        calls = new ArrayList<>();
        area1 = new ServiceArea(new ArrayList<>(), Constants.QYR,
                100, 100, RATIO);
        area2 = new ServiceArea(new ArrayList<>(), Constants.REQUEST,
                200, 200, RATIO);
        area3 = new ServiceArea(new ArrayList<>(), Constants.TECHNICAL_ASSITENCE,
                250, 250, RATIO);
        commonsAreas = new HashMap<>();
        commonsAreas.put(area1.getType(), area1);
        commonsAreas.put(area2.getType(), area2);
        commonsAreas.put(area3.getType(), area3);
        List<Agent> bots1 = generateBots(BOTS_NUMBER, area1, commonsAreas);
        area1.setBots(bots1);

        List<Agent> bots2 = generateBots(BOTS_NUMBER, area2, commonsAreas);
        area2.setBots(bots2);

        List<Agent> bots3 = generateBots(BOTS_NUMBER, area3, commonsAreas);
        area3.setBots(bots3);
        events = new HashMap<>();
        double startTime = 0;
        int startTimeInt;
        while (startTime < MAX_TIME) {
            startTime += getTime();
            startTimeInt = (int) startTime;
            Call call = getRandomCall();
            calls.add(call);
            List<Event> evts = events.get(startTimeInt);
            if (evts == null) {
                evts = new ArrayList<>();
            }
            evts.add(new Event(Event.CALL_IN, startTime, call));
            events.put(startTimeInt, evts);
        }
    }

    public void run() {
        // begin simulation
        int time = 0;
        List<Event> current;
        while (time <= MAX_TIME) {
            current = events.get(time);
            System.out.println("time actual: " + time);
            if (current != null) {
                //new call in
                System.out.println("current: " + current.size());
                addEventsToRandomQueue(current);
            }
            //refresh states

            //check queues
            area1.checkQueue();
            area2.checkQueue();
            area3.checkQueue();
            //make progress attending calls
            progressCalls();
            //try to help into the service Area
            area1.checkInnerHelp();
            area2.checkInnerHelp();
            area3.checkInnerHelp();

            //try to help other areas
            area1.checkOuterHelp(getMostBusyArea());
            area2.checkOuterHelp(getMostBusyArea());
            area3.checkOuterHelp(getMostBusyArea());

            //move agents 
            area1.moveAgents();
            area2.moveAgents();
            area3.moveAgents();
            time += 1;
        }
    }

    private static List<Agent> generateBots(int size, ServiceArea area, Map<Integer, ServiceArea> common) {
        List<Agent> bots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            bots.add(new Agent(generateExperticeMap(area), area, common, getRandomX(area), getRandomY(area)));
        }
        return bots;
    }

    private static int getRandomX(ServiceArea area) {
        return (int) (area.getX() - area.getRatio() + Math.random() * area.getRatio() * 2);
    }

    private static int getRandomY(ServiceArea area) {
        return (int) (area.getY() - area.getRatio() + Math.random() * area.getRatio() * 2);
    }

    private static Map<Integer, Double> generateExperticeMap(ServiceArea area) {
        Map<Integer, Double> expertice = new HashMap<>();
        expertice.put(Constants.QYR, 1.0);
        expertice.put(Constants.REQUEST, 1.0);
        expertice.put(Constants.TECHNICAL_ASSITENCE, 1.0);
        expertice.put(area.getType(), 0.6 + Math.random() * 0.3);
        return expertice;
    }

    private static double getTime() {
        return Math.log(1 - rand.nextDouble()) / (-LAMDA);
    }

    private static Call getRandomCall() {
        int rand2 = (int) (Math.random() * 3);
        int type = 0;
        switch (rand2) {
            case 0:
                type = Constants.QYR;
                break;
            case 1:
                type = Constants.REQUEST;
                break;
            case 2:
                type = Constants.TECHNICAL_ASSITENCE;
                break;
        }
        return new Call(getNormal(), type, maxID++);
    }

    public static double getNormal() {
        return rand.nextGaussian() * CALL_DESVIATION + CALL_AVG;
    }

    private void addEventsToRandomQueue(List<Event> current) {
        int rand2;
        for (Event evt : current) {
            rand2 = rand.nextInt(3);
            switch (rand2) {
                case 0:
                    area1.getPendingCalls().add(evt.getCall());
                    break;
                case 1:
                    area2.getPendingCalls().add(evt.getCall());
                    break;
                case 2:
                    area3.getPendingCalls().add(evt.getCall());
                    break;
            }
        }
    }

    private void progressCalls() {
        for (Call call : calls) {
            if (!call.isFinish()) {
                call.progress();
            }
        }
    }

    private ServiceArea getMostBusyArea() {
        //TODO: check
        return area1;
    }
}
