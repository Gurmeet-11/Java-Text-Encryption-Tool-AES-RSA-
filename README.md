# 🛡️ Java Text Encryption Tool (AES & RSA)

A simple Java Swing-based GUI application for encrypting and decrypting text using **AES** and **RSA** encryption algorithms. This tool provides a hands-on experience with basic cryptography concepts and Java's `javax.crypto` and `java.security` libraries.


---

## 🚀 Features

- 🔐 Encrypt and decrypt text using **AES**
- 🔑 Encrypt and decrypt text using **RSA**
- 🧠 Auto-generates AES key and RSA key pairs
- ✨ Simple graphical interface built with **Java Swing**
- 📤 Encrypted text is displayed in a separate output panel

---

## 📁 File Structure


📦 TextEncryptionTool/
├── TextEncryptionGUI.java # Main GUI class
├── README.md # Project documentation
└── LICENSE

---

## 🧰 Requirements

- Java 8 or later
- javac and java in PATH (standard JDK tools)

 
---

💡 Usage
---


Type the message in the input text area.

Select an encryption algorithm: AES or RSA.

Click Encrypt to encrypt the text.

Click Decrypt to decrypt the last encrypted text.

The decrypted message will show in the output panel.

---

🔐 Algorithms Used
---



AES (Advanced Encryption Standard):

128-bit key

Symmetric encryption (same key for encrypt/decrypt)

RSA (Rivest-Shamir-Adleman):

2048-bit key pair

Asymmetric encryption (public key for encryption, private key for decryption)

---


🛠️ Customization
---


You can modify the source to:

Add file saving/loading support

Switch between encryption modes (CBC, ECB)

Use password-based key generation

Store or export generated keys

---

📝 License
---

This project is open source under the MIT License.

---

🙋‍♀️ Author
---

Developed by Gurmeet Kaur

Feel free to contribute or suggest improvements!





