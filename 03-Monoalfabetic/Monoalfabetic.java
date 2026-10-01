
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;



public class Monoalfabetic {
    public static final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();

    public static void main(String[] args) {

        String[] test = {"Test 01 àrbitre, coixí, Perímetre", 
                        "Test 02 Taüll, DÍA", "Test 03 Peça, Òrrius, Bòvila"};
        String[] xifrat = new String[test.length];

        char[] permutat =  permutaAlfabet(MAJUSCULES);
        System.out.println(LLETRES);
        System.out.println(permutat);
        System.out.println("Xifratge:");
        for (int i = 0; i < test.length; i++) {
            xifrat[i] =  xifraMonoAlfa(permutat, test[i]);
            System.out.println(xifrat[i]);
        }
        System.out.println("Desxifratge:");
        for (int i = 0; i < test.length; i++) {
            xifrat[i] = desxifraMonoAlfa(permutat, xifrat[i]);
            System.out.println(xifrat[i]);
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

    private  static char[] permutaAlfabet(char[] MAJUSCULES){

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
        String resultat = "";

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            indice = buscarLletra(caracter, MAJUSCULES);
            
            if(Character.isLowerCase(caracter)){

                indice = buscarLletra(Character.toUpperCase(caracter), MAJUSCULES);
                
                resultat += Character.toLowerCase(permutat[indice]);
            }else if( indice == -1){
                resultat += caracter; //no será un caracter
            }else{//es mayusculas
                resultat += permutat[indice];
            }
            
        }

        return resultat;
    }

    public static String desxifraMonoAlfa(char[] permutat, String xifrat){
        int indice;
        String resultat = "";

        for (int i = 0; i < xifrat.length(); i++) {
            char caracter = xifrat.charAt(i);

            indice = buscarLletra(caracter, permutat);

            if(Character.isLowerCase(caracter)){

                indice = buscarLletra(Character.toUpperCase(caracter), permutat);
                
                resultat += Character.toLowerCase(MAJUSCULES[indice]);
            }else if( indice == -1){
                resultat += caracter; //no será un caracter
            }else{
                resultat += MAJUSCULES[indice];
            }
        }

        return resultat;
    }

}

