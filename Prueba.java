public class Prueba {
    public static void main(String[] args) {
        System.out.println(Ejercicio4());

    }

    public static boolean Ejercicio4() {
        int n = 11;
        int b = 1;
        int a = 0;
        int aux = 0;
        boolean res = true;
        boolean flag = true;
        int cont = 0;

        int universe[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        boolean flags[] = new boolean[universe.length];

        for (int i = 0; i < universe.length; i++) {
            for (int y = 0; y < universe.length; y++) {
                a = universe[i] * universe[y];

                if ((a % n) == (b % n)) {
                    flags[y] = true;
                    cont++;
                }
            }

            for (int k = 0; k < flags.length; k++) {

                if (flags[k] == true) {
                    aux++;
                }
            }

            if (aux != 1) {
                flag = false;
            }

            // reiniciar los flags
            for (int t = 0; t < flags.length; t++) {
                flags[t] = false;
            }
            aux = 0;

        }

        if((cont == universe.length) && (flag) ){
            res = true;
        }else{
            res = false;
        }

        return res;
    }
}