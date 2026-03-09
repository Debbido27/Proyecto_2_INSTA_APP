
package instagram_22141094.GUI;

import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Base_cuenta.Gender;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
    Login_Manager loginManager;
    public PANEL_USUARIO(String usuario,Login_Manager loginManager){
      this.usuario=usuario;
      this.usuario=usuario;
      this.loginManager=loginManager;
        setLayout(new BorderLayout());
      setPreferredSize(new Dimension(1200,800));
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
        perfilBtn.addActionListener(e -> cardLayout.show(contentPanel, "PERFIL"));
        subirBtn.addActionListener(e -> cardLayout.show(contentPanel, "SUBIR"));

        gbc.insets = new Insets(20, 15, 20, 15);
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
    usernameField.setEditable(false); // No editable directamente
    panel.add(usernameField, gbc);
    
    // Botón para cambiar username
    JButton cambiarUsernameBtn = new JButton("Cambiar username");
    cambiarUsernameBtn.setBackground(new Color(0, 149, 246));
    cambiarUsernameBtn.setForeground(Color.WHITE);
    cambiarUsernameBtn.setFont(new Font("Arial", Font.BOLD, 12));
    cambiarUsernameBtn.setBorderPainted(false);
    cambiarUsernameBtn.setFocusPainted(false);
    cambiarUsernameBtn.addActionListener(e -> {
        String nuevoUsername = JOptionPane.showInputDialog(this, "Nuevo nombre de usuario:");
        if(nuevoUsername != null && !nuevoUsername.trim().isEmpty()) {
            try {
                if(loginManager.cambiarUsername(usuario, nuevoUsername)) {
                    JOptionPane.showMessageDialog(this, "Username cambiado exitosamente");
                    usuario = nuevoUsername; // Actualizar variable local
                    usernameField.setText(nuevoUsername);
                } else {
                    JOptionPane.showMessageDialog(this, "Error: El username ya existe");
                }
            } catch (IOException ex) {
                Logger.getLogger(PANEL_USUARIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    panel.add(cambiarUsernameBtn, gbc);
    
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
    panel.add(nombreField, gbc);
    nombreField.setEditable(true);
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
    
    JLabel rutaFotoLabel = new JLabel(userActual != null && !userActual.getProfilePath().isEmpty() ? 
                                      userActual.getProfilePath() : "Sin foto");
    rutaFotoLabel.setForeground(Color.LIGHT_GRAY);
    rutaFotoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
    fotoPanel.add(rutaFotoLabel, BorderLayout.CENTER);
    
    JButton seleccionarFotoBtn = new JButton("Seleccionar");
    seleccionarFotoBtn.setBackground(new Color(0, 149, 246));
    seleccionarFotoBtn.setForeground(Color.WHITE);
    seleccionarFotoBtn.setFont(new Font("Arial", Font.BOLD, 12));
    seleccionarFotoBtn.setBorderPainted(false);
    seleccionarFotoBtn.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Seleccionar foto de perfil");
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            rutaFotoLabel.setText(ruta);
        }
    });
    fotoPanel.add(seleccionarFotoBtn, BorderLayout.EAST);
    
    panel.add(fotoPanel, gbc);
    
    // Cambiar contraseña
    JLabel passLabel = new JLabel("Cambiar contraseña:");
    passLabel.setFont(new Font("Arial", Font.BOLD, 14));
    passLabel.setForeground(Color.WHITE);
    panel.add(passLabel, gbc);
    
    JButton cambiarPassBtn = new JButton("Cambiar contraseña");
    cambiarPassBtn.setBackground(new Color(0, 149, 246));
    cambiarPassBtn.setForeground(Color.WHITE);
    cambiarPassBtn.setFont(new Font("Arial", Font.BOLD, 14));
    cambiarPassBtn.setBorderPainted(false);
    
    
    cambiarPassBtn.addActionListener(e -> {
    String newPass = JOptionPane.showInputDialog(this, "Nueva contraseña:");
    if(newPass != null && !newPass.trim().isEmpty()) {
        try {
            if(loginManager.cambiarPassword(usuario, newPass)) {
                
                
                // Actualizar la referencia del usuario
                userActual = loginManager.buscarUser(usuario);
                
                // Opcional: Mostrar confirmación adicional
                System.out.println("Contraseña actualizada para: " + usuario);
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar contraseña");
            }
        } catch (IOException ex) {
            Logger.getLogger(PANEL_USUARIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
    panel.add(cambiarPassBtn, gbc);
    
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
        String nuevoNombre = nombreField.getText().trim();
        int nuevaEdad = Integer.parseInt(edadField.getText().trim());
        Gender nuevoGenero = (Gender) generoCombo.getSelectedItem();
        AccountType nuevoTipo = (AccountType) tipoCombo.getSelectedItem();

        if(!nuevoNombre.equals(userActual.getFullname()))
            loginManager.cambiarNombre(usuario, nuevoNombre);

        if(nuevaEdad != userActual.getAge())
            loginManager.cambiarEdad(usuario, nuevaEdad);

        if(nuevoGenero != userActual.getGender())
            loginManager.cambiarGenero(usuario, nuevoGenero);

        if(nuevoTipo != userActual.getAccountType())
            loginManager.cambiarTipoCuenta(usuario, nuevoTipo);

        // Recargar userActual con los datos frescos
        userActual = loginManager.buscarUser(usuario);

        JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente");

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18,18,18));
        
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(18,18,18));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(40,60,20,60));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(5,20,5,20);
        gbc.anchor=GridBagConstraints.WEST;
        
    if(userActual == null) userActual = loginManager.buscarUser(usuario);
     
    JLabel fotoCirculo = new JLabel();
    fotoCirculo.setPreferredSize(new Dimension(100,100));
    fotoCirculo.setBackground(new Color(60,60,60));
    fotoCirculo.setOpaque(true);
    fotoCirculo.setFont(new Font("Arial", Font.BOLD, 36));
    fotoCirculo.setForeground(Color.WHITE);
    
    fotoCirculo.setText(usuario.substring(0,1).toUpperCase());
    gbc.gridx=0;gbc.gridy=0;
    gbc.gridheight=3;
    infoPanel.add(fotoCirculo,gbc);
    
    gbc.gridx=1; gbc.gridy=0;
    gbc.gridheight=1;
    JPanel usernamePanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10,10));
    usernamePanel.setBackground(new Color(18,18,18));
    
     JLabel usernameLabel = new JLabel(usuario);
    usernameLabel.setFont(new Font("Arial", Font.BOLD, 22));
    usernameLabel.setForeground(Color.WHITE);
    usernamePanel.add(usernameLabel);
    
    JButton editarBtn = new JButton("Editar perfil");
    editarBtn.setBackground(new Color(54, 54, 54));
    editarBtn.setForeground(Color.WHITE);
    editarBtn.setFont(new Font("Arial", Font.BOLD, 13));
    editarBtn.setBorderPainted(false);
    editarBtn.setFocusPainted(false);
    editarBtn.addActionListener(e -> cardLayout.show(contentPanel, "PERFIL"));
    usernamePanel.add(editarBtn);
    infoPanel.add(usernamePanel, gbc);
    
    gbc.gridx = 1; gbc.gridy = 1;
    JPanel statsPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 0));
    statsPanel.setBackground(new Color(18, 18, 18));
    statsPanel.add(crearStat("0", "publicaciones"));
    statsPanel.add(crearStat("0", "seguidores"));
    statsPanel.add(crearStat("0", "siguiendo"));
    infoPanel.add(statsPanel, gbc);
    
    gbc.gridx = 1; gbc.gridy = 2;
    JLabel nombreLabel = new JLabel(userActual != null ? userActual.getFullname() : "");
    nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
    nombreLabel.setForeground(Color.WHITE);
    infoPanel.add(nombreLabel, gbc);
    
    panel.add(infoPanel, BorderLayout.NORTH);
     
    
    
    JPanel sep = new JPanel();
    sep.setBackground(new Color(38, 38, 38));
    sep.setPreferredSize(new Dimension(0, 1));
    panel.add(sep, BorderLayout.CENTER);
    
    
    JPanel gridPanel = new JPanel(new java.awt.GridLayout(0, 3, 3, 3));
    gridPanel.setBackground(new Color(18, 18, 18));
    gridPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
    
    
    for(int i = 0; i < 9; i++){
        JPanel celda = new JPanel();
        celda.setBackground(new Color(30, 30, 30));
        celda.setPreferredSize(new Dimension(200, 200));
        gridPanel.add(celda);
    }
    
     panel.add(gridPanel, BorderLayout.SOUTH);
    
    return panel;
    }
    
    
    
}
