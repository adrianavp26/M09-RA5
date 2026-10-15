package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    public final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();
    public final char[] PERMUTAT = new char[MAJUSCULES.length];


    public Random random;

    public int buscarLletra(char lletra, char[] alfabet){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == lletra){
                return i;
            }
        }
        return -1;
    }
    //random new random 
    public void initRandom(long clau){
        random = new Random(clau);
    }

    //permutar
    private void permutaAlfabet(){

        ArrayList<Character> lista = new ArrayList<>();
        for(char caracter : MAJUSCULES){
            lista.add(caracter);
        }
        Collections.shuffle(lista, random);

        for(int i = 0; i < lista.size(); i++){
            PERMUTAT[i] = lista.get(i); 
        }
    }


    public String xifraPoliAlfa(String msg){
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

    public String desxifraPoliAlfa(String msg){
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

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        long element;
        try{
            element = Long.parseLong(clau);
        }catch(NumberFormatException e){
            throw new ClauNoSuportada("La clau per xifrar Polialfabetic ha de se run String convertible a long");
        }
        initRandom(element);
        String xifra = xifraPoliAlfa(msg);
        byte[] bytes = xifra.getBytes(StandardCharsets.UTF_8);
        return new TextXifrat(bytes);
    }

    @Override 
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        long element;
        try {
            element = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrar Polialfabetic ha de se run String convertible a long");
        }
        initRandom(element);
        String text = xifrat.toString();
        return  desxifraPoliAlfa(text);
    }
}
