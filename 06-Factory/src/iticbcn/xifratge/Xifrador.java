package iticbcn.xifratge;
public interface  Xifrador {

    //al ser una interfaz los metodos no tienen cuerpo
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
    public String desxifra(TextXifrat xifrat, String clau)throws ClauNoSuportada;
    
}
