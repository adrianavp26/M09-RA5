import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {
    public static final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();
    public static final char[] PERMUTAT = new char[MAJUSCULES.length];

    private static final long clauSecreta = 3333L;

    public static Random random;
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
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
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
    public static void initRandom(long clau){
        random = new Random(clau);
    }

    //permutar
    private  static void permutaAlfabet(){

        ArrayList<Character> lista = new ArrayList<>();
        for(char caracter : MAJUSCULES){
            lista.add(caracter);
        }
        Collections.shuffle(lista, random);

        for(int i = 0; i < lista.size(); i++){
            PERMUTAT[i] = lista.get(i); 
        }
    }


    public static String xifraPoliAlfa(String msg){
        String resultat = "";
        int indice;

        for (int i = 0; i < msg.length(); i++) {
            char caracter = msg.charAt(i);
            indice = buscarLletra(caracter, MAJUSCULES);
            permutaAlfabet();
            if(Character.isLowerCase(caracter)){

                indice = buscarLletra(Character.toUpperCase(caracter), MAJUSCULES);

                resultat += Character.toLowerCase(PERMUTAT[indice]);
            }else if (indice == -1){
                resultat += caracter;
            }else{
                resultat += PERMUTAT[indice];
            }

        }

        return resultat;
    }

    public static String desxifraPoliAlfa(String msg){
        String resultat = "";
        int indice;

        for (int i = 0; i < msg.length(); i++) {
            char caracter = msg.charAt(i);
            permutaAlfabet();
            indice = buscarLletra(caracter, PERMUTAT);
            if(Character.isLowerCase(caracter)){

                indice = buscarLletra(Character.toUpperCase(caracter), PERMUTAT);
                resultat += Character.toLowerCase(MAJUSCULES[indice]);

            }else if (indice == -1) {
                resultat += caracter;
            }else{ 
                resultat += MAJUSCULES[indice];
            }

        }

        return resultat;
    }
}
