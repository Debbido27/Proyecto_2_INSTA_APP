
package instagram_22141094.GUI;

import Instagram_login_user.Login_Manager;
import Instagram_login_user.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
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
        add(construir(), BorderLayout.CENTER);
        
    }
    
    private JPanel construir(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18,18,18));
        
               JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(18, 18, 18));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 20, 60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // FOTO
        JLabel fotoLabel = new JLabel();
        fotoLabel.setPreferredSize(new Dimension(100, 100));
        fotoLabel.setBackground(new Color(60, 60, 60));
        fotoLabel.setOpaque(true);
        fotoLabel.setFont(new Font("Arial", Font.BOLD,36));
        
    }
    
}
