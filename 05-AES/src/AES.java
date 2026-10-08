import java.security.SecureRandom;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "";


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
            System.out.println("Enc: " + new String (bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
        
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception{
        //obtenir bytes de l'string

        //generar el iv

        //generar el hash

        //encriptat

        // combinar el iv i el xifrat

        //retornar el iv + msgxifrat
        return;
    }

    public static String desxifraAES(Byte[] bMsgXifrat, String clau) throws Exception{

        //exreu iv

        //extreu part xifrada

        //fem el hash de la clau

        //desxifra

        //return string desxifrat
        return"";
    }

    public static byte[] generaIv(){

        SecureRandom randomSecure = new SecureRandom();


        return iv;
    }

    public static byte[] getBytesXifrats(){
        SecureRandom randomSecureRandom = SecureRandom.getInstance();

        byte[] iv

        return tamany;
    }

    


}
