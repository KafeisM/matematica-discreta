import java.util.function.Predicate;

public class Prueba {
    public static void main(String[] args) {
        //System.out.println(Ejercicio4());
        //System.out.println(prueba());
       // System.out.println(exercici1(5639, 3829));
       System.out.println(exercici2(6, 2, 1));
    }

    static boolean exercici2(int a, int b, int c) {
        //veure si mcd(a,b)|c, si es divisor, te solució
        boolean sol = false;
        int res = 0;
        int aux = -1;
        int major;
        int menor;
 

        if(a > b){
            major = a;
            menor = b;
        }else{
            major = b;
            menor = a;
        }
        
        while(aux != 0){
            aux = major % menor;
            major = menor;

            if(aux == 0){
                res =  menor;
            }

            menor = aux; 
        }   
        
        if((c % res) == 0){
            sol = true;
        }

      return sol; // HECHO
    }


    static int exercici1(int a, int b) {
        int res = 0;
        int aux = -1;
        int major;
        int menor;
 

        if(a > b){
            major = a;
            menor = b;
        }else{
            major = b;
            menor = a;
        }
        
        while(aux != 0){
            aux = major % menor;
            major = menor;

            if(aux == 0){
                res =  menor;
            }

            menor = aux; 
        }   

      return res; // HECHO
    }

    public static boolean prueba(){

        boolean res = true;
        Predicate<Integer> p;
        Predicate<Integer> q;
        int [] universe = new int[] { 1, 2, 3, 4, 5, 6 };
        p = x -> x % 4 == 0; // x és múltiple de 4
        q = x -> x % 2 == 0; // x és múltiple de 2
        for (int x = 0; x < universe.length; x++) {
            System.out.println(universe[x]);
        }    


        return res;
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
