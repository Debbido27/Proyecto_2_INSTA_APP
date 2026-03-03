
package instagram_22141094.GUI;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class LOGIN  extends JFrame{
    
    public LOGIN(){
        
    }
    
    private void INICIAR(){
        setTitle("INSTAGRAM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,1200);
        setLocationRelativeTo(null);
        
        CardLayout cd = new CardLayout();
        JPanel inicar = new JPanel (cd);
        
    }
    
    private JPanel INICIO(){
        JPanel inicio = new JPanel (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets (0,0,0,0);
        
        
    }
}
