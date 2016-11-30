/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author JuanCamilo
 */
public class Statistics {

    private double avgTime;
    private double finishCalls;
    private double avgBusyTime;
    private double avgFreeTime;

    public Statistics(double avgTime, double finishCalls, double avgBusyTime, double avgFreeTime) {
        this.avgTime = avgTime;
        this.finishCalls = finishCalls;
        this.avgBusyTime = avgBusyTime;
        this.avgFreeTime = avgFreeTime;
    }

    public double getStat(int column) {
        switch (column) {
            case 0:
                return avgTime;
            case 1:
                return finishCalls;
            case 2:
                return avgBusyTime;
            case 3:
                return avgFreeTime;
        }
        return 0.0;
    }

}
