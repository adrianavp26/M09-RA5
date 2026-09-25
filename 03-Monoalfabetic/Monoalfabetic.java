
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;



public class Monoalfabetic {
    public static final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();

    public static void main(String[] args) {

        String test = "Test 01 àrbitre, coixí, Perímetre";
        
        System.out.println(permutaAlfabet(MAJUSCULES)); 
        xifraMonoAlfa(permutaAlfabet(MAJUSCULES), test);
    }

    public static int buscarLletra(char lletra, char[] alfabet){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == lletra){
                return i;
            }
        }
        return -1;
    }

    //tiene que ser privado
    public static char[] permutaAlfabet(char[] MAJUSCULES){

        //de charArray a List(arrayList) y despues de list a charArray
        ArrayList<Character> lista = new ArrayList<>();
        for(char caracter : MAJUSCULES){
            lista.add(caracter);
        }
        //permutar
        Collections.shuffle(lista);

        //pasar de list a charArray
        char[] resultat = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultat[i] = lista.get(i);
        }

        return resultat;
    }

    public static String xifraMonoAlfa(char[] permutat, String texto){

        //xifrem el text amb el permutat 
        int indice;
        String resultat = String.valueOf(permutat); //para poder añadirlo al string

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            //va por indice hay que aceder al alfa normal para poder encontral que caracter es en permutat
            //BUSCCAMOS EL INDICE
            indice = buscarLletra(caracter, MAJUSCULES);
            //si no lo encuentra -- FALTA TENER EN CUENTA MAYUS Y MINUS
            if(Character.isLowerCase(caracter)){

                //habra que volvera a pasar a lowercase 
            }
            if(indice == -1){
                resultat += caracter;
            }
            //lo guardamos para retornarlo al final
            resultat += permutat[indice];

        }

        return resultat;
    }

    public static String desxifraMonoAlfa(){

        return "";
    }

}

