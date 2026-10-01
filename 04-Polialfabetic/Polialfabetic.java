public class Polialfabetic {
    public static final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();
    private static final int clauSecreta = 3 ;
    public static void main(String[] args) {
        String msgs[]= {"Test 01 àrbitre, coixí, Perímetre", 
                        "Test 02 Taüll, DÍA", "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msgs[i]);
        }
    }

    public static int buscarLletra(char lletra, char[] alfabet){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == lletra){
                return i;
            }
        }
        return -1;
    }
    //random new random 


    public static String xifraPoliAlfa(String msg){
        return "";
    }
    public static String desxifraPoliAlfa(String msg){
        return "";
    }
}
