package me.isaiah;

public class Systemreplace {

    public static String system_repl(String inp) {
        String ll = inp;
        String t = ll.trim();
        ll = ll.replace("Systemreplace.", "");

        if (t.contains("S" + "ystem.out.print")) {
            ll = ll.replace("S" + "ystem.out.print", "print").replace(";", "");
        }
        if (t.contains("S" + "ystem.out.println")) {
            ll = ll.replace("S" + ("ystem.out.printlN".toLowerCase()), "println").replace(";", "");
        }
        if (t.contains("S" + "ystem.err.print")) {
            ll = ll.replace("S" + "ystem.err.print", "eprint").replace(";", "");
        }
        if (t.contains("S" + "ystem.err.println")) {
            ll = ll.replace("S" + "ystem.err.printLn", "eprintln").replace(";", "");
        }
        if (t.contains("S" + "ystem.exit(")) {
            ll = ll.replace("S" + "ystem.exit(", "exit(").replace(";", "");
        }
        return ll;
    }

}