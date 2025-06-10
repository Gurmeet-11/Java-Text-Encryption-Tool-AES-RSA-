import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.*;
import java.util.Base64;

public class TextEncryptionGUI extends JFrame {
    private JTextArea inputArea, outputArea;
    private JComboBox<String> algoBox;
    private JButton encryptBtn, decryptBtn;
    private SecretKey aesKey;
    private KeyPair rsaKeyPair;

    public TextEncryptionGUI() {
        setTitle("Text Encryption Tool (AES & RSA)");
        setSize(700, 500);
        setLayout(new BorderLayout());

        inputArea = new JTextArea(6, 40);
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);

        algoBox = new JComboBox<>(new String[]{"AES", "RSA"});
        encryptBtn = new JButton("Encrypt");
        decryptBtn = new JButton("Decrypt");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Algorithm:"));
        topPanel.add(algoBox);
        topPanel.add(encryptBtn);
        topPanel.add(decryptBtn);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(new JScrollPane(inputArea));
        centerPanel.add(new JScrollPane(outputArea));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        generateKeys();

        encryptBtn.addActionListener(e -> encryptText());
        decryptBtn.addActionListener(e -> decryptText());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void generateKeys() {
        try {
            // AES Key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            aesKey = keyGen.generateKey();

            // RSA KeyPair
            KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
            rsaGen.initialize(2048);
            rsaKeyPair = rsaGen.generateKeyPair();
        } catch (Exception ex) {
            outputArea.setText("Key generation error: " + ex.getMessage());
        }
    }

    private void encryptText() {
        try {
            String text = inputArea.getText();
            String algo = (String) algoBox.getSelectedItem();
            String encrypted = "";

            if ("AES".equals(algo)) {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                byte[] encryptedBytes = cipher.doFinal(text.getBytes());
                encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
            } else {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, rsaKeyPair.getPublic());
                byte[] encryptedBytes = cipher.doFinal(text.getBytes());
                encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
            }

            outputArea.setText(encrypted);
        } catch (Exception ex) {
            outputArea.setText("Encryption error: " + ex.getMessage());
        }
    }

    private void decryptText() {
        try {
            String encrypted = outputArea.getText();
            String algo = (String) algoBox.getSelectedItem();
            String decrypted = "";

            if ("AES".equals(algo)) {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, aesKey);
                byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
                decrypted = new String(decryptedBytes);
            } else {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, rsaKeyPair.getPrivate());
                byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
                decrypted = new String(decryptedBytes);
            }

            outputArea.setText(decrypted); // show decrypted result in outputArea
        } catch (Exception ex) {
            outputArea.setText("Decryption error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEncryptionGUI::new);
    }
}
