

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

    public static String xifraRot13(String cadena){
        String xifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);

            if(Character.isUpperCase(lletra)){
                int indice = -1;

                for (int j = 0; j < majuscules.length; j++) {

                    if(majuscules[j] == lletra){
                        indice = j;
                    }
                }
                int nuevoIndice = (indice + 13) % majuscules.length;
                
                xifrat += majuscules[nuevoIndice];
            }else if(Character.isLowerCase(lletra)){
                int indice = -1;

                for (int j = 0; j < minuscules.length; j++) {
                    if(minuscules[j] == lletra){
                        indice = j;
                    }
                }
                int nuevoIndice = (indice + 13) % minuscules.length;
                
                xifrat += minuscules[nuevoIndice];
            }else{
                xifrat += lletra;
            }
        }

        return xifrat;
    }

    public static String desxifraRot13(String cadena){
        String desxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            if(Character.isUpperCase(lletra)){
                int indice = -1;
                for (int j = 0; j < majuscules.length; j++) {

                    if(majuscules[j] == lletra){
                        indice = j;
                    }
                }
                int nuevoIndice = ((indice - 13) + minuscules.length) % minuscules.length;
                
                desxifrat += majuscules[nuevoIndice];
            }else if(Character.isLowerCase(lletra)){
                int indice = -1;

                for (int j = 0; j < minuscules.length; j++) {
                    if(minuscules[j] == lletra){
                        indice = j;
                    }
                }
                int nuevoIndice = ((indice - 13) + minuscules.length) % minuscules.length;
                desxifrat += minuscules[nuevoIndice];
            }else{
                desxifrat += lletra;
            }
        }

        return desxifrat;
    }
}
