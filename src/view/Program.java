package view;

import model.Automaton;

public class Program {
    public static void main(String[] args) {
        Automaton afd = new Automaton();

        System.out.println();
        //Valid
        System.out.println(afd.verify("2546"));
        System.out.println(afd.verify("24."));
        System.out.println(afd.verify(".5"));
        System.out.println(afd.verify("314e-2"));
        System.out.println(afd.verify("-4.2e2"));
        System.out.println(afd.verify("+.25"));

        System.out.println();
        //Invalid
        System.out.println(afd.verify("-."));
        System.out.println(afd.verify("e2"));
        System.out.println(afd.verify("."));
        System.out.println(afd.verify("-.+"));
        System.out.println(afd.verify("--4"));
        System.out.println(afd.verify("7.e"));
        System.out.println(afd.verify(".2e-1.5"));
    }
}
