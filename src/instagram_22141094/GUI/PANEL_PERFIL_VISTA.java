
package instagram_22141094.GUI;

import Instagram_login_user.Followers_Manager;
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
    Followers_Manager followersManager;
    public PANEL_PERFIL_VISTA(User userVisto, String usuarioLogueado, Login_Manager loginManager, 
                           Followers_Manager followersManager,
                           CardLayout cardlayout, JPanel contentPanel){
    this.userVisto = userVisto;
    this.usuarioLogueado = usuarioLogueado;
    this.loginManager = loginManager;
    this.followersManager = followersManager;
    this.cardLayout = cardlayout;
    this.contentPanel = contentPanel;
    setLayout(new BorderLayout());
    setBackground(new Color(18, 18, 18));
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
     int SIZE = 100;
String profilePath = userVisto.getProfilePath();

// Cargar imagen ANTES del panel anónimo
Image imagenFinal;
boolean imagenCargada = false;

if (profilePath != null && !profilePath.isEmpty()
        && !profilePath.equals("USUARIO_DEFAULT.png")
        && !profilePath.equals("image.jpg")
        && new File(profilePath).exists()) {
    try {
        ImageIcon icon = new ImageIcon(profilePath);
        imagenFinal = icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
        imagenCargada = true;
    } catch (Exception ex) {
        imagenFinal = null;
    }
} else {
    imagenFinal = null;
}

// Si no cargó foto personalizada, intentar default
if (!imagenCargada) {
    try {
        ImageIcon icon = new ImageIcon(getClass().getResource("USUARIO_DEFAULT.png"));
        imagenFinal = icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
        imagenCargada = true;
    } catch (Exception ex) {
        imagenFinal = null;
    }
}

final Image imgFinal = imagenFinal;
final boolean imgCargada = imagenCargada;

JPanel fotoCircular = new JPanel() {
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        java.awt.geom.Ellipse2D circle = new java.awt.geom.Ellipse2D.Float(0, 0, SIZE, SIZE);
        g2.setClip(circle);

        if (imgCargada && imgFinal != null) {
            g2.drawImage(imgFinal, 0, 0, this);
        } else {
            g2.setColor(new Color(60, 60, 60));
            g2.fillOval(0, 0, SIZE, SIZE);
            g2.setClip(null);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            java.awt.FontMetrics fm = g2.getFontMetrics();
            String letra = userVisto.getUsername().substring(0, 1).toUpperCase();
            int x = (SIZE - fm.stringWidth(letra)) / 2;
            int y = (SIZE - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(letra, x, y);
        }

        g2.setClip(null);
        g2.setColor(new Color(80, 80, 80));
        g2.setStroke(new java.awt.BasicStroke(2));
        g2.drawOval(1, 1, SIZE - 2, SIZE - 2);
        g2.dispose();
    }
};

fotoCircular.setPreferredSize(new Dimension(SIZE, SIZE));
fotoCircular.setOpaque(false);

gbc.gridx = 0; gbc.gridy = 0;
gbc.gridheight = 3;
infoPanel.add(fotoCircular, gbc);

        
        
        
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
    boolean yaSigue = followersManager.estaSiguiendo(usuarioLogueado, userVisto.getUsername());
    boolean pendiente = followersManager.tieneSolicitudPendiente(usuarioLogueado, userVisto.getUsername());

    String textoBtn = yaSigue ? "Siguiendo" : (pendiente ? "Solicitado" : "Seguir");
    Color colorBtn = yaSigue ? new Color(54, 54, 54) : (pendiente ? new Color(100, 100, 100) : new Color(0, 149, 246));

    JButton seguirBtn = new JButton(textoBtn);
    seguirBtn.setBackground(colorBtn);
    seguirBtn.setForeground(Color.WHITE);
    seguirBtn.setFont(new Font("Arial", Font.BOLD, 13));
    seguirBtn.setBorderPainted(false);
    seguirBtn.setFocusPainted(false);

    seguirBtn.addActionListener(e -> {
        if (yaSigue) {
            followersManager.dejarDeSeguir(usuarioLogueado, userVisto.getUsername());
        } else if (!pendiente) {
            followersManager.seguir(usuarioLogueado, userVisto.getUsername(), loginManager);
        }
        // Refrescar panel
        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            if ("PERFIL_BUSCADO".equals(contentPanel.getComponent(i).getName())) {
                contentPanel.remove(i);
                break;
            }
        }
        PANEL_PERFIL_VISTA nuevo = new PANEL_PERFIL_VISTA(
            userVisto, usuarioLogueado, loginManager, followersManager, cardLayout, contentPanel
        );
        nuevo.setName("PERFIL_BUSCADO");
        contentPanel.add(nuevo, "PERFIL_BUSCADO");
        cardLayout.show(contentPanel, "PERFIL_BUSCADO");
    });

    usernamePanel.add(seguirBtn);
}
        infoPanel.add(usernamePanel, gbc);

       gbc.gridx = 1; gbc.gridy = 1;
        JPanel statsPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 0));
        statsPanel.setBackground(new Color(18, 18, 18));

        int numFollowers = followersManager.contarFollowers(userVisto.getUsername());
        int numFollowing = followersManager.contarFollowing(userVisto.getUsername());

        JLabel statPublicaciones = crearStat("0", "publicaciones");
        JLabel statSeguidores = crearStat(String.valueOf(numFollowers), "seguidores");
        JLabel statSiguiendo = crearStat(String.valueOf(numFollowing), "siguiendo");

        if (userVisto.getUsername().equals(usuarioLogueado)) {
           // REEMPLAZAR POR:
statSeguidores.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent e) {
        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            if (contentPanel.getComponent(i) instanceof PANEL_SEGUIDORES) {
                ((PANEL_SEGUIDORES) contentPanel.getComponent(i)).construir();
                break;
            }
        }
        cardLayout.show(contentPanel, "SEGUIDORES");
    }
});

statSiguiendo.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent e) {
        for (int i = 0; i < contentPanel.getComponentCount(); i++) {
            if (contentPanel.getComponent(i) instanceof PANEL_SIGUIENDO) {
                ((PANEL_SIGUIENDO) contentPanel.getComponent(i)).construir();
                break;
            }
        }
        cardLayout.show(contentPanel, "SIGUIENDO");
    }
});
}

statsPanel.add(statPublicaciones);
statsPanel.add(statSeguidores);
statsPanel.add(statSiguiendo);
infoPanel.add(statsPanel, gbc);
        // NOMBRE COMPLETO
        
        
        
     gbc.gridx = 1; gbc.gridy = 2;

boolean esCuentaPrivada = userVisto.getAccountType().toString().equals("PRIVATE");
boolean loSigue = followersManager.estaSiguiendo(usuarioLogueado, userVisto.getUsername());
boolean esMiPerfil = userVisto.getUsername().equals(usuarioLogueado);
boolean puedeVerContenido = esMiPerfil || !esCuentaPrivada || loSigue;

if (puedeVerContenido) {
    JPanel datosPanel = new JPanel(new GridBagLayout());
    datosPanel.setBackground(new Color(18, 18, 18));

    GridBagConstraints dgbc = new GridBagConstraints();
    dgbc.anchor = GridBagConstraints.WEST;
    dgbc.gridwidth = GridBagConstraints.REMAINDER;
    dgbc.insets = new Insets(2, 0, 2, 0);

    JLabel nombreLabel = new JLabel(userVisto.getFullname());
    nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
    nombreLabel.setForeground(Color.WHITE);
    datosPanel.add(nombreLabel, dgbc);

    JLabel generoLabel = new JLabel("Género: " + userVisto.getGender());
    generoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    generoLabel.setForeground(new Color(180, 180, 180));
    datosPanel.add(generoLabel, dgbc);

    JLabel edadLabel = new JLabel("Edad: " + userVisto.getAge());
    edadLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    edadLabel.setForeground(new Color(180, 180, 180));
    datosPanel.add(edadLabel, dgbc);

    JLabel fechaLabel = new JLabel("Miembro desde: " + userVisto.getRegisterDate());
    fechaLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    fechaLabel.setForeground(new Color(180, 180, 180));
    datosPanel.add(fechaLabel, dgbc);

    JLabel tipoLabel = new JLabel("Cuenta: " + userVisto.getAccountType());
    tipoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    tipoLabel.setForeground(new Color(180, 180, 180));
    datosPanel.add(tipoLabel, dgbc);

    JLabel estadoLabel = new JLabel("Estado: " + userVisto.getStatus());
    estadoLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    Color colorEstado = userVisto.getStatus().toString().equals("ACTIVE") ? 
        new Color(0, 200, 100) : new Color(200, 50, 50);
    estadoLabel.setForeground(colorEstado);
    datosPanel.add(estadoLabel, dgbc);

    infoPanel.add(datosPanel, gbc);
} else {
    JLabel privadoLabel = new JLabel("Esta cuenta es privada");
    privadoLabel.setFont(new Font("Arial", Font.BOLD, 14));
    privadoLabel.setForeground(new Color(160, 160, 160));
    infoPanel.add(privadoLabel, gbc);
}

panel.add(infoPanel, BorderLayout.NORTH);

JPanel sep = new JPanel();
sep.setBackground(new Color(38, 38, 38));
sep.setPreferredSize(new Dimension(0, 1));
panel.add(sep, BorderLayout.CENTER);

if (puedeVerContenido) {
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
} else {
    JPanel bloqueado = new JPanel(new GridBagLayout());
    bloqueado.setBackground(new Color(18, 18, 18));
    JLabel lockLabel = new JLabel("Sigue esta cuenta para ver sus publicaciones");
    lockLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    lockLabel.setForeground(new Color(160, 160, 160));
    bloqueado.add(lockLabel);
    panel.add(bloqueado, BorderLayout.SOUTH);
}

return panel;
    }
    
      private JLabel crearStat(String numero, String texto) {
        JLabel stat = new JLabel("<html><b>" + numero + "</b><br><center>" + texto + "</center></html>", JLabel.CENTER);
        stat.setFont(new Font("Arial", Font.PLAIN, 14));
        stat.setForeground(Color.WHITE);
        return stat;
    }
    
}
