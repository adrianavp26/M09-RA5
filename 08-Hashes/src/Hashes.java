import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int npass = 0;

    public static void main(String[] args) throws Exception{
        
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("================================\n");
            System.out.printf("Algorisme:%s\n", algorismes[i]);

            System.out.printf("-----------------------\n");
            System.out.printf("--Inici de força bruta --\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps: %s\n", h.getInterval(t1,t2));
            System.out.printf("---------------------------\n\n");
        }
    }


    public String getSHA512AmbSalt(String pw, String salt) throws Exception{
        String pws = pw + salt;

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(pws.getBytes(StandardCharsets.UTF_8));
        HexFormat hex = HexFormat.of();
        String hash = hex.formatHex(bytes);

        return hash;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception{
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, 65536, 512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        SecretKey key = factory.generateSecret(spec);

        byte[] bytes = key.getEncoded();

        HexFormat hex = HexFormat.of();
        String hash = hex.formatHex(bytes);

        return hash;
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception{
        String charset = "abcdefABCDEF1234567890!";
        int n = charset.length();

        for (int longitud = 6; longitud >= 1; longitud--) {
            for(int i0 = 0; i0 < n; i0++){
                if(i0 > 0 && longitud < 1)continue;
                for (int i1 = 0; i1 < n; i1++) {
                    if(i1 > 0 && longitud < 2)continue;
                    for (int i2 = 0; i2 < n; i2++) {
                        if(i2 > 0 && longitud < 3)continue;
                        for (int i3 = 0; i3 < n; i3++) {
                            if(i3 > 0 && longitud < 4)continue;
                            for (int i4 = 0; i4 < n; i4++) {
                                if(i4 > 0 && longitud < 5)continue;
                                for (int i5 = 0; i5 < n; i5++) {
                                    if(i5 > 0 && longitud < 6)continue;
                                    
                                    char[] pwd = new char[longitud];
                                    if(longitud >= 1) pwd[0] = charset.charAt(i0);
                                    if(longitud >= 2) pwd[1] = charset.charAt(i1);
                                    if(longitud >= 3) pwd[2] = charset.charAt(i2);
                                    if(longitud >= 4) pwd[3] = charset.charAt(i3);
                                    if(longitud >= 5) pwd[4] = charset.charAt(i4);
                                    if(longitud >= 6) pwd[5] = charset.charAt(i5);

                                    String seleccionada = new String(pwd);
                                    npass++;

                                    String hashSeleccionada;
                                    if(alg.equals("SHA-512")){
                                        hashSeleccionada = getSHA512AmbSalt(seleccionada, salt);
                                    }else{
                                        hashSeleccionada = getPBKDF2AmbSalt(seleccionada, salt);
                                    }
                                    if(hashSeleccionada.equals(hash)) return seleccionada;

                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getInterval(long tl, long t2){
        long ms = t2 - tl;

        return ms + " ms";
    }
}
