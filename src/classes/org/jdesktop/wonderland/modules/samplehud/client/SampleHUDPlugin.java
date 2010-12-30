package org.jdesktop.wonderland.modules.samplehud.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import org.jdesktop.wonderland.client.BaseClientPlugin;
import org.jdesktop.wonderland.client.jme.JmeClientMain;
import org.jdesktop.wonderland.client.login.ServerSessionManager;
import org.jdesktop.wonderland.common.annotation.Plugin;

/**
 *
 * @author jos
 */
@Plugin
public class SampleHUDPlugin extends BaseClientPlugin {
    
    private JMenuItem sampleHUDMI = null;
    private SampleHUD sampleHUD;
    private boolean sampleHUDEnabled = false;

    /**
     * Creates a new Menu Item for the HUD that will allow to show/hide it.
     * @param loginInfo
     */
    @Override
    public void initialize(ServerSessionManager loginInfo) {
        sampleHUDMI = new JCheckBoxMenuItem("Sample HUD");
        sampleHUDMI.setSelected(false);
        sampleHUDMI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                sampleHUDEnabled = !sampleHUDEnabled;
                sampleHUDMI.setSelected(sampleHUDEnabled);
                if (sampleHUD == null ) {
                    sampleHUD = new SampleHUD();
                }
                else
                    sampleHUD.setHudComponentVisible(sampleHUDEnabled);
            }
        });

        super.initialize(loginInfo);
    }

    /**
     * Adds the Menu Item created in initialize to the Window Menu in the
     * Wonderland Client
     */
    @Override
    public void activate() {
        JmeClientMain.getFrame().addToWindowMenu(sampleHUDMI);
    }

     /**
     * Removes the Menu Item created in initialize to the Window Menu in the
     * Wonderland Client
     */
    @Override
    public void deactivate() {
        JmeClientMain.getFrame().removeFromWindowMenu(sampleHUDMI);
    }

}


