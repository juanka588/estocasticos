/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author JuanCamilo
 */
public class LineChart_AWT extends JFrame {
    
    public LineChart_AWT(String applicationTitle, String chartTitle,ChartPanel content) {
        super(applicationTitle);
        content.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(content);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
