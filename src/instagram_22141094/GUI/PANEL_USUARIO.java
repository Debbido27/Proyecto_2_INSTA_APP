
package instagram_22141094.GUI;

import java.awt.CardLayout;
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
import javax.swing.SwingConstants;


public class PANEL_USUARIO extends JFrame{
   private String usuario;
   private CardLayout cardLayout;
    private JPanel contentPanel;
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
        menu.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(38, 38, 38)));
        
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
    
    private JButton crearBotonMenu(String texto){
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(0, 0, 0));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setPreferredSize(new Dimension(220, 45));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(38, 38, 38));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(0, 0, 0));
        }
    });
    
    return btn;

    }
    
    private JPanel crearFeedPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18));
        JLabel label = new JLabel("Feed posts", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        panel.add(label);
        return panel;
    }
    
    private JPanel crearBuscarPanel(){
     JPanel panel = new JPanel();
    panel.setBackground(new Color(18, 18, 18));
    JLabel label = new JLabel("BUSCAR - Perfiles y Hashtags", JLabel.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 24));
    label.setForeground(Color.WHITE);
    panel.add(label);
    return panel;
    }
    
    private JPanel crearInboxPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18));
        JLabel label = new JLabel ("INBOX mensajes privados",JLabel.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,24));
        label.setForeground(Color.WHITE);
        panel.add(label);
        return panel;
    }
    
    private JPanel crearSubirPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18));
        JLabel label = new JLabel("Subir publicacion",JLabel.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,24));
        label.setForeground(Color.WHITE);
        panel.add(label);
        return panel;
    }
    
    private JPanel crearPerfilPanel(){
    JPanel panel = new JPanel();
    panel.setBackground(new Color(18, 18, 18));
    JLabel label = new JLabel("PERFIL DE " + usuario, JLabel.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 24));
    label.setForeground(Color.WHITE);
    panel.add(label);
    return panel;
}
    
}
