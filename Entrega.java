import java.lang.AssertionError;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import javax.annotation.processing.SupportedOptions;

import java.util.Set;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb el comentari "// TO DO".
 *
 * Cada tema té el mateix pes, i l'avaluació consistirà en:
 *
 * - Principalment, el correcte funcionament de cada mètode (provant amb diferents entrades). Teniu
 *   alguns exemples al mètode `main`.
 *
 * - La neteja del codi (pensau-ho com faltes d'ortografia). L'estàndar que heu de seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . No és
 *   necessari seguir-la estrictament, però ens basarem en ella per jutjar si qualcuna se'n desvia
 *   molt.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni mètodes de classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 8.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Jordi Florit Ensenyat
 * - Nom 2: Marc Ferrer Fernández
 * - Nom 3: Raul Alcauter Sansó
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital abans de la data que se us hagui
 * comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més fàcilment
 * les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat, assegurau-vos
 * que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
    /*
     * Aquí teniu els exercicis del Tema 1 (Lògica).
     *
     * Els mètodes reben de paràmetre l'univers (representat com un array) i els
     * predicats adients
     * (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és
     * un element de
     * l'univers, podeu fer-ho com `p.test(x)`, té com resultat un booleà. Els
     * predicats de dues
     * variables són de tipus `BiPredicate<Integer, Integer>` i similarment
     * s'avaluen com
     * `p.test(x, y)`.
     *
     * En cada un d'aquests exercicis us demanam que donat l'univers i els predicats
     * retorneu `true`
     * o `false` segons si la proposició donada és certa (suposau que l'univers és
     * suficientment
     * petit com per utilitzar la força bruta)
     */
    static class Tema1 {
        /*
         * És cert que ∀x,y. P(x,y) -> Q(x) ^ R(y) ?
         */
        static boolean exercici1(
                int[] universe,
                BiPredicate<Integer, Integer> p,
                Predicate<Integer> q,
                Predicate<Integer> r) {

            boolean res = true;

            for (int x : universe) {
                for (int y : universe) {
                    if (p.test(x, y)) {
                        res = (q.test(x) && r.test(y));

                        if (!res) {
                            System.out.println("Ejercicio 1: " + res);
                            return res;
                        }
                    }
                }
            }

            System.out.println("Ejercicio 1: " + res);
            return res; // HECHO
        }

        /*
         * És cert que ∃!x. ∀y. Q(y) -> P(x) ?
         */
        static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {

            boolean res = true;
            int contador = 0;
            int aux = 0;

            for (int x = 0; x < universe.length; x++) {
                //cercam un cas (amb la mateixa x) en el que la implicació sigui 1 -> 0, per determinar que es fals
                for (int y = 0; y < universe.length; y++) {
                    if (!(q.test(universe[y]) && (p.test(universe[x]) == false))) {
                        //si això passa no farem res, en cas contrari (que la implicació sigui correcta) incrementarem un comptador
                        contador++;
                    }
                }

                if (contador == universe.length) {
                    //si el comptador es igual a la dimensió del univers, es compleix per tot el univers amb una única x
                    aux++;
                }
                contador = 0; //per probar la resta de x, resetetjam el comptador

                if (aux > 1) { //si es compleix més d'un pic, vol dir que no és una única x. Per tant, fals
                    res = false;
                    System.out.println("Ejercicio 2: " + res);
                    return res;
                }

            }
            if (aux == 0) { //si no es compleix cap pic, també és fals.
                res = false;
            }
            System.out.println("Ejercicio 2: " + res);
            return res; // TO DO
        }

        /*
         * És cert que ¬(∃x. ∀y. y ⊆ x) ?
         *
         * Observau que els membres de l'univers són arrays, tractau-los com conjunts i
         * podeu suposar
         * que cada un d'ells està ordenat de menor a major.
         */
        static boolean exercici3(int[][] universe) {
            boolean existe = false;

            boolean res = false;
            for (int[] y : universe) {
                for (int[] x : universe) {
                    if (x.length < y.length) {
                        existe = false;
                    } else {
                        int n = 0;
                        for (int i = 0; i < y.length && n != y.length; i++) {
                            for (int j = 0; j < x.length; j++) {
                                if (y[i] == x[j]) {
                                    n++;
                                }
                            }
                            if (n == y.length) {
                                existe = true;
                                break;
                            } else {
                                existe = false;
                            }
                        }
                    }
                }
                if (!existe) {
                    res = false;
                    System.out.println("Ejercicio 3: " + !res);
                    return !res;
                } else {
                    res = true;
                }

            }
            System.out.println("Ejercicio 3: " + !res);
            return !res; // HECHO

        }

        /*
         * És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?
         */
        static boolean exercici4(int[] universe, int n) {

            boolean[] flags = new boolean[universe.length];
            int aux = 0;
            boolean res = false;
            int cont = 0;
            boolean esunic = true;

            // operacio com a ≡ b (mod n) <---> n|(a-b) <---> a mod n == b mod n
            // a i b son elements del univers, per tot x existeix un unic y que ho compleix
            int a = 0;
            int b = 1;

            for (int i = 0; i < universe.length; i++) {
                for (int y = 0; y < universe.length; y++) {
                    a = universe[i] * universe[y];

                    if ((a % n) == (b % n)) {
                        flags[y] = true;
                        cont++;
                    }
                }

                //comprovam que nomes es compleix per un unic valor y
                for (int k = 0; k < flags.length; k++) {

                    if (flags[k] == true) {
                        aux++;
                    }
                }

                //si no es unic ho posam, que per aquella x hi ha mes de un y
                if (aux != 1) {
                    esunic = false;
                }

                // reiniciar els flags per la seguent iteracio de x
                for (int t = 0; t < flags.length; t++) {
                    flags[t] = false;
                }
                aux = 0;

            }

            if ((cont == universe.length) && (esunic)) {
                res = true;
            } else {
                res = false;
            }

            System.out.println("Ejercicio 4:" + res);
            return res;
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // ∀x,y. P(x,y) -> Q(x) ^ R(y)

            assertThat(
                    exercici1(
                            new int[] { 2, 3, 5, 6 },
                            (x, y) -> x * y <= 4,
                            x -> x <= 3,
                            x -> x <= 3));

            assertThat(
                    !exercici1(
                            new int[] { -2, -1, 0, 1, 2, 3 },
                            (x, y) -> x * y >= 0,
                            x -> x >= 0,
                            x -> x >= 0));

            // Exercici 2
            // ∃!x. ∀y. Q(y) -> P(x) ?

            assertThat(
                    exercici2(
                            new int[] { -1, 1, 2, 3, 4 },
                            x -> x < 0,
                            x -> true));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6 },
                            x -> x % 2 == 0, // x és múltiple de 2
                            x -> x % 4 == 0 // x és múltiple de 4
                    ));

            // Exercici 3
            // ¬(∃x. ∀y. y ⊆ x) ?

            assertThat(
                    exercici3(new int[][] { { 1, 2 }, { 0, 3 }, { 1, 2, 3 }, {} }));

            assertThat(
                    !exercici3(new int[][] { { 1, 2 }, { 0, 3 }, { 1, 2, 3 }, {}, { 0, 1, 2, 3 } }));

            // Exercici 4
            // És cert que ∀x. ∃!y. x·y ≡ 1 (mod n) ?

            assertThat(
                    exercici4(
                            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                            11));

            assertThat(
                    !exercici4(
                            new int[] { 0, 5, 7 },
                            13));
        }
    }

    /*
     * Aquí teniu els exercicis del Tema 2 (Conjunts).
     *
     * De la mateixa manera que al Tema 1, per senzillesa tractarem els conjunts com
     * arrays (sense
     * elements repetits). Per tant, un conjunt de conjunts d'enters tendrà tipus
     * int[][].
     *
     * Les relacions també les representarem com arrays de dues dimensions, on la
     * segona dimensió
     * només té dos elements. Per exemple
     * int[][] rel = {{0,0}, {1,1}, {0,1}, {2,2}};
     * i també donarem el conjunt on està definida, per exemple
     * int[] a = {0,1,2};
     *
     * Les funcions f : A -> B (on A i B son subconjunts dels enters) les
     * representam donant int[] a,
     * int[] b, i un objecte de tipus Function<Integer, Integer> que podeu avaluar
     * com f.apply(x) (on
     * x és un enter d'a i el resultat f.apply(x) és un enter de b).
     */
    static class Tema2 {
        /*
         * És `p` una partició d'`a`?
         *
         * `p` és un array de conjunts, haureu de comprovar que siguin elements d'`a`.
         * Podeu suposar que
         * tant `a` com cada un dels elements de `p` està ordenat de menor a major.
         */
        static boolean exercici1(int[] a, int[][] p) {
            int tam = a.length;
            boolean[] usado = new boolean[tam];
            for (int i = 0; i < tam; i++) {
                usado[i] = false;
            }
            boolean res;
            int i = 0;
            int min = a[0], max = a[tam - 1];
            for (int ax : a) {
                boolean existe = false;
                for (int[] px : p) {
                    for (int ppx : px) {
                        if (ppx >= min && ppx <= max) {
                            if (ax == ppx) {
                                existe = true;
                                if (!usado[i]) {
                                    usado[i] = true;
                                } else {
                                    res = false;
                                    System.out.println(res);
                                    return res;
                                }
                                break;
                            }
                        } else {
                            res = false;
                            System.out.println(res);
                            return res;
                        }
                    }
                }
                if (!existe) {
                    res = false;
                    System.out.println(res);
                    return res;
                }
                i++;
            }

            res = true;
            System.out.println(res);
            return res; // HECHO
        }

        /*
         * Comprovau si la relació `rel` definida sobre `a` és un ordre parcial i que
         * `x` n'és el mínim.
         *
         * Podeu soposar que `x` pertany a `a` i que `a` està ordenat de menor a major.
         */
        static boolean exercici2(int[] a, int[][] rel, int x) {
            boolean isRefl, isAntis, isTrans, isMin, res, isRel;
            int c = 0;

            for (int ax : a) {
                for (int[] relx : rel) {
                    if (ax == relx[0] && ax == relx[1]) {
                        c++;
                        break;
                    }
                }
            }

            isRefl = (c == a.length);

            if (!isRefl) {
                res = false;
                System.out.println(res);
                return res;
            }
            c = 0;
            int c1 = 0;

            for (int[] relx : rel) {
                int xx = relx[0], yy = relx[1];
                for (int[] rely : rel) {
                    if (rely[0] == yy) {
                        int zz = rely[1];
                        c++;
                        for (int[] relz : rel) {
                            if (relz[0] == xx && relz[1] == zz) {
                                c1++;
                                break;
                            }
                        }
                    }

                }

            }

            isTrans = (c1 == c);

            if(!isTrans){
                res = false;
                System.out.println(res);
                return res;
            }

            for (int[] relx : rel) {
                int xx = relx[0], yy = relx[1];
                for (int[] rely : rel) {
                    if((rely[0] == yy && rely[1] == xx) && yy != xx){
                        res = false;
                        System.out.println(res);
                        return res;
                    }
                }
            }

            isAntis = true;
            int min = x;
            c = 0;

            for (int[] relx : rel) {
                if(relx[0] == min || relx[1] == min){
                    c++;
                }
            }

            isMin = (c == a.length);

            if(!isMin){
                res = false;
                System.out.println(res);
                return res;
            }

            for (int[] relx : rel) {
                if(!((relx[0] >= a[0] && relx[0] <= a[a.length-1]) && (relx[1] >= a[0] && relx[1] <= a[a.length-1]))){
                    res = false;
                    System.out.println(res);
                    return res;
                }
            }

            isRel = true;

            if(isRefl && isTrans && isAntis && isMin && isRel){
                res = true;
                System.out.println(res);
                return res;
            }else{
                res = false;
                System.out.println(res);
                return false;
            }
            //HECHO
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`. Trobau
         * l'antiimatge de
         * `y` (ordenau el resultat de menor a major, podeu utilitzar `Arrays.sort()`).
         * Podeu suposar
         * que `y` pertany a `codom` i que tant `dom` com `codom` també estàn ordenats
         * de menor a major.
         */
        static int[] exercici3(int[] dom, int[] codom, Function<Integer, Integer> f, int y) {

            int c = 0;
            for (int i : dom) {
                if(y == f.apply(i)){
                    c++;
                }
            }

            int[] res = new int[c];

            if(c == 0){
                return res;
            }else{
                int ix = 0;
                for (int i : dom) {
                    if(y == f.apply(i)){
                        res[ix] = i;
                    }
                }
            }

            Arrays.sort(res);

            return res; // HECHO
        }

        /*
         * Suposau que `f` és una funció amb domini `dom` i codomini `codom`. Retornau:
         * - 3 si `f` és bijectiva
         * - 2 si `f` només és exhaustiva
         * - 1 si `f` només és injectiva
         * - 0 en qualsevol altre cas
         *
         * Podeu suposar que `dom` i `codom` estàn ordenats de menor a major. Per
         * comoditat, podeu
         * utilitzar les constants definides a continuació:
         */
        static final int NOTHING_SPECIAL = 0;
        static final int INJECTIVE = 1;
        static final int SURJECTIVE = 2;
        static final int BIJECTIVE = INJECTIVE + SURJECTIVE;

        static int exercici4(int[] dom, int[] codom, Function<Integer, Integer> f) {
            boolean noInyectiva = false; //indica si esa función NO es inyectiva
            boolean noExhaustiva = false;

            //mirar si inyectiva
            for (int i = 0; i < dom.length; i++){
                int x = dom[i];
                for (int j = 0; j < dom.length; j++){
                    int y = dom[j];
                    if (i == j){
                    } else{
                        if (f.apply(x) == f.apply(y)){
                            noInyectiva = true;
                            break;
                        }
                    }
                }
            }

            //mirar si exhaustiva
            int aux = 0;
            int aux2 = 0;
            for (int i = 0; i < codom.length; i++){
                int y = codom[i];
                for (int j = 0; j < dom.length; j++){
                    int x = dom[j];
                    if (f.apply(x) == y){
                        aux++;
                    }

                }
                if (aux != 0){
                    aux2++;
                }
                aux = 0;

            }
            if (aux2 != codom.length){
                noExhaustiva = true;
            }


            //comprobar los resultados de los anteriores apartadoss y determinar res
            if (!noInyectiva && !noExhaustiva){
                System.out.println("Ej 4: " + BIJECTIVE);
                return BIJECTIVE;
            } else if (!noInyectiva && noExhaustiva){
                System.out.println("Ej 4: " + INJECTIVE);
                return INJECTIVE;
            } else if (noInyectiva && !noExhaustiva){
                System.out.println("Ej 4: " + SURJECTIVE);
                return SURJECTIVE;
            } else if(noInyectiva && noExhaustiva){
                System.out.println("Ej 4: " + NOTHING_SPECIAL);
                return NOTHING_SPECIAL;
            }

            return -1; // TO DO
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // `p` és una partició d'`a`?

            assertThat(
                    exercici1(
                            new int[] { 1, 2, 3, 4, 5 },
                            new int[][] { { 1, 2 }, { 3, 5 }, { 4 } }));

            assertThat(
                    !exercici1(
                            new int[] { 1, 2, 3, 4, 5 },
                            new int[][] { { 1, 2 }, { 5 }, { 1, 4 } }));

            // Exercici 2
            // és `rel` definida sobre `a` d'ordre parcial i `x` n'és el mínim?

            ArrayList<int[]> divisibility = new ArrayList<int[]>();

            for (int i = 1; i < 8; i++) {
                for (int j = 1; j <= i; j++) {
                    if (i % j == 0) {
                        // i és múltiple de j, és a dir, j|i
                        divisibility.add(new int[] { i, j });
                    }
                }
            }

            assertThat(
                    exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6, 7 },
                            divisibility.toArray(new int[][] {}),
                            1));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3 },
                            new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 1, 2 }, { 2, 3 } },
                            1));

            assertThat(
                    !exercici2(
                            new int[] { 1, 2, 3, 4, 5, 6, 7 },
                            divisibility.toArray(new int[][] {}),
                            2));

            // Exercici 3
            // calcular l'antiimatge de `y`

            assertThat(
                    Arrays.equals(
                            new int[] { 0, 2 },
                            exercici3(
                                    new int[] { 0, 1, 2, 3 },
                                    new int[] { 0, 1 },
                                    x -> x % 2, // residu de dividir entre 2
                                    0)));

            assertThat(
                    Arrays.equals(
                            new int[] {},
                            exercici3(
                                    new int[] { 0, 1, 2, 3 },
                                    new int[] { 0, 1, 2, 3, 4 },
                                    x -> x + 1,
                                    0)));

            // Exercici 4
            // classificar la funció en res/injectiva/exhaustiva/bijectiva

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3 },
                            x -> (x + 1) % 4) == BIJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3, 4 },
                            x -> x + 1) == INJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1 },
                            x -> x / 2) == SURJECTIVE);

            assertThat(
                    exercici4(
                            new int[] { 0, 1, 2, 3 },
                            new int[] { 0, 1, 2, 3 },
                            x -> x <= 1 ? x + 1 : x - 1) == NOTHING_SPECIAL);
        }
    }

    static class Tema3 {
        /*
         * Donat `a`, `b` retornau el màxim comú divisor entre `a` i `b`.
         *
         * Podeu suposar que `a` i `b` són positius.
         */
        static int exercici1(int a, int b) {
            int res = 0;
            int aux = -1;
            int major;
            int menor;

            //mirar quin es el major de els 2 i assignar-ho

            if (a > b) {
                major = a;
                menor = b;
            } else {
                major = b;
                menor = a;
            }

            //mentre el residu sigui diferent de 0, anar aplicant el algoritme de euclides,
            //el darrer residu, abans de que doni 0, sera el máxim comu divisor
            while (aux != 0) {
                aux = major % menor;
                major = menor;

                if (aux == 0) {
                    res = menor;
                }

                menor = aux;
            }

            System.out.println("Ejercicio 1: " + res);

            return res; // HECHO
        }

        /*
         * Es cert que `a``x` + `b``y` = `c` té solució?.
         *
         * Podeu suposar que `a`, `b` i `c` són positius.
         */
        static boolean exercici2(int a, int b, int c) {
            // veure si mcd(a,b)|c, si es divisor, te solució
            //per veure el mcd, aplicar el algoritme de euclides
            boolean sol = false;
            int res = 0;
            int aux = -1;
            int major;
            int menor;

            if (a > b) {
                major = a;
                menor = b;
            } else {
                major = b;
                menor = a;
            }

            while (aux != 0) {
                aux = major % menor;
                major = menor;

                if (aux == 0) {
                    res = menor;
                }

                menor = aux;
            }

            if ((c % res) == 0) {
                sol = true;
            }

            System.out.println("Ejercicio 2: " + sol);

            return sol; // HECHO
        }

        /*
         * Quin es l'invers de `a` mòdul `n`?
         *
         * Retornau l'invers sempre entre 1 i `n-1`, en cas que no existeixi retornau -1
         */
        static int exercici3(int a, int n) {
            int aux;
            int aux2;
            int res = 0;
            boolean trobat = false;
            for (int b = 1; b < n; b++){ //aquest bucle realitza una cerca de la longitud del mòdul provant quin és l'invèrs
                aux = a * b;
                aux2 = aux % n; //treim el residu del nombre obtingut i miram si es = 1
                if (aux2 == 1){
                    trobat = true;
                    res = b;
                    break;
                }
            }
            if (trobat){ //si s'ha trobat, retornam true, sino fals
                if(res > n){
                    res = res % n;
                }
                System.out.println("Ejercicio 3: " + res);
                return res;
            } else {
                System.out.println("Ejercicio 3: " + -1);
                return -1;
            }
             // HECHO
        }

        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu
         * `main`)
         */
        static void tests() {
            // Exercici 1
            // `mcd(a,b)`

            assertThat(
                    exercici1(2, 4) == 2);

            assertThat(
                    exercici1(1236, 984) == 12);

            // Exercici 2
            // `a``x` + `b``y` = `c` té solució?

            assertThat(
                    exercici2(4, 2, 2));
            assertThat(
                    !exercici2(6, 2, 1));
            // Exercici 3
            // invers de `a` mòdul `n`
            assertThat(exercici3(2, 5) == 3);
            assertThat(exercici3(2, 6) == -1);
        }
    }

    static class Tema4 {
        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, retornau l'ordre i la mida del graf.
         */
        static int[] exercici1(int[][] A) {
            int o = A.length, m = 0;
            for (int j = 0; j < A.length; j++) {
                for (int i = j+1; i < A.length; i++) {
                    if(A[i][j] == 1){
                        m++;
                    }
                }
            }
            System.out.println(m);
          return new int[]{o,m}; // HECHO
        }
    
        /*
         * Donada una matriu d'adjacencia `A` d'un graf no dirigit, digau si el graf es eulerià.
         */
        static boolean exercici2(int[][] A) {
            int grau = 0;

            //perque un  graf sigui euleria ha de tenir tots els nodes grau parell
            //per tant si ens donen la matriu de adjacencia, cada fila ens indica el grau de aquell node
            boolean noEuleria = false;
            boolean res = false;

                for(int x = 0; x < A.length && !noEuleria; x++){
                    for(int y = 0; y < A[x].length && !noEuleria; y++ ){
                        if(A[x][y] == 1){
                            grau++;
                        }
                    }

                    //si el un grau no es parell ja surt i retornara fals
                    if(grau % 2 != 0){
                        noEuleria = true;
                    }
                    grau = 0;
                }

            if(noEuleria){
                res = false;
            }else{
                res = true;
            }

            System.out.println("Exercici 2 " + res);

          return res; // HECHO
        }
    
        /*
         * Donat `n` el número de fulles d'un arbre arrelat i `d` el nombre de fills dels nodes interiors, i de l'arrel,
         * retornau el nombre total de vèrtexos de l'arbre
         *
         */
        static int exercici3(int n, int d) {
            //si cada node interior té d fills, el seu grau serà de d + 1, ja que també tenen un pare
            int grau = d + 1;
            int numNodes;

            //primer hem de conseguir el nombre de nodes interiors
            numNodes = ((2*n) - (n + 2)) / (grau - 2);
            
            //quan tenim els nombres de nodes interiors, els sumam a l'arrel i a les fulles per obtenir tots els vertexs
            int vertexs = numNodes + 1 + n;
            System.out.println("Exercici 3: " + vertexs);
            return vertexs; // HECHO
        }
    
        /*
         * Donada una matriu d'adjacencia `A` d'un graf connex no dirigit, digau si el graf conté algún cicle.
         */
        static boolean exercici4(int[][] A) {
            //un arbre es un graf conex aciclic, per tant si confirmam que es unn arbre, ja sabrem que no conte cap cicle
            //condicio per ser arbre:
            //1. E(G) = V(G) - 1
            //2. 2*E = SUM(d(u)) 

            boolean cond = true;
            int V = A.length; // el numero de nodes sera el nombre de files de la matriu

            int E = V-1;    
            int sum = 0;
            
            for(int i = 0; i < A.length; i++){
                for(int j = 0; j < A.length; j++){
                    if(A[i][j] == 1){
                        sum++; //sumatori de els graus de els nodes
                    }
                } 
            }

            //si es cumpleix que es arbre, no contindra cicle
            if((2*E) == sum){
                cond = false;
            }
          
            System.out.println(cond);
            return cond; // HECHO
        }
        /*
         * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
         */
        static void tests() {
          // Exercici 1
          // `ordre i mida`
    
          assertThat(
            Arrays.equals(exercici1(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}}), new int[] {3, 2})
          );
    
          assertThat(
            Arrays.equals(exercici1(new int[][] { {0, 1, 0, 1}, {1, 0, 1, 1}, {0 , 1, 0, 1}, {1, 1, 1, 0}}), new int[] {4, 5})
          );
          
          
          // Exercici 2
          // `Es eulerià?`
    
          assertThat(
                  exercici2(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
          );
          assertThat(
                  !exercici2(new int[][] { {0, 1, 0}, {1, 0, 1}, {0,1, 0}})
          );
          // Exercici 3
          // `Quants de nodes té l'arbre?`
          assertThat(exercici3(5, 2) == 9);
          assertThat(exercici3(7, 3) == 10);
    
          // Exercici 4
          // `Conté algún cicle?`
          assertThat(
                  exercici4(new int[][] { {0, 1, 1}, {1, 0, 1}, {1, 1, 0}})
          );
          assertThat(
                  !exercici4(new int[][] { {0, 1, 0}, {1, 0, 1}, {0, 1, 0}})
          );
    
        }
      }

    /*
     * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que
     * haurien de donar
     * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres
     * (no els tendrem en
     * compte, però és molt recomanable).
     *
     * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor
     * sigui `true`.
     */
    public static void main(String[] args) {
        
        System.out.println("TEMA 1:");
        Tema1.tests();

        System.out.println("TEMA 2: ");
        Tema2.tests();

        System.out.println("TEMA 3: ");
        Tema3.tests();

        System.out.println("TEMA 4: ");
        Tema4.tests();
    }

    static void assertThat(boolean b) {
        if (!b)
            throw new AssertionError();
    }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
