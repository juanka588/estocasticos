/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Color;

/**
 *
 * @author JuanCamilo
 */
public class Constants {

    public static final int TECHNICAL_ASSITENCE = 0, QYR = 1, REQUEST = 2,
            VELOCITY = 2, REDIRECT_COST = 3;

    public static final double MAX_EXP = 0.1;

    //GUI settings
    public static final int BOT_SIZE = 12;
    public static final Color BOT_COLOR = new Color(0, 47, 178, 144);
    public static final Color AREA_COLOR = new Color(255, 190, 25, 144);
     public static final Color DEFAULT_COLOR = new Color(255, 190, 25, 144);

    public static String getTypeString(int type) {
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
        return typeS;
    }

}
