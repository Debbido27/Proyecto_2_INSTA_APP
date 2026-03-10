
package instagram_22141094.GUI;

import Instagram_login_user.Followers_Manager;
import Instagram_login_user.Login_Manager;
import Instagram_login_user.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    
    
    public void construir(){
        removeAll();

        JLabel titulo = new JLabel("Solicitudes de seguimiento", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel listaPanel = new JPanel(new GridBagLayout());
        listaPanel.setBackground(new Color(18, 18, 18));

        String[] solicitudes = followersManager.getSolicitudes(usuarioLogueado);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 60, 5, 60);

        if (solicitudes.length == 0) {
            JLabel vacio = new JLabel("No tienes solicitudes pendientes", JLabel.CENTER);
            vacio.setForeground(new Color(160, 160, 160));
            vacio.setFont(new Font("Arial", Font.PLAIN, 16));
            gbc.gridy = 0;
            listaPanel.add(vacio, gbc);
        } else {
            for (int i = 0; i < solicitudes.length; i++) {
                User u = loginManager.buscarUser(solicitudes[i]);
                if (u != null) {
                    gbc.gridy = i;
                    listaPanel.add(crearCard(u), gbc);
                }
            }
        }
        
         GridBagConstraints gbcFill = new GridBagConstraints();
        gbcFill.gridwidth = GridBagConstraints.REMAINDER;
        gbcFill.weighty = 1.0;
        gbcFill.fill = GridBagConstraints.VERTICAL;
        gbcFill.gridy = solicitudes.length + 1;
        listaPanel.add(new JPanel() {{ setBackground(new Color(18, 18, 18)); }}, gbcFill);

        JScrollPane scroll = new JScrollPane(listaPanel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(18, 18, 18));
        add(scroll, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
    
    
    
    
    
}

