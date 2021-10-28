package me.isaiah;

public class FunctionParser {

    /**
     * V requires all functions to be
     * in snake case and not carmel.
     * 
     * carmelCase -> snake_case
     */
    public static String parse_function(String inp) {
        String ll = inp;

        ll = ll.replace("FunctionParser"+".", "");
        String t = ll.trim();

        //if (t.startsWith("public " + class_name + "(")) {
        //    ll = ll.replace("public " + class_name, "pub fn " + class_name.toLowerCase());
        //}

        if (t.startsWith("public void")) {
            ll = ll.replace("public VOID".toLowerCase(), "pub fn");
            if (ll.contains("(")) {
                String lh = ll.replace('(', '\n').replace(')', '\n').split("\n")[1];
                String nh = "";
                if (lh.length() > 1) {
                    String[] ddd = lh.split(", ");
                    int z = 0;
                    for (String s : ddd) {
                        String[] sp = s.split(" ");
                        String zer = sp[0].replace("String", "string");
                        if (z == (ddd.length - 1)) {
                            nh += sp[1] + " " + zer;
                        } else {
                            nh += sp[1] + " " + zer + ", ";
                        }
                        z++;
                    }
                    ll = ll.replace(lh, nh);
                }
            }
            //ll = ll.replace("pub fn", "pub fn (this " + class_name.toLowerCase() + ")");
        }

        if (t.startsWith("public static void") || t.startsWith("public static String") || t.startsWith("public static boolean")) {
            ll = ll.replace("public static VOID".toLowerCase(), "pub fn");
            if (ll.startsWith("public static String")) {
                ll = ll.replace("public static String", "pub fn").replace(" {", " string {");
            }
            ll = ll.replace("public static boolean".toLowerCase(), "fn");
            if (ll.contains("(")) {
                String lh = ll.replace('(', '\n').replace(')', '\n').split("\n")[1];
                String nh = "";
                if (lh.length() > 1) {
                    String[] ddd = lh.split(", ");
                    int z = 0;
                    for (String s : ddd) {
                        String[] sp = s.split(" ");
                        String zer = sp[0].replace("String", "string").replace("File", "io.File");
                        if (zer.endsWith("[]")) {
                            zer = "[]" + zer.replace("[]", "");
                        }
                        if (z == (ddd.length - 1)) {
                            nh += sp[1] + " " + zer;
                        } else {
                            nh += sp[1] + " " + zer + ", ";
                        }
                        z++;
                    }
                    ll = ll.replace(lh, nh);
                }
            }
        }


        return ll;
    }

}