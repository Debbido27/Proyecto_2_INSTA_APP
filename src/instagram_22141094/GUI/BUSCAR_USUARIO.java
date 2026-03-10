
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


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
         GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel titulo = new JLabel("Buscar usuarios");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 15, 0);
        buscarPanel.add(titulo, gbc);

        JTextField campoBuscar = new JTextField();
        campoBuscar.setBackground(new Color(54, 54, 54));
        campoBuscar.setForeground(Color.WHITE);
        campoBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        campoBuscar.setPreferredSize(new Dimension(400, 40));
        campoBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        buscarPanel.add(campoBuscar, gbc);

        add(buscarPanel, BorderLayout.NORTH);
         
         resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new GridBagLayout());
        resultadosPanel.setBackground(new Color(18, 18, 18));

        JScrollPane scroll = new JScrollPane(resultadosPanel);
        scroll.setBackground(new Color(18, 18, 18));
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(18, 18, 18));
        add(scroll, BorderLayout.CENTER);

        // Buscar mientras escribe
        campoBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = campoBuscar.getText().trim();
                if (!texto.isEmpty()) {
                    buscarYMostrar(texto);
                } else {
                    resultadosPanel.removeAll();
                    resultadosPanel.revalidate();
                    resultadosPanel.repaint();
                }
            }
        });
    }

        
        private void buscarYMostrar(String texto){
            resultadosPanel.removeAll();
            
            User[] resultados = loginManager.buscarUsuariosCoincidentes(texto);
             GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(5, 60, 5, 60);

            for (int i = 0; i < resultados.length; i++) {
                User u = resultados[i];
                JPanel card = crearCard(u);
                gbc.gridy = i;
                resultadosPanel.add(card, gbc);
                
                 GridBagConstraints gbcFill = new GridBagConstraints();
                gbcFill.gridwidth = GridBagConstraints.REMAINDER;
                gbcFill.weighty = 1.0;
                gbcFill.fill = GridBagConstraints.VERTICAL;
                gbcFill.gridy = resultados.length;
                resultadosPanel.add(new JPanel() {{ setBackground(new Color(18, 18, 18)); }}, gbcFill);

                resultadosPanel.revalidate();
                resultadosPanel.repaint();
            }
            
        }
            
            
            private JPanel crearCard(User u){
                
                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(new Color(30,30,30));
                card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(54, 54, 54)),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            
             card.setPreferredSize(new Dimension(0,70));
             card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
             
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

        // Info
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
            
              card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                abrirPerfil(u);
            }
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(45, 45, 45));
                infoPanel.setBackground(new Color(45, 45, 45));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBackground(new Color(30, 30, 30));
                infoPanel.setBackground(new Color(30, 30, 30));
            }
        });

        return card;
        
            }
            
            
       
      private void abrirPerfil(User u){
          
          
          
      }
         
            
            
        }
        
        
        
        
        
        
        
        
        
        
    

