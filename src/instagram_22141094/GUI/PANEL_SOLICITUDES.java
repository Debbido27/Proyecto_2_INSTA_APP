
package instagram_22141094.GUI;

import Instagram_login_user.Followers_Manager;
import Instagram_login_user.Login_Manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Dell
 */
public class PANEL_SOLICITUDES extends JPanel{
    
    private String usuarioLogueado;
    private Login_Manager loginManager;
    private Followers_Manager followersManager;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public PANEL_SOLICITUDES(String usuarioLogueado, Login_Manager loginManager,
                              Followers_Manager followersManager,
                              CardLayout cardLayout, JPanel contentPanel) {
        this.usuarioLogueado = usuarioLogueado;
        this.loginManager = loginManager;
        this.followersManager = followersManager;
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(18, 18, 18));
        construir();
    }
    
    
    
    
    
}

