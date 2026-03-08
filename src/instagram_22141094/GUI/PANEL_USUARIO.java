
package instagram_22141094.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PANEL_USUARIO extends JFrame{
   private String usuario;
   
    public PANEL_USUARIO(String usuario){
      this.usuario=usuario;
      principal();
      
    }
    
    public JPanel principal(){
        
       JPanel Principal = new JPanel();
       setBackground(Color.WHITE);
       JLabel bienvenida = new JLabel("Bienvenido"+usuario,JLabel.CENTER);
       bienvenida.setFont(new Font("Arial",Font.BOLD,24));
       
        
        return Principal;
    }
    
    private JPanel menuLatera(){
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(0,0,0,0));
        menu.setPreferredSize(new Dimension(250,800));
        menu.setBorder(BorderFactory.createMatteBorder(0,0,0,1 new Color(38, 38, 38)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 15, 5, 15);
        
        JLabel logo = new JLabel("INSTAGRAM");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(Color.WHITE);
        gbc.insets = new Insets(20, 15, 20, 15);
        menu.add(logo, gbc);

       JButton feedBtn = crearBotonMenu("Feed");
        JButton buscarBtn = crearBotonMenu("Buscar");
        JButton inboxBtn = crearBotonMenu("Inbox");
        JButton perfilBtn = crearBotonMenu("Perfil");
        JButton subirBtn = crearBotonMenu("Subir");

        feedBtn.addActionListener(e -> cardLayout.show(contentPanel, "FEED"));
        buscarBtn.addActionListener(e -> cardLayout.show(contentPanel, "BUSCAR"));
        inboxBtn.addActionListener(e -> cardLayout.show(contentPanel, "INBOX"));
        perfilBtn.addActionListener(e -> cardLayout.show(contentPanel, "PERFIL"));
        subirBtn.addActionListener(e -> cardLayout.show(contentPanel, "SUBIR"));

        gbc.insets = new Insets(5, 15, 5, 15);
        menu.add(feedBtn, gbc);
        menu.add(buscarBtn, gbc);
        menu.add(inboxBtn, gbc);
        menu.add(perfilBtn, gbc);
        menu.add(subirBtn, gbc);
    
    return menu;
        
    }
}
