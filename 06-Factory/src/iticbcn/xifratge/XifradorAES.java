package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Jhandajndfpow";


    public static void main(String[] args) {
        
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try{
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            }catch(Exception e){
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("---------------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
        
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception{
        //obtenir bytes de l'string
        byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);
        //generar el iv
        byte[] ivBytes = generaIv();
        //generar el hash
        SecretKeySpec clauSecreta = generaHash(clau);
        //encriptat
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clauSecreta, new IvParameterSpec(ivBytes));

        byte[] xifrat = cipher.doFinal(bytesMsg);
        byte[] resultat = new byte[MIDA_IV + xifrat.length];

        for (int i = 0; i < MIDA_IV; i++) {
            resultat[i] = ivBytes[i];
        }
        for (int i = 0; i < xifrat.length; i++) {
            resultat[MIDA_IV + i] = xifrat[i];
        }
        return resultat;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String clau) throws Exception{

        //exreu iv
        byte[] ivBytes = extreuIv(bMsgXifrat);
        //extreu part xifrada
        byte[] xifrat = getBytesXifrats(bMsgXifrat);
        //fem el hash de la clau
        SecretKeySpec clauSecreta = generaHash(clau);
        //desxifra - el decryptmode
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clauSecreta, new IvParameterSpec(ivBytes));
        byte[] byteMsg = cipher.doFinal(xifrat);
        
        return new String(byteMsg, StandardCharsets.UTF_8);
    }
    //generador de iv
    public static byte[] generaIv(){
        SecureRandom randomSecure = new SecureRandom();
        randomSecure.nextBytes(iv);
        return iv;
    }
    //get de bytesxifrats
    public static byte[] getBytesXifrats(byte[] bMsgXifrat){

        byte[] tamany = new byte[bMsgXifrat.length - MIDA_IV];
        for (int i = 0; i < tamany.length; i++) {
            tamany[i] = bMsgXifrat[i + MIDA_IV];
        }
        return tamany;
    }
    //extreu  IV
    public static byte[] extreuIv(byte[] bMsgXifrat){
        byte[] ivExtret = new byte[MIDA_IV];

        for (int i = 0; i < ivExtret.length; i++) {
            ivExtret[i] = bMsgXifrat[i];
        }
        return ivExtret;
    }
    // creació de clau er al cipher
    public static SecretKeySpec generaHash(String password) throws Exception{
        
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }


}
