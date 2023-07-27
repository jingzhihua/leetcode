package algorithm;

public class BinaryAdd {

    public static void main(String[] args) {
        System.out.println(addBinary("11", "10"));
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();

        int index_a = a.length() - 1;
        int index_b = b.length() - 1;
        int jinwei = 0;
        while (index_a >= 0 && index_b >= 0) {
            int single_number = a.charAt(index_a) + b.charAt(index_b) - 2 * '0' + jinwei;
            if (single_number >= 2) jinwei = 1;
            else jinwei = 0;
            res.append(single_number % 2 );
            index_a--;
            index_b--;
        }
        while (index_a-- >=0) {
            int single_number = a.charAt(index_a) + jinwei - '0';
            if (single_number >= 2) jinwei = 1;
            else jinwei = 0;
            res.append(single_number % 2);
        }
        while (index_b-- >=0) {
            int single_number = b.charAt(index_b) + jinwei - '0';
            if (single_number >= 2) jinwei = 1;
            else jinwei = 0;
            res.append(single_number % 2);
        }
        if(jinwei == 1) res.append(1);

        return res.reverse().toString();
    }
}
