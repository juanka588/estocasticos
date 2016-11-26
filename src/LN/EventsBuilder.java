/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LN;

import Data.Agent;
import Data.Constants;
import Data.ServiceArea;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JuanCamilo
 */
public class EventsBuilder {

    public static int BOTS_NUMBER = 20;

    public static void main(String[] args) {
        ServiceArea area1 = new ServiceArea(new ArrayList<>(), Constants.QYR, new LinkedList<>());
        ServiceArea area2 = new ServiceArea(new ArrayList<>(), Constants.REQUEST, new LinkedList<>());
        ServiceArea area3 = new ServiceArea(new ArrayList<>(), Constants.TECHNICAL_ASSITENCE, new LinkedList<>());

        List<ServiceArea> commonsAreas = Arrays.asList(area1, area2, area3);

        List<Agent> bots1 = generateBots(BOTS_NUMBER, area1, commonsAreas);
        area1.setBots(bots1);

        List<Agent> bots2 = generateBots(BOTS_NUMBER, area2, commonsAreas);
        area2.setBots(bots2);

        List<Agent> bots3 = generateBots(BOTS_NUMBER, area3, commonsAreas);
        area3.setBots(bots3);
        
        

    }

    private static List<Agent> generateBots(int size, ServiceArea area, List<ServiceArea> common) {
        List<Agent> bots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            bots.add(new Agent(generateExperticeMap(area), area, common));
        }
        return bots;
    }

    private static Map<Integer, Double> generateExperticeMap(ServiceArea area) {
        Map<Integer, Double> expertice = new HashMap<>();
        expertice.put(Constants.QYR, 1.0);
        expertice.put(Constants.REQUEST, 1.0);
        expertice.put(Constants.TECHNICAL_ASSITENCE, 1.0);
        expertice.put(area.getType(), 0.6 + Math.random() * 0.3);
        return expertice;
    }

}
