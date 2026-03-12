package instagram_22141094.GUI;

import Instagram_login_user.Base_cuenta.MediaType;
import Instagram_login_user.Post_Manager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class PANEL_SUBIR extends JPanel {

    private String usuario;
    private Post_Manager postManager;

    public PANEL_SUBIR(String usuario, Post_Manager postManager) {
        this.usuario = usuario;
        this.postManager = postManager;
        setLayout(new BorderLayout());
        setBackground(new Color(18, 18, 18));
        construir();
    }

    private void construir() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(18, 18, 18));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(8, 0, 8, 0);

        // Titulo
        JLabel titulo = new JLabel("Nueva publicación", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 149, 246));
        formPanel.add(titulo, gbc);

        // Contenido
        JLabel contenidoLabel = new JLabel("Contenido (máx 220 caracteres):");
        contenidoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contenidoLabel.setForeground(Color.WHITE);
        formPanel.add(contenidoLabel, gbc);

        JTextArea contenidoArea = new JTextArea(4, 20);
        contenidoArea.setBackground(new Color(54, 54, 54));
        contenidoArea.setForeground(Color.WHITE);
        contenidoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        contenidoArea.setLineWrap(true);
        contenidoArea.setWrapStyleWord(true);
        contenidoArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        // Limitar a 220 caracteres
        contenidoArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (contenidoArea.getText().length() >= 220) {
                    e.consume();
                }
            }
        });
        JScrollPane scrollContenido = new JScrollPane(contenidoArea);
        scrollContenido.setBorder(null);
        formPanel.add(scrollContenido, gbc);

        // Hashtags
        JLabel hashtagsLabel = new JLabel("Hashtags (separados por coma, sin #):");
        hashtagsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        hashtagsLabel.setForeground(Color.WHITE);
        formPanel.add(hashtagsLabel, gbc);

        JTextField hashtagsField = new JTextField();
        hashtagsField.setBackground(new Color(54, 54, 54));
        hashtagsField.setForeground(Color.WHITE);
        hashtagsField.setFont(new Font("Arial", Font.PLAIN, 14));
        hashtagsField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(hashtagsField, gbc);

        // Menciones
        JLabel mencionesLabel = new JLabel("Menciones (separados por coma, sin @):");
        mencionesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mencionesLabel.setForeground(Color.WHITE);
        formPanel.add(mencionesLabel, gbc);

        JTextField mencionesField = new JTextField();
        mencionesField.setBackground(new Color(54, 54, 54));
        mencionesField.setForeground(Color.WHITE);
        mencionesField.setFont(new Font("Arial", Font.PLAIN, 14));
        mencionesField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(mencionesField, gbc);

        // Ruta imagen
        JLabel rutaLabel = new JLabel("Ruta de imagen:");
        rutaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        rutaLabel.setForeground(Color.WHITE);
        formPanel.add(rutaLabel, gbc);

        JTextField rutaField = new JTextField();
        rutaField.setBackground(new Color(54, 54, 54));
        rutaField.setForeground(Color.WHITE);
        rutaField.setFont(new Font("Arial", Font.PLAIN, 14));
        rutaField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(rutaField, gbc);

        // Tipo multimedia
        JLabel tipoLabel = new JLabel("Tipo de imagen:");
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tipoLabel.setForeground(Color.WHITE);
        formPanel.add(tipoLabel, gbc);

        JComboBox<MediaType> tipoCombo = new JComboBox<>(new MediaType[]{
            MediaType.SQUARE, MediaType.VERTICAL, MediaType.HORIZONTAL
        });
        tipoCombo.setBackground(new Color(54, 54, 54));
        tipoCombo.setForeground(Color.WHITE);
        tipoCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        tipoCombo.setPreferredSize(new Dimension(0, 40));
        formPanel.add(tipoCombo, gbc);

        // Botón subir
        JButton subirBtn = new JButton("PUBLICAR");
        subirBtn.setBackground(new Color(0, 149, 246));
        subirBtn.setForeground(Color.WHITE);
        subirBtn.setFont(new Font("Arial", Font.BOLD, 16));
        subirBtn.setBorderPainted(false);
        subirBtn.setFocusPainted(false);
        subirBtn.setPreferredSize(new Dimension(0, 45));
        gbc.insets = new Insets(20, 0, 8, 0);
        formPanel.add(subirBtn, gbc);

        subirBtn.addActionListener(e -> {
            String contenido = contenidoArea.getText().trim();
            String hashtags = hashtagsField.getText().trim();
            String menciones = mencionesField.getText().trim();
            String ruta = rutaField.getText().trim();
            MediaType tipo = (MediaType) tipoCombo.getSelectedItem();

            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El contenido no puede estar vacío");
                return;
            }

            if (ruta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes agregar una imagen");
                return;
            }

            File archivo = new File(ruta);
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this, "La ruta de imagen no existe");
                return;
            }

            boolean ok = postManager.crearPost(usuario, contenido, hashtags, menciones, ruta, tipo.toString());
            if (ok) {
                // Limpiar campos
                contenidoArea.setText("");
                hashtagsField.setText("");
                mencionesField.setText("");
                rutaField.setText("");
                tipoCombo.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, "¡Publicación creada exitosamente!");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la publicación");
            }
        });

        JScrollPane scroll = new JScrollPane(formPanel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(18, 18, 18));
        add(scroll, BorderLayout.CENTER);
    }
}