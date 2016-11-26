/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JuanCamilo
 */
public class Agent {

    private Map<Integer, Double> expertices;
    private ServiceArea origin;
    private List<ServiceArea> commonAreas;

    public Agent(Map<Integer, Double> expertices, ServiceArea origin, List<ServiceArea> commonAreas) {
        this.expertices = expertices;
        this.origin = origin;
        this.commonAreas = commonAreas;
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

    public List<ServiceArea> getCommonAreas() {
        return commonAreas;
    }

    public void setCommonAreas(List<ServiceArea> commonAreas) {
        this.commonAreas = commonAreas;
    }

    
}
