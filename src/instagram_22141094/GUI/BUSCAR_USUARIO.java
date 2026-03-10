
package instagram_22141094.GUI;

import Instagram_login_user.Login_Manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class BUSCAR_USUARIO extends JPanel {
    private Login_Manager loginManager;
    private String usuarioLogueado;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel resultadosPanel;
    
    public BUSCAR_USUARIO(String usuarioLogueado, Login_Manager loginManager, CardLayout cardLayout, JPanel contentPanel){
        
        this.usuarioLogueado = usuarioLogueado;
        this.loginManager = loginManager;
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(18,18,18));
        construir();
    }
    
    
    private void construir(){
        JPanel buscarPanel = new JPanel (new GridBagLayout());
        buscarPanel.setBackground(new Color(18,18,18));
        buscarPanel.setBorder(BorderFactory.createEmptyBorder(30,60,20,60));
        
        
    }
    
    
}
