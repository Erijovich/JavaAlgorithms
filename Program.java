package hw_4;

public class Program {

    public static void main(String[] args) {

        HashMapa<String, String> hashMapa = new HashMapa<>(4);
        String oldValue = hashMapa.put("+79001112233", "AAAAAAAA");
        oldValue = hashMapa.put("+79001112231", "BBBBBB");
        oldValue = hashMapa.put("+79001112232", "CCCCCC");
        oldValue = hashMapa.put("+79001112233", "DDDDDDDD");
        oldValue = hashMapa.put("+79001112234", "EEEEEEE");
        oldValue = hashMapa.put("+79001112235", "MMMMMM");
        oldValue = hashMapa.put("+79001112236", "FFFFF");
        oldValue = hashMapa.put("+79001112237", "GGGGG1");
        oldValue = hashMapa.put("+79001112238", "GGGGG2");
        oldValue = hashMapa.put("+79001112239", "GGGGG3");
        oldValue = hashMapa.put("+79001112230", "GGGGG4");

        System.out.println(hashMapa);
    }
}
