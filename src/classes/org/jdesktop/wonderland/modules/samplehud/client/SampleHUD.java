/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jdesktop.wonderland.modules.samplehud.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jdesktop.wonderland.client.hud.CompassLayout.Layout;
import org.jdesktop.wonderland.client.hud.HUD;
import org.jdesktop.wonderland.client.hud.HUDComponent;
import org.jdesktop.wonderland.client.hud.HUDManagerFactory;

/**
 *
 * @author jos
 */
public class SampleHUD {

    private static final Logger logger = Logger.getLogger(SampleHUD.class.getName());
    
    private HUD mainHUD;
    private HUDComponent sampleHud;
    
    private JButton oneButton;

    /**
     * Constructor to grab the main HUD area, and display the HUD within it.
     */
    public SampleHUD() {
        mainHUD = HUDManagerFactory.getHUDManager().getHUD("main");
        displayHud();
    }


    private void displayHud() {
        createPanelForHUD();
        createHUDComponent();
        setHudComponentVisible(true);
    }

    /**
     * Creates a JPanel which will contain the elements to be shown in the HUD.
     * @return panelForHUD
     */
    private JPanel createPanelForHUD() {
        //We are not packing or showing so this should be thread-safe.
        JPanel panelForHUD = new JPanel();

        oneButton = new JButton("Click me! I am a button! :P");
        oneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                logger.warning("I'm gonna do nothing but you can keep on clicking!");
            }
        });
        panelForHUD.add(oneButton);

        return panelForHUD;
    }

    /**
     * Creates the HUD Component, if it does not exist yet, and adds it to the
     * CENTER of the main HUD area (entire screen above the 3D scene).
     */
    private void createHUDComponent() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                if (sampleHud == null) {
                    JPanel panelForHUD = createPanelForHUD(); //Think about this a bit more! can I Test Drive it? windowlicker!
                    sampleHud = mainHUD.createComponent(panelForHUD);
                    sampleHud.setDecoratable(true);
                    sampleHud.setName("Sample HUD");
                    sampleHud.setPreferredLocation(Layout.CENTER);
                    mainHUD.addComponent(sampleHud);
                }
            }
        });

    }

    /**
     * Changes the visibility of the HUD according to the boolean passed.
     * @param show
     */
    public void setHudComponentVisible(final boolean show) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                sampleHud.setVisible(show);
            }
        });

    }
}