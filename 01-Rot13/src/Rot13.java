

public class Rot13 {
    //te 39 lletres
    static char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {

        String[] palabras = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] xifrat = new String[palabras.length];

        System.out.println("Xifrat");
        System.out.println("---------");
        for (int i = 0; i < palabras.length; i++) {
            xifrat[i] = xifraRot13(palabras[i]);
            System.out.println(xifrat[i]);
        }
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < palabras.length; i++) {
            xifrat[i] = desxifraRot13(xifrat[i]);
            System.out.println(xifrat[i]);
        }

    }

    //envia un string y retorna un string 
    public static String xifraRot13(String cadena){
        String xifrat = "";
        //primero hay que recorrer la frase para ir guardando caracteres
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            //miramos si es mayus o minus
            if(Character.isUpperCase(lletra)){
                int indice = -1;
                //comparamos letra con el caracter mayus
                for (int j = 0; j < majuscules.length; j++) {

                    if(majuscules[j] == lletra){
                        //guardamos el indice para luego poder sumar 13
                        indice = j;
                    }
                }
                //calculamos la nueva posicion y tenemos en cuenta que el numero nunca supere el largo de los mayus
                int nuevoIndice = (indice + 13) % majuscules.length;
                // añadimos la nueva letra
                xifrat += majuscules[nuevoIndice];
            }else if(Character.isLowerCase(lletra)){
                //lo mismo que con las mayus pero ahora con las minus
                int indice = -1;
                //comparamos letra con el caracter mayus
                for (int j = 0; j < minuscules.length; j++) {

                    if(minuscules[j] == lletra){
                        indice = j;
                    }
                }
                //calculamos la nueva posicion y tenemos en cuenta que el numero nunca supere el largo de los mayus
                //por eso usamos el modulo para volver a empezar
                int nuevoIndice = (indice + 13) % minuscules.length;
                // añadimos la nueva letra
                xifrat += minuscules[nuevoIndice];
            }else{
                //entonces, si no son caracteres no se tienen que cifrar
                xifrat += lletra;
            }
        }

        return xifrat;
    }

    public static String desxifraRot13(String cadena){
        String desxifrat = "";
        //es lo mismo pero restando 13 en vez de sumar 
        
        //primero hay que recorrer la frase para ir guardando caracteres
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            //miramos si es mayus o minus
            if(Character.isUpperCase(lletra)){
                int indice = -1;
                //comparamos letra con el caracter mayus
                for (int j = 0; j < majuscules.length; j++) {

                    if(majuscules[j] == lletra){
                        indice = j;
                    }
                }
                //calculamos la nueva posicion y tenemos en cuenta que el numero nunca supere el largo de los mayus
                int nuevoIndice = ((indice - 13) + minuscules.length) % minuscules.length;
                // añadimos la nueva letra
                desxifrat += majuscules[nuevoIndice];
            }else if(Character.isLowerCase(lletra)){
                int indice = -1;
                //comparamos letra con el caracter mayus
                for (int j = 0; j < minuscules.length; j++) {

                    if(minuscules[j] == lletra){
                        //guardamos el indice para luego poder sumar 13
                        indice = j;
                    }
                }
                int nuevoIndice = ((indice - 13) + minuscules.length) % minuscules.length;
                // añadimos la nueva letra
                desxifrat += minuscules[nuevoIndice];
            }else{
                desxifrat += lletra;
            }
        }

        return desxifrat;
    }
}
