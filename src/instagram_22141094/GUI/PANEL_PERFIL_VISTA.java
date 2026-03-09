
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
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        
        String profilePath = userVisto.getProfilePath();
        if (profilePath != null && !profilePath.isEmpty()
                && !profilePath.equals("USUARIO_DEFAULT.png")
                && !profilePath.equals("image.jpg")
                && new File(profilePath).exists()) {
            try {
                ImageIcon icon = new ImageIcon(profilePath);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                fotoLabel.setText(userVisto.getUsername().substring(0, 1).toUpperCase());
            }
        } else {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource("USUARIO_DEFAULT.png"));
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                fotoLabel.setText(userVisto.getUsername().substring(0, 1).toUpperCase());
            }
        }
        
          gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridheight = 3;
        infoPanel.add(fotoLabel, gbc);

        // USERNAME + BOTON
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 1;
        JPanel usernamePanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        usernamePanel.setBackground(new Color(18, 18, 18));

        JLabel usernameLabel = new JLabel(userVisto.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        usernameLabel.setForeground(Color.WHITE);
        usernamePanel.add(usernameLabel);

        if (userVisto.getUsername().equals(usuarioLogueado)) {
            JButton editarBtn = new JButton("Editar perfil");
            editarBtn.setBackground(new Color(54, 54, 54));
            editarBtn.setForeground(Color.WHITE);
            editarBtn.setFont(new Font("Arial", Font.BOLD, 13));
            editarBtn.setBorderPainted(false);
            editarBtn.setFocusPainted(false);
            editarBtn.addActionListener(e -> cardLayout.show(contentPanel, "PERFIL"));
            usernamePanel.add(editarBtn);
        } else {
            JButton seguirBtn = new JButton("Seguir");
            seguirBtn.setBackground(new Color(0, 149, 246));
            seguirBtn.setForeground(Color.WHITE);
            seguirBtn.setFont(new Font("Arial", Font.BOLD, 13));
            seguirBtn.setBorderPainted(false);
            seguirBtn.setFocusPainted(false);
            // lógica de seguir se agrega después
            usernamePanel.add(seguirBtn);
        }
        infoPanel.add(usernamePanel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        JPanel statsPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 0));
        statsPanel.setBackground(new Color(18, 18, 18));
        statsPanel.add(crearStat("0", "publicaciones"));
        statsPanel.add(crearStat("0", "seguidores"));
        statsPanel.add(crearStat("0", "siguiendo"));
        infoPanel.add(statsPanel, gbc);

        // NOMBRE COMPLETO
        gbc.gridx = 1; gbc.gridy = 2;
        JLabel nombreLabel = new JLabel(userVisto.getFullname());
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreLabel.setForeground(Color.WHITE);
        infoPanel.add(nombreLabel, gbc);

        panel.add(infoPanel, BorderLayout.NORTH);
        
        JPanel sep = new JPanel();
        sep.setBackground(new Color(38, 38, 38));
        sep.setPreferredSize(new Dimension(0, 1));
        panel.add(sep, BorderLayout.CENTER);

        // GRID
        JPanel gridPanel = new JPanel(new java.awt.GridLayout(0, 4, 3, 3));
        gridPanel.setBackground(new Color(18, 18, 18));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
        for (int i = 0; i < 9; i++) {
            JPanel celda = new JPanel();
            celda.setBackground(new Color(30, 30, 30));
            celda.setPreferredSize(new Dimension(300, 300));
            gridPanel.add(celda);
        }
        panel.add(gridPanel, BorderLayout.SOUTH);

        return panel;
    }
    
}
