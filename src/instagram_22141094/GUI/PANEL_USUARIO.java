
package instagram_22141094.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PANEL_USUARIO extends JFrame{
   private String usuario;
   
    public PANEL_USUARIO(String usuario){
      this.usuario=usuario;
      principal();
      
    }
    
    public JPanel principal(){
        
       JPanel Principal = new JPanel();
       setBackground(Color.WHITE);
       JLabel bienvenida = new JLabel("Bienvenido"+usuario,JLabel.CENTER);
       bienvenida.setFont(new Font("Arial",Font.BOLD,24));
       
        
        return Principal;
    }
}
