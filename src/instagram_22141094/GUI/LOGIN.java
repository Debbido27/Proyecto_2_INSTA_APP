
package instagram_22141094.GUI;

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
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LOGIN  extends JFrame{
    
    public LOGIN(){
     INICIAR();   
    }
    
    private void INICIAR(){
        setTitle("INSTAGRAM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,1200);
        setLocationRelativeTo(null);
        
        CardLayout cd = new CardLayout();
        JPanel iniciar = new JPanel (cd);
        
        JPanel inicio = INICIO();
        iniciar.add(inicio,"LOGIN");
        add(iniciar);
        cd.show(iniciar,"LOGIN");
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
        JTextField campoUusario = new JTextField(15);
        campoUusario.setBackground(new Color(109,123,133));
        
        campoUusario.setPreferredSize(new Dimension(250,40));
        campoUusario.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(new Color(0, 0,0)),
         BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campoUusario.setFont(new Font("Arial",Font.PLAIN,14));
        
        JLabel USUARIO = new JLabel("Telefono, usuario, o email");
        USUARIO.setFont(new Font("Arial",Font.PLAIN,14));
        USUARIO.setForeground(new Color(142,142,142));
        
        
        JTextField CONTRA = new JTextField(15);
        CONTRA.setPreferredSize(new Dimension(250,40));
                CONTRA.setBackground(new Color(109,123,133));

        CONTRA.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(new Color(109, 123, 133)),
         BorderFactory.createEmptyBorder(5, 10, 5, 10)
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

            formulaPanel.add(USUARIO,fgbc);


            JTextField contrasena = new JTextField(15);

            fgbc.gridy=0;
            formulaPanel.add(USUARIO,fgbc);
            fgbc.gridy=1;
            formulaPanel.add(campoUusario,fgbc);
            fgbc.gridy=3;
            formulaPanel.add(CONTRA,fgbc);
            fgbc.gridy=2;
            formulaPanel.add(PASS,fgbc);
            fgbc.gridy=4;
            fgbc.insets= new Insets(20,20,20,20);
            formulaPanel.add(LOGIN, fgbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 0;       
                gbc.weighty = 0;       
                gbc.fill = GridBagConstraints.NONE; 
                gbc.anchor = GridBagConstraints.NORTH; 
                informacion.add(formulaPanel, gbc);

        
        return informacion;
    }
        
        
    
}
