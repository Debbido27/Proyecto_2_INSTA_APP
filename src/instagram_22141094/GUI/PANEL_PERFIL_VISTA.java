
package instagram_22141094.GUI;

import Instagram_login_user.Login_Manager;
import Instagram_login_user.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;


public class PANEL_PERFIL_VISTA extends JPanel {
    
    private User userVisto;
    private String usuarioLogueado;
    private Login_Manager loginManager;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    
    public PANEL_PERFIL_VISTA(User userVisto, String usuarioLogueado, Login_Manager loginManager, CardLayout cardlayout, JPanel contentPanel){
        this.userVisto=userVisto;
        this.usuarioLogueado=usuarioLogueado;
        this.loginManager=loginManager;
        this.cardLayout=cardLayout;
        this.contentPanel=contentPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(18,18,18));
        add(construir().BorderLayout.CENTER);
        
    }
    
}
