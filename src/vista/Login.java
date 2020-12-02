package src.vista;

import java.awt.*;
import javax.swing.*;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import src.managers.gestionUsuario.GestionUsuarioApi;

/**
 *
 * @author sebastian
 */
class bg extends JComponent {

	Image i;

	public bg(Image i) {
		this.i = i;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(i, 0, 0, null);
	}
}

public class Login extends JFrame {

    public JLabel BCerrar;
    public JButton BIngresar;
    public JLabel LAviso;
    public JPasswordField TFContrasena;
    public JTextField TFUsuario;
    private JLabel LUsuario;
    private JLabel LContrasena;

	public Login() {
		try {
			BufferedImage i = ImageIO.read(new File(getClass().getResource("/src/assets/icono.png").toURI()));
			setIconImage(i);
			setTitle("Electricaribe");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			BufferedImage bf = ImageIO.read(new File(getClass().getResource("/src/assets/login.png").toURI()));
			setContentPane(new bg(bf));
			initComponents();
			getRootPane().setDefaultButton(BIngresar);
			setLocationRelativeTo(null);
			LAviso.setVisible(false);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | URISyntaxException | IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

    private void initComponents() {

        TFUsuario = new JTextField();
        LUsuario = new JLabel();
        LContrasena = new JLabel();
        BIngresar = new JButton();
        BCerrar = new JLabel();
        TFContrasena = new JPasswordField();
        LAviso = new JLabel();
    
        attachListeners();
        setMinimumSize(new Dimension(640, 400));
        setUndecorated(true);
        setResizable(false);

        TFUsuario.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        LUsuario.setBackground(new Color(255, 255, 255));
        LUsuario.setForeground(new Color(0, 0, 0));
        LUsuario.setText("Usuario:");

        LContrasena.setBackground(new Color(255, 255, 255));
        LContrasena.setForeground(new Color(0, 0, 0));
        LContrasena.setText("Contraseña:");

        BIngresar.setText("Ingresar");

        BCerrar.setBackground(new Color(255, 255, 255));
        BCerrar.setForeground(new Color(255, 255, 255));
        BCerrar.setHorizontalAlignment(SwingConstants.RIGHT);
        BCerrar.setText("X");
        BCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BCerrar.setHorizontalTextPosition(SwingConstants.CENTER);
        BCerrar.setMaximumSize(new Dimension(25, 25));
        BCerrar.setMinimumSize(new Dimension(25, 25));
        BCerrar.setPreferredSize(new Dimension(25, 25));

        TFContrasena.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        TFContrasena.setPreferredSize(new Dimension(50, 25));

        LAviso.setForeground(Color.red);
        LAviso.setText("Credenciales inválidas.");
        LAviso.setFocusable(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(2, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(LAviso)
                                .addComponent(BIngresar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(LUsuario)
                                    .addComponent(LContrasena))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                                    .addComponent(TFUsuario, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFContrasena, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(235, 235, 235))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BCerrar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BCerrar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addGap(125)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(TFUsuario, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(LUsuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(LContrasena)
                    .addComponent(TFContrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(BIngresar)
                    .addComponent(LAviso))));

        pack();
    }

    private void attachListeners(){
        BCerrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                dispatchEvent(new WindowEvent(Login.this, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        BIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String resultado = GestionUsuarioApi.loguearUsuario(Login.this.TFUsuario.getText(), Login.this.TFContrasena.getText());
                if(resultado.equals("OK")){
                    Login.this.dispatchEvent(new WindowEvent(Login.this, WindowEvent.WINDOW_CLOSING));
                }else{
                    JSONObject jsonResultado = new JSONObject(resultado.toString());
                    JOptionPane.showMessageDialog(null, jsonResultado.getString("mensaje"));
                }
            }
        });
    }
}