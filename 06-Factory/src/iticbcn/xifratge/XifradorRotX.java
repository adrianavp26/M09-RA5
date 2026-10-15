package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX  implements Xifrador{

    String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    char[] minuscules = lletres.toCharArray();
    char[] majuscules = lletres.toUpperCase().toCharArray();
/*
    public static void main(String[] args) {
        String[] palabras = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] xifrat = new String[palabras.length];

        int[] numero = {0,2,4,6,8};

        System.out.println("Xifrat");
        System.out.println("---------");
        for (int i = 0; i < palabras.length; i++) {
            xifrat[i] = xifraRotX(palabras[i], numero[i]);
            System.out.println(xifrat[i]);
        }
        
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < palabras.length; i++) {
            xifrat[i] = desxifraRotX(xifrat[i], numero[i]);
            System.out.println(xifrat[i]);
        }
        System.out.println();

        String frase = "Úiüht, úiü wx ùxì ív?";
        forcaBrutaRotX(frase);
        
    }
 */
    //metode per buscar la lletra en el nostre propi alfabet
    public int buscarLletra(char lletra, char[] alfabet){
        for (int i = 0; i < alfabet.length; i++) {
            if(alfabet[i] == lletra){
                return i;
            }
        }
        return -1;
    }

    public String xifraRotX(String cadena, int desplaçament){
        String xifrat = "";

        int indice;
        char[] alfabetUtilitzat;

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            indice =  buscarLletra(lletra, majuscules);

            if(indice == -1){
                indice = buscarLletra(lletra, minuscules);
                alfabetUtilitzat = minuscules;
            }else{
                alfabetUtilitzat = majuscules;
            }

            //si no lo ha encontrado todavia no es una letra
            if(indice == -1){
                xifrat += lletra;
            }else{
                //si lo ha encotrado:
                int despl = desplaçament % alfabetUtilitzat.length;
                int nuevoIndice = ((indice + despl) + alfabetUtilitzat.length) % alfabetUtilitzat.length;
                xifrat += alfabetUtilitzat[nuevoIndice];
            }
        }

        return xifrat;
    }
    
    public String desxifraRotX(String cadena, int desplaçament){
        String desxifrat = "";
        int indice;
        char[] alfabetUtilitzat;

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            indice =  buscarLletra(lletra, majuscules);

            if(indice == -1){
                indice = buscarLletra(lletra, minuscules);
                alfabetUtilitzat = minuscules;
            }else{
                alfabetUtilitzat = majuscules;
            }

            //si no lo ha encontrado todavia no es una letra
            if(indice == -1){
                desxifrat += lletra;
            }else{
                //si lo ha encotrado:
                int despl = desplaçament % alfabetUtilitzat.length;
                int nuevoIndice = ((indice - despl) + alfabetUtilitzat.length) % alfabetUtilitzat.length;
                desxifrat += alfabetUtilitzat[nuevoIndice];
            }
        }

        return desxifrat;
    }

    public void  forcaBrutaRotX(String cadenaXifrada){
        String resultat;
        System.out.println("Missatge xifrat: " + cadenaXifrada);

        //hay que recorrer el alfabeto entero
        for (int i = 0; i < majuscules.length; i++) {
            resultat = desxifraRotX(cadenaXifrada, i);
            System.out.printf("(%d) -> %s%n", i, resultat);
        }
        
    }

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        int numeros;
        try {
            numeros = Integer.parseInt(clau);
            
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("la clau de rotX ha de ser un enter");
        }

        String xifrat = xifraRotX(msg, numeros); 
        byte[] bytes = xifrat.getBytes(StandardCharsets.UTF_8);
        
        return new TextXifrat(bytes);
    }
    @Override 
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{
        int numeros;
        try {
            numeros = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("la clau de rotX ha de ser un enter");
        }

        String text = xifrat.toString();
        
        return desxifraRotX(text, numeros);
    }
}
