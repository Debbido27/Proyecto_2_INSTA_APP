
package instagram_22141094.GUI;
import javax.swing.JOptionPane;
import Instagram_login_user.Login_Manager;
import Instagram_login_user.Base_cuenta.Gender;
import Instagram_login_user.Base_cuenta.AccountType;
import Instagram_login_user.Login_Manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LOGIN  extends JFrame{
    Login_Manager loginManager;
    public LOGIN(){
     loginManager = new Login_Manager();
     INICIAR();   
     
    }
    
    private void INICIAR(){
    setTitle("INSTAGRAM");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200,1200);
    setLocationRelativeTo(null);
    
    CardLayout cd = new CardLayout();
    JPanel iniciar = new JPanel(cd);
    
    JPanel inicio = INICIO(); // Panel de login
    JPanel registro = INICIO_REGISTRO(); // Nuevo método
    
    iniciar.add(inicio, "LOGIN");
    iniciar.add(registro, "REGISTRO");
    
    add(iniciar);
    cd.show(iniciar, "LOGIN");
}
    
    private JPanel INICIO(){
        JPanel inicio = new JPanel (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets (0,0,0,0);
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=0.7;
        gbc.weighty=0.3;
        inicio.add(Fondo(),gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.3; // 
        gbc.weighty=0.0;
        inicio.add(DATOS(), gbc);
        return inicio;
        
    }
    
    private JPanel Fondo(){
        JPanel Fondo = new JPanel(new BorderLayout());
        
        JLabel TITULO = new JLabel("INSTAGRAM",JLabel.CENTER);
        TITULO.setFont(new Font("Arial",Font.PLAIN,44));
        TITULO.setForeground(new Color(0,0,0));
        
        JPanel BACKGROUNDTITULO = new JPanel();
        BACKGROUNDTITULO.setBackground(new Color(12,15,20));
        BACKGROUNDTITULO.setOpaque(true);
        BACKGROUNDTITULO.setPreferredSize(new Dimension(0,80));
        BACKGROUNDTITULO.add(TITULO);
        Fondo.add(BACKGROUNDTITULO,BorderLayout.NORTH);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("image.jpg"));
        Image img = icon.getImage();
        
         JPanel panelImagen = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(12, 15, 20)); 
            g.fillRect(0, 0, getWidth(), getHeight());

            int x = (getWidth() - img.getWidth(null)) / 2; 
            g.drawImage(img, x, 0, this);
        }
    };
         
        panelImagen.setOpaque(true);
        Fondo.add(panelImagen,BorderLayout.CENTER);
        
        return Fondo;
        
        
    }
    
    private JPanel DATOS(){
    JPanel informacion = new JPanel();
    informacion.setBackground(new Color(21,33,39));
    informacion.setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10,20,10,20);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    JPanel formulaPanel = new JPanel(new GridBagLayout());
    formulaPanel.setBackground(new Color(21,33,39));
    formulaPanel.setBorder(null);
    formulaPanel.setPreferredSize(new Dimension(200,800));
    
    GridBagConstraints fgbc = new GridBagConstraints();
    fgbc.insets = new Insets(10,20,10,20);
    fgbc.fill = GridBagConstraints.HORIZONTAL;
    fgbc.gridwidth = GridBagConstraints.REMAINDER;
    
    //CAMPO DE USUARIO
    JTextField campoUsuario = new JTextField(15);
    campoUsuario.setBackground(new Color(109,123,133));
    campoUsuario.setPreferredSize(new Dimension(250,40));
    campoUsuario.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    campoUsuario.setFont(new Font("Arial",Font.PLAIN,14));
    
    JLabel USUARIO = new JLabel("Telefono, usuario, o email");
    USUARIO.setFont(new Font("Arial",Font.PLAIN,14));
    USUARIO.setForeground(new Color(142,142,142));
    
    JTextField CONTRA = new JTextField(15);
    CONTRA.setPreferredSize(new Dimension(250,40));
    CONTRA.setBackground(new Color(109,123,133));
    CONTRA.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(109,123,133)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    CONTRA.setFont(new Font("Arial",Font.PLAIN,14));
    
    JLabel PASS = new JLabel("Ingrese la contrasena: ");
    PASS.setFont(new Font("Arial",Font.PLAIN,14));
    PASS.setForeground(new Color(142,142,142));
    
    JButton LOGIN = new JButton("Iniciar sesion");
    LOGIN.setBackground(new Color(0,149,246));
    LOGIN.setForeground(Color.WHITE);
    LOGIN.setFont(new Font("Arial",Font.BOLD,14));
    LOGIN.setBorderPainted(false);
    LOGIN.setFocusPainted(false);
    LOGIN.setPreferredSize(new Dimension(250,40));
    
    JButton CREAR = new JButton("Crear nueva cuenta");
    CREAR.setBackground(new Color(46,125,50));
    CREAR.setForeground(Color.WHITE);
    CREAR.setFont(new Font("Arial",Font.BOLD,14));
    CREAR.setBorderPainted(false);
    CREAR.setFocusPainted(false);
    CREAR.setPreferredSize(new Dimension(250,40));
    
    CREAR.addActionListener(e -> {
    CardLayout cl = (CardLayout)((JPanel)getContentPane().getComponent(0)).getLayout();
    cl.show((JPanel)getContentPane().getComponent(0), "REGISTRO");
});
    // Agregar componentes
    fgbc.gridy = 0;
    formulaPanel.add(USUARIO, fgbc);
    fgbc.gridy = 1;
    formulaPanel.add(campoUsuario, fgbc);
    fgbc.gridy = 2;
    formulaPanel.add(PASS, fgbc);
    fgbc.gridy = 3;
    formulaPanel.add(CONTRA, fgbc);
    fgbc.gridy = 4;
    fgbc.insets = new Insets(20,20,20,20);
    formulaPanel.add(LOGIN, fgbc);
    fgbc.gridy = 5;
    fgbc.insets = new Insets(5,20,20,20);
    formulaPanel.add(CREAR, fgbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.NORTH;
    informacion.add(formulaPanel, gbc);
    
    
    LOGIN.addActionListener(e -> {
    String usuario = campoUsuario.getText().trim(); // <-- AGREGAR .trim()
    String contrasena = CONTRA.getText().trim(); // <-- AGREGAR .trim()
    
    // Validar campos vacíos
    if(usuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de usuario está vacío");
        campoUsuario.requestFocus();
        return;
    }
    
    if(contrasena.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo de contraseña está vacío");
        CONTRA.requestFocus();
        return;
    }
    
    try {
        if(loginManager.login(usuario, contrasena)) {
            // Login exitoso - mostrar panel en blanco
           
               getContentPane().removeAll();
                PANEL_USUARIO pn = new PANEL_USUARIO(usuario);
                setContentPane(pn);
                setSize(1200,800);
                revalidate();
                repaint();                

        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    } catch(NullPointerException ex) {
        JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos. Asegúrate de crear la carpeta INSTA_RAIZ");
    }
});
    
    
    return informacion;
}
    
private JPanel REGISTRO(){
    JPanel registro = new JPanel();
    registro.setBackground(new Color(21,33,39));
    registro.setLayout(new GridBagLayout());
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10,20,10,20);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
    JPanel formulaRegistro = new JPanel(new GridBagLayout());
    formulaRegistro.setBackground(new Color(21,33,39));
    formulaRegistro.setBorder(null);
    formulaRegistro.setPreferredSize(new Dimension(200,800));
    
    GridBagConstraints fgbc = new GridBagConstraints();
    fgbc.insets = new Insets(8,20,8,20);
    fgbc.fill = GridBagConstraints.HORIZONTAL;
    fgbc.gridwidth = GridBagConstraints.REMAINDER;
    
    JLabel usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Arial",Font.PLAIN,14));
    usernameLabel.setForeground(new Color(142,142,142));
    
    JTextField usernameField = new JTextField(15);
    usernameField.setBackground(new Color(109,123,133));
    usernameField.setPreferredSize(new Dimension(250,35));
    usernameField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel passLabel = new JLabel("Contraseña:");
    passLabel.setFont(new Font("Arial",Font.PLAIN,14));
    passLabel.setForeground(new Color(142,142,142));
    
    JTextField passField = new JTextField(15);
    passField.setBackground(new Color(109,123,133));
    passField.setPreferredSize(new Dimension(250,35));
    passField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel nombreLabel = new JLabel("Nombre completo:");
    nombreLabel.setFont(new Font("Arial",Font.PLAIN,14));
    nombreLabel.setForeground(new Color(142,142,142));
    
    JTextField nombreField = new JTextField(15);
    nombreField.setBackground(new Color(109,123,133));
    nombreField.setPreferredSize(new Dimension(250,35));
    nombreField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel edadLabel = new JLabel("Edad:");
    edadLabel.setFont(new Font("Arial",Font.PLAIN,14));
    edadLabel.setForeground(new Color(142,142,142));
    
    JTextField edadField = new JTextField(15);
    edadField.setBackground(new Color(109,123,133));
    edadField.setPreferredSize(new Dimension(250,35));
    edadField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel generoLabel = new JLabel("Género:");
    generoLabel.setFont(new Font("Arial",Font.PLAIN,14));
    generoLabel.setForeground(new Color(142,142,142));
    
    JTextField generoField = new JTextField(15);
    generoField.setBackground(new Color(109,123,133));
    generoField.setPreferredSize(new Dimension(250,35));
    generoField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel tipoLabel = new JLabel("Tipo de cuenta:");
    tipoLabel.setFont(new Font("Arial",Font.PLAIN,14));
    tipoLabel.setForeground(new Color(142,142,142));
    
    JTextField tipoField = new JTextField(15);
    tipoField.setBackground(new Color(109,123,133));
    tipoField.setPreferredSize(new Dimension(250,35));
    tipoField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JLabel rutaLabel = new JLabel("Ruta foto de perfil:");
    rutaLabel.setFont(new Font("Arial",Font.PLAIN,14));
    rutaLabel.setForeground(new Color(142,142,142));
    
    JTextField rutaField = new JTextField(15);
    rutaField.setBackground(new Color(109,123,133));
    rutaField.setPreferredSize(new Dimension(250,35));
    rutaField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0,0,0)),
        BorderFactory.createEmptyBorder(5,10,5,10)
    ));
    
    JButton crearBtn = new JButton("Crear cuenta");
    crearBtn.setBackground(new Color(0,149,246));
    crearBtn.setForeground(Color.WHITE);
    crearBtn.setFont(new Font("Arial",Font.BOLD,14));
    crearBtn.setBorderPainted(false);
    crearBtn.setFocusPainted(false);
    crearBtn.setPreferredSize(new Dimension(250,40));
    
    JButton volverBtn = new JButton("Volver al login");
    volverBtn.setBackground(new Color(100,100,100));
    volverBtn.setForeground(Color.WHITE);
    volverBtn.setFont(new Font("Arial",Font.BOLD,12));
    volverBtn.setBorderPainted(false);
    volverBtn.setFocusPainted(false);
    volverBtn.setPreferredSize(new Dimension(250,30));
    volverBtn.addActionListener(e -> {
    CardLayout cl = (CardLayout)((JPanel)getContentPane().getComponent(0)).getLayout();
    cl.show((JPanel)getContentPane().getComponent(0), "LOGIN");
});
    int row = 0;
    fgbc.gridy = row++;
    formulaRegistro.add(usernameLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(usernameField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(passLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(passField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(nombreLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(nombreField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(edadLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(edadField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(generoLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(generoField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(tipoLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(tipoField, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(rutaLabel, fgbc);
    fgbc.gridy = row++;
    formulaRegistro.add(rutaField, fgbc);
    fgbc.gridy = row++;
    fgbc.insets = new Insets(20,20,10,20);
    formulaRegistro.add(crearBtn, fgbc);
    fgbc.gridy = row++;
    fgbc.insets = new Insets(5,20,20,20);
    formulaRegistro.add(volverBtn, fgbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.NORTH;
    registro.add(formulaRegistro, gbc);
    
    
    crearBtn.addActionListener(e -> {
    // Obtener textos y eliminar espacios al inicio y final con .trim()
    String username = usernameField.getText().trim();
    String password = passField.getText().trim();
    String fullname = nombreField.getText().trim();
    String edadTexto = edadField.getText().trim();
    String generoTexto = generoField.getText().trim();
    String tipoTexto = tipoField.getText().trim();
    String rutaFoto = rutaField.getText().trim();
    
    // Validar que ningún campo obligatorio esté vacío
    if(username.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Username está vacío");
        usernameField.requestFocus();
        return;
    }
    
    if(password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Contraseña está vacío");
        passField.requestFocus();
        return;
    }
    
    if(fullname.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Nombre completo está vacío");
        nombreField.requestFocus();
        return;
    }
    
    if(edadTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Edad está vacío");
        edadField.requestFocus();
        return;
    }
    
    if(generoTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Género está vacío");
        generoField.requestFocus();
        return;
    }
    
    if(tipoTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El campo Tipo de cuenta está vacío");
        tipoField.requestFocus();
        return;
    }
    
    try {
        int edad = Integer.parseInt(edadTexto);
        if(edad <= 0 || edad > 120) {
            JOptionPane.showMessageDialog(this, "Edad no válida (debe ser entre 1 y 120)");
            edadField.requestFocus();
            return;
        }
        
        Gender genero;
        AccountType tipo;
        
        try {
            genero = Gender.valueOf(generoTexto.toUpperCase());
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Género inválido. Use: MASCULINO, FEMENINO, u OTRO");
            generoField.requestFocus();
            return;
        }
        
        try {
            tipo = AccountType.valueOf(tipoTexto.toUpperCase());
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Tipo de cuenta inválido. Use: PUBLIC o PRIVATE");
            tipoField.requestFocus();
            return;
        }
        
        boolean creado = loginManager.crearUser(username, password, fullname, genero, edad, tipo);
        
        if(creado) {
            if(!rutaFoto.isEmpty()) {
                loginManager.setFotoPerfil(username, rutaFoto);
            }
            
            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
            
            CardLayout cl = (CardLayout)((JPanel)getContentPane().getComponent(0)).getLayout();
            cl.show((JPanel)getContentPane().getComponent(0), "LOGIN");
            
            // Limpiar campos
            usernameField.setText("");
            passField.setText("");
            nombreField.setText("");
            edadField.setText("");
            generoField.setText("");
            tipoField.setText("");
            rutaField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "El usuario ya existe");
        }
    } catch(NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Edad debe ser un número válido");
        edadField.requestFocus();
    }
});
    return registro;
}

private JPanel INICIO_REGISTRO(){
    JPanel inicio = new JPanel (new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets (0,0,0,0);
    
    gbc.gridx=0;
    gbc.gridy=0;
    gbc.weightx=0.7;
    gbc.weighty=0.3;
    inicio.add(Fondo(),gbc);
    
    gbc.gridx = 1;
    gbc.weightx = 0.3;
    gbc.weighty=0.0;
    inicio.add(REGISTRO(), gbc);
    return inicio;
}
        
        
    
}
