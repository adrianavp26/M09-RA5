import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class Hashes {
    
    public static void main(String[] args) {
        
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

    public String getPBKDF2AmbSalt(String pw, String salt){
        
    }

    public String forcaBruta(String alg, String hash, String salt){

    }

    public String getInterval(long tl, long t2){

    }
}
