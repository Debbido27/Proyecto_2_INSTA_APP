
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
}
