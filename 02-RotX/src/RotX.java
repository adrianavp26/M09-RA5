public class RotX {

    static String[] lletras = {"aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz"};
    static char[] minuscules = ;
    static char[] majuscules = ;

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
        
    }

    public static String xifraRotX(String cadena, int desplaçament){
        String xifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);

        }

        return xifrat;
    }

    public static String desxifraRotX(String cadena, int desplaçament){
        String desxifrat = "";


        return desxifrat;
    }

    public static String forcaBrutaRotX(String cadenaXifrada){

        return"";
    }
}
