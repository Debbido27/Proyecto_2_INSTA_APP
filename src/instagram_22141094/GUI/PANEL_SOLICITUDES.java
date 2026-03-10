
package instagram_22141094.GUI;

import Instagram_login_user.Followers_Manager;
import Instagram_login_user.Login_Manager;
import Instagram_login_user.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    
    private JPanel crearCard(User u){
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(30, 30, 30));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(54, 54, 54)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        card.setPreferredSize(new Dimension(0, 70));

        JLabel fotoLabel = new JLabel();
        fotoLabel.setPreferredSize(new Dimension(50, 50));
        fotoLabel.setBackground(new Color(60, 60, 60));
        fotoLabel.setOpaque(true);
        fotoLabel.setHorizontalAlignment(JLabel.CENTER);
        fotoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fotoLabel.setForeground(Color.WHITE);

        String profilePath = u.getProfilePath();
        if (profilePath != null && !profilePath.isEmpty()
                && !profilePath.equals("USUARIO_DEFAULT.png")
                && !profilePath.equals("image.jpg")
                && new File(profilePath).exists()) {
            try {
                ImageIcon icon = new ImageIcon(profilePath);
                Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                fotoLabel.setText(u.getUsername().substring(0, 1).toUpperCase());
            }
        } else {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("USUARIO_DEFAULT.png"));
                Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                fotoLabel.setText(u.getUsername().substring(0, 1).toUpperCase());
            }
        }
        card.add(fotoLabel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(30, 30, 30));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel(u.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        usernameLabel.setForeground(Color.WHITE);
        infoPanel.add(usernameLabel, gbc);

        JLabel nombreLabel = new JLabel(u.getFullname());
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        nombreLabel.setForeground(new Color(160, 160, 160));
        infoPanel.add(nombreLabel, gbc);

        card.add(infoPanel, BorderLayout.CENTER);

        // Botones aceptar y rechazar
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        botonesPanel.setBackground(new Color(30, 30, 30));

        JButton aceptarBtn = new JButton("Aceptar");
        aceptarBtn.setBackground(new Color(0, 149, 246));
        aceptarBtn.setForeground(Color.WHITE);
        aceptarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        aceptarBtn.setBorderPainted(false);
        aceptarBtn.setFocusPainted(false);
        aceptarBtn.addActionListener(e -> {
            followersManager.aceptarSolicitud(u.getUsername(), usuarioLogueado);
            construir();
        });

        JButton rechazarBtn = new JButton("Rechazar");
        rechazarBtn.setBackground(new Color(237, 73, 86));
        rechazarBtn.setForeground(Color.WHITE);
        rechazarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        rechazarBtn.setBorderPainted(false);
        rechazarBtn.setFocusPainted(false);
        rechazarBtn.addActionListener(e -> {
            followersManager.rechazarSolicitud(u.getUsername(), usuarioLogueado);
            construir();
        });

        botonesPanel.add(aceptarBtn);
        botonesPanel.add(rechazarBtn);
        card.add(botonesPanel, BorderLayout.EAST);

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(45, 45, 45));
                infoPanel.setBackground(new Color(45, 45, 45));
                botonesPanel.setBackground(new Color(45, 45, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(30, 30, 30));
                infoPanel.setBackground(new Color(30, 30, 30));
                botonesPanel.setBackground(new Color(30, 30, 30));
            }
        });

        return card;

    }
    
    
    
    
    
}

