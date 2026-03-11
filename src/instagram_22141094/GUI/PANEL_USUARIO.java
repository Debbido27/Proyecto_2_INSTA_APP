
package instagram_22141094.GUI;

import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PANEL_USUARIO extends JPanel{
   private String usuario;
   private CardLayout cardLayout;
    private JPanel contentPanel;
    private User userActual;
    Followers_Manager followersManager = new Followers_Manager();
    Login_Manager loginManager;
    
    
   public PANEL_USUARIO(String usuario, Login_Manager loginManager){
    this.usuario = usuario;
    this.loginManager = loginManager;
    this.followersManager = new Followers_Manager();
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(1200, 800));
    initFrame();
}
    
    private void initFrame() {
    
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(new Color(0, 0, 0));
    
    JPanel menuPanel = menuLatera(); 
    mainPanel.add(menuPanel, BorderLayout.WEST);
    
    cardLayout = new CardLayout();
    contentPanel = new JPanel(cardLayout);
    contentPanel.setBackground(new Color(18, 18, 18));
    
    contentPanel.add(crearFeedPanel(), "FEED");
    contentPanel.add(crearBuscarPanel(), "BUSCAR");
    contentPanel.add(crearInboxPanel(), "INBOX");
    contentPanel.add(crearPerfilPanel(), "PERFIL");
    contentPanel.add(crearSubirPanel(), "SUBIR");
    contentPanel.add(crearPerfilMainPanel(), "PERFIL_MAIN");
    contentPanel.add(new PANEL_SEGUIDORES(usuario, loginManager, followersManager, cardLayout, contentPanel), "SEGUIDORES");
    contentPanel.add(new PANEL_SIGUIENDO(usuario, loginManager, followersManager, cardLayout, contentPanel), "SIGUIENDO");
    contentPanel.add(new PANEL_SOLICITUDES(usuario, loginManager, followersManager, cardLayout, contentPanel), "SOLICITUDES");
    mainPanel.add(contentPanel, BorderLayout.CENTER);
    
    add(mainPanel);
}
            
            
    private JPanel menuLatera(){
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(0,0,0,0));
        menu.setPreferredSize(new Dimension(250,800));
        menu.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(38, 38, 38)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 5, 20);
        
        JLabel logo = new JLabel("INSTAGRAM");
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(Color.WHITE);
        gbc.insets = new Insets(30, 50, 30, 50);
        menu.add(logo, gbc);
             
        
       JButton feedBtn = crearBotonMenu("Feed");
        JButton buscarBtn = crearBotonMenu("Buscar");
        JButton inboxBtn = crearBotonMenu("Inbox");
        JButton perfilBtn = crearBotonMenu("Perfil");
        JButton subirBtn = crearBotonMenu("Subir");

       
        feedBtn.addActionListener(e -> cardLayout.show(contentPanel, "FEED"));
        buscarBtn.addActionListener(e -> cardLayout.show(contentPanel, "BUSCAR"));
        inboxBtn.addActionListener(e -> cardLayout.show(contentPanel, "INBOX"));
       perfilBtn.addActionListener(e -> {
    userActual = loginManager.buscarUser(usuario);
    contentPanel.remove(contentPanel.getComponentCount() - 1);
    contentPanel.add(crearPerfilMainPanel(), "PERFIL_MAIN");
    cardLayout.show(contentPanel, "PERFIL_MAIN");
});   
        subirBtn.addActionListener(e -> cardLayout.show(contentPanel, "SUBIR"));

        JButton cerrarSesionBtn = crearBotonMenu("Cerrar Sesion");
        cerrarSesionBtn.setForeground(new Color(255,0,0));
        
        cerrarSesionBtn.addActionListener(e -> {
            JFrame frame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            frame.dispose();
            new LOGIN().setVisible(true);
        });
        
        JButton solicitudesBtn = crearBotonMenu("Solicitudes");
        solicitudesBtn.addActionListener(e -> cardLayout.show(contentPanel, "SOLICITUDES"));
        gbc.insets = new Insets(20, 15, 20, 15);
        menu.add(feedBtn, gbc);
        menu.add(buscarBtn, gbc);
        menu.add(inboxBtn, gbc);
        menu.add(perfilBtn, gbc);
        menu.add(subirBtn, gbc);
        menu.add(solicitudesBtn, gbc);
        menu.add(cerrarSesionBtn,gbc);
      
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
        btn.setPreferredSize(new Dimension(220, 55));
        
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
        JLabel label = new JLabel(" ", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        panel.add(label);
        return panel;
    }
    
    private JPanel crearBuscarPanel(){
    return new BUSCAR_USUARIO(usuario, loginManager, followersManager, cardLayout, contentPanel);

    }
    
    private JPanel crearInboxPanel() {
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBackground(new Color(18, 18, 18));

    // ─── SIDEBAR izquierdo ───────────────────────────────────────────
    JPanel sidebar = new JPanel();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
    sidebar.setBackground(new Color(0, 0, 0));
    sidebar.setPreferredSize(new Dimension(300, 0));
    sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(38, 38, 38)));

    // Header "Mensajes"
    JLabel inboxTitle = new JLabel("  ");
    inboxTitle.setFont(new Font("Arial", Font.BOLD, 18));
    inboxTitle.setForeground(Color.WHITE);
    inboxTitle.setPreferredSize(new Dimension(300, 60));
    inboxTitle.setMaximumSize(new Dimension(300, 60));
    inboxTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(38, 38, 38)));
    sidebar.add(inboxTitle);

    // Barra de búsqueda (solo visual, no hace nada)
    JPanel searchPanel = new JPanel(new BorderLayout());
    searchPanel.setBackground(new Color(0, 0, 0));
    searchPanel.setMaximumSize(new Dimension(300, 55));
    searchPanel.setPreferredSize(new Dimension(300, 55));
    searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

    JTextField searchField = new JTextField("Buscar");
    searchField.setBackground(new Color(38, 38, 38));
    searchField.setForeground(new Color(168, 168, 168));
    searchField.setFont(new Font("Arial", Font.PLAIN, 14));
    searchField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(54, 54, 54)),
        BorderFactory.createEmptyBorder(6, 12, 6, 12)
    ));
    searchField.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent e) {
            if (searchField.getText().equals("Buscar")) {
                searchField.setText("");
                searchField.setForeground(Color.WHITE);
            }
        }
        public void focusLost(java.awt.event.FocusEvent e) {
            if (searchField.getText().isEmpty()) {
                searchField.setText("Buscar");
                searchField.setForeground(new Color(168, 168, 168));
            }
        }
    });
    searchPanel.add(searchField, BorderLayout.CENTER);
    sidebar.add(searchPanel);

    mainPanel.add(sidebar, BorderLayout.WEST);

    // ─── ÁREA CENTRAL vacía ──────────────────────────────────────────
    JPanel chatArea = new JPanel(new BorderLayout());
    chatArea.setBackground(new Color(18, 18, 18));

    // Espacio vacío de mensajes (cascarón)
    JPanel emptyChat = new JPanel(new GridBagLayout());
    emptyChat.setBackground(new Color(18, 18, 18));
    JLabel emptyLabel = new JLabel("");
    emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    emptyLabel.setForeground(new Color(80, 80, 80));
    emptyChat.add(emptyLabel);
    chatArea.add(emptyChat, BorderLayout.CENTER);

    // Input de mensaje abajo (solo visual, no hace nada)
    JPanel inputPanel = new JPanel(new BorderLayout());
    inputPanel.setBackground(new Color(18, 18, 18));
    inputPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(38, 38, 38)),
        BorderFactory.createEmptyBorder(12, 15, 12, 15)
    ));

    JTextField messageField = new JTextField("Envía un mensaje...");
    messageField.setBackground(new Color(38, 38, 38));
    messageField.setForeground(new Color(168, 168, 168));
    messageField.setFont(new Font("Arial", Font.PLAIN, 14));
    messageField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(80, 80, 80)),
        BorderFactory.createEmptyBorder(10, 15, 10, 15)
    ));
    messageField.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent e) {
            if (messageField.getText().equals("Envía un mensaje...")) {
                messageField.setText("");
                messageField.setForeground(Color.WHITE);
            }
        }
        public void focusLost(java.awt.event.FocusEvent e) {
            if (messageField.getText().isEmpty()) {
                messageField.setText("Envía un mensaje...");
                messageField.setForeground(new Color(168, 168, 168));
            }
        }
    });
    inputPanel.add(messageField, BorderLayout.CENTER);
    chatArea.add(inputPanel, BorderLayout.SOUTH);

    mainPanel.add(chatArea, BorderLayout.CENTER);

    return mainPanel;
}
    
    private JPanel crearSubirPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18,18,18));
        JLabel label = new JLabel(" ",JLabel.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,24));
        label.setForeground(Color.WHITE);
        panel.add(label);
        return panel;
    }
    
    private JPanel crearPerfilPanel(){
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(18, 18, 18));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    
    // Título
    JLabel titulo = new JLabel("EDITAR PERFIL", JLabel.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 28));
    titulo.setForeground(new Color(0, 149, 246));
    gbc.insets = new Insets(20, 10, 30, 10);
    panel.add(titulo, gbc);
    
    // Obtener datos actuales del usuario
// Usar la variable de instancia en lugar de crear una nueva
if(userActual == null) {
    userActual = loginManager.buscarUser(usuario);
}    
    // Campo Username (no editable directamente)
    gbc.insets = new Insets(5, 50, 5, 50);
    JLabel usernameLabel = new JLabel("Nombre de usuario:");
    usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    usernameLabel.setForeground(Color.WHITE);
    panel.add(usernameLabel, gbc);
    
  JTextField usernameField = new JTextField(usuario);
usernameField.setBackground(new Color(54, 54, 54));
usernameField.setForeground(Color.WHITE);
usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
usernameField.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(80, 80, 80)),
    BorderFactory.createEmptyBorder(8, 10, 8, 10)
));
usernameField.setEditable(true);
panel.add(usernameField, gbc);

// Campo Nombre completo
JLabel nombreLabel = new JLabel("Nombre completo:");
nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
nombreLabel.setForeground(Color.WHITE);
panel.add(nombreLabel, gbc);

JTextField nombreField = new JTextField(userActual != null ? userActual.getFullname() : "");
nombreField.setBackground(new Color(54, 54, 54));
nombreField.setForeground(Color.WHITE);
nombreField.setFont(new Font("Arial", Font.PLAIN, 14));
nombreField.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(80, 80, 80)),
    BorderFactory.createEmptyBorder(8, 10, 8, 10)
));
nombreField.setEditable(true);
panel.add(nombreField, gbc);

// Campo Edad
JLabel edadLabel = new JLabel("Edad:");
edadLabel.setFont(new Font("Arial", Font.BOLD, 14));
edadLabel.setForeground(Color.WHITE);
panel.add(edadLabel, gbc);

JTextField edadField = new JTextField(userActual != null ? String.valueOf(userActual.getAge()) : "");
edadField.setBackground(new Color(54, 54, 54));
edadField.setForeground(Color.WHITE);
edadField.setFont(new Font("Arial", Font.PLAIN, 14));
edadField.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(80, 80, 80)),
    BorderFactory.createEmptyBorder(8, 10, 8, 10)
));
edadField.setEditable(true);
panel.add(edadField, gbc);

// Campo Género
JLabel generoLabel = new JLabel("Género:");
generoLabel.setFont(new Font("Arial", Font.BOLD, 14));
generoLabel.setForeground(Color.WHITE);
panel.add(generoLabel, gbc);

JComboBox<Gender> generoCombo = new JComboBox<>(Gender.values());
generoCombo.setBackground(new Color(54, 54, 54));
generoCombo.setForeground(Color.WHITE);
generoCombo.setFont(new Font("Arial", Font.PLAIN, 14));
if(userActual != null) {
    generoCombo.setSelectedItem(userActual.getGender());
}
panel.add(generoCombo, gbc);

// Campo Tipo de cuenta
JLabel tipoLabel = new JLabel("Tipo de cuenta:");
tipoLabel.setFont(new Font("Arial", Font.BOLD, 14));
tipoLabel.setForeground(Color.WHITE);
panel.add(tipoLabel, gbc);

JComboBox<AccountType> tipoCombo = new JComboBox<>(AccountType.values());
tipoCombo.setBackground(new Color(54, 54, 54));
tipoCombo.setForeground(Color.WHITE);
tipoCombo.setFont(new Font("Arial", Font.PLAIN, 14));
if(userActual != null) {
    tipoCombo.setSelectedItem(userActual.getAccountType());
}
panel.add(tipoCombo, gbc);

// Foto de perfil
JLabel fotoLabel = new JLabel("Foto de perfil:");
fotoLabel.setFont(new Font("Arial", Font.BOLD, 14));
fotoLabel.setForeground(Color.WHITE);
panel.add(fotoLabel, gbc);

JPanel fotoPanel = new JPanel(new BorderLayout());
fotoPanel.setBackground(new Color(54, 54, 54));
fotoPanel.setPreferredSize(new Dimension(300, 40));
JTextField rutaFotoField = new JTextField("");
rutaFotoField.setPreferredSize(new Dimension(300, 35));
rutaFotoField.setBackground(new Color(54, 54, 54));
rutaFotoField.setForeground(Color.WHITE);
rutaFotoField.setFont(new Font("Arial", Font.PLAIN, 12));
rutaFotoField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
rutaFotoField.setEditable(true);
fotoPanel.add(rutaFotoField, BorderLayout.CENTER);
panel.add(fotoPanel, gbc);

// Campo Contraseña
JLabel passLabel = new JLabel("Nueva contraseña:");
passLabel.setFont(new Font("Arial", Font.BOLD, 14));
passLabel.setForeground(Color.WHITE);
panel.add(passLabel, gbc);

JTextField passField = new JTextField("");
passField.setBackground(new Color(54, 54, 54));
passField.setForeground(Color.WHITE);
passField.setFont(new Font("Arial", Font.PLAIN, 14));
passField.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(80, 80, 80)),
    BorderFactory.createEmptyBorder(8, 10, 8, 10)
));
passField.setEditable(true);
panel.add(passField, gbc);

JButton eliminarBtn = new JButton("ELIMINAR CUENTA");
eliminarBtn.setBackground(new Color(200, 30, 30));
eliminarBtn.setForeground(Color.WHITE);
eliminarBtn.setFont(new Font("Arial", Font.BOLD, 14));
eliminarBtn.setBorderPainted(false);
eliminarBtn.setFocusPainted(false);
eliminarBtn.setPreferredSize(new Dimension(250, 45));

eliminarBtn.addActionListener(e -> {
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "¿Seguro que quieres eliminar tu cuenta? ",
        "Eliminar cuenta",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    if (confirm == JOptionPane.YES_OPTION) {
        loginManager.eliminarUsuario(usuario);
        JFrame frame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        new LOGIN().setVisible(true);
    }
});

gbc.insets = new Insets(20, 50, 5, 50);
panel.add(eliminarBtn, gbc);
// Botón Guardar cambios
JButton guardarBtn = new JButton("GUARDAR CAMBIOS");
guardarBtn.setBackground(new Color(0, 149, 246));
guardarBtn.setForeground(Color.WHITE);
guardarBtn.setFont(new Font("Arial", Font.BOLD, 16));
guardarBtn.setBorderPainted(false);
guardarBtn.setFocusPainted(false);
guardarBtn.setPreferredSize(new Dimension(250, 45));

guardarBtn.addActionListener(e -> {
    try {
        String nuevoUsername = usernameField.getText().trim();
        String nuevoNombre = nombreField.getText().trim();
        int nuevaEdad = Integer.parseInt(edadField.getText().trim());
        Gender nuevoGenero = (Gender) generoCombo.getSelectedItem();
        AccountType nuevoTipo = (AccountType) tipoCombo.getSelectedItem();

        if(!nuevoUsername.isEmpty() && !nuevoUsername.equals(usuario)) {
            if(!loginManager.cambiarUsername(usuario, nuevoUsername)) {
                JOptionPane.showMessageDialog(this, "Error: El username ya existe");
                return;
            }
            usuario = nuevoUsername;
        }

        if(!nuevoNombre.equals(userActual.getFullname()))
            loginManager.cambiarNombre(usuario, nuevoNombre);

        if(nuevaEdad != userActual.getAge())
            loginManager.cambiarEdad(usuario, nuevaEdad);

        if(nuevoGenero != userActual.getGender())
            loginManager.cambiarGenero(usuario, nuevoGenero);

        if(nuevoTipo != userActual.getAccountType())
            loginManager.cambiarTipoCuenta(usuario, nuevoTipo);

        String nuevaPass = passField.getText().trim();
        if(!nuevaPass.isEmpty()) {
            loginManager.cambiarPassword(usuario, nuevaPass);
        }

        String nuevaRuta = rutaFotoField.getText().trim();
        if(!nuevaRuta.isEmpty()) {
            File archivo = new File(nuevaRuta);
            if(archivo.exists()) {
                loginManager.setFotoPerfil(usuario, nuevaRuta);
            } else {
                JOptionPane.showMessageDialog(this, "Ruta de foto no válida, se mantendrá la actual");
            }
        }

        userActual = loginManager.buscarUser(usuario);
        contentPanel.remove(contentPanel.getComponentCount() - 1);
        contentPanel.add(crearPerfilMainPanel(), "PERFIL_MAIN");

    } catch(NumberFormatException ex){
        JOptionPane.showMessageDialog(this, "Edad debe ser un número válido");
    } catch(IOException ex){
        Logger.getLogger(PANEL_USUARIO.class.getName()).log(Level.SEVERE, null, ex);
    }
});

gbc.insets = new Insets(30, 50, 20, 50);
panel.add(guardarBtn, gbc);

return panel;
}

    
  private JPanel crearPerfilMainPanel(){
    if(userActual == null) userActual = loginManager.buscarUser(usuario);
    PANEL_PERFIL_VISTA vista = new PANEL_PERFIL_VISTA(
        userActual, usuario, loginManager, followersManager, cardLayout, contentPanel
    );
    return vista;
}  
    
    
    
private JLabel crearStat(String numero, String texto){
    JLabel stat = new JLabel("<html><b>" + numero + "</b><br><center>" + texto + "</center></html>", JLabel.CENTER);
    stat.setFont(new Font("Arial", Font.PLAIN, 14));
    stat.setForeground(Color.WHITE);
    return stat;
}
    
}
