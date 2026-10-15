package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;



public class XifradorMonoalfabetic implements Xifrador{

    private final char[] permutar;

    public final String LLETRES = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public final char[] MAJUSCULES = LLETRES.toUpperCase().toCharArray();

    public XifradorMonoalfabetic(){
        permutar = permutaAlfabet(MAJUSCULES);
    }


    public int buscarLletra(char lletra, char[] alfabet){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == lletra){
                return i;
            }
        }
        return -1;
    }

    private char[] permutaAlfabet(char[] MAJUSCULES){

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

    public String xifraMonoAlfa(char[] permutat, String texto){

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

    public String desxifraMonoAlfa(char[] permutat, String xifrat){
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

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratge monoalfabetic no soporta clau != null");
        }

        String xifrat = xifraMonoAlfa(permutar, msg); 
        byte[] bytes = xifrat.getBytes(StandardCharsets.UTF_8);
        
        return new TextXifrat(bytes);
    }
    @Override 
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratge monoalfabetic no soporta clau != null");
        }

        String text = xifrat.toString();
        
        return desxifraMonoAlfa(permutar, text);
    }

}

