package me.isaiah;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static void do_file(File f) throws IOException {
        List<String> arr = new ArrayList<>();
        List<String> imports = new ArrayList<>();
        String pkg = "";
        boolean debug = false;
        if (!f.isDirectory()) {
            arr.clear();
            imports.clear();

            List<String> l = Files.readAllLines(f.toPath());

            String cls = "";
            int ls = l.size();
            int lc = 0;
            String fnn = "";

            boolean ppause = false;
            for (String ll : l) {
                if (ll.contains("//" + " J2V OFF")) {
                    ppause = true;
                }
                if (ll.contains("//" + " J2V ON")) {
                    ppause = false;
                }
                if (ll.endsWith("// JAVA ONLY")) {
                    lc++;
                    continue;
                }
                if (ll.contains("//" + " V") || ppause || ll.contains("//" + " J2V ON")) { 
                    if (ll.contains("//" + " V")) {
                        ll = ll.replace("//" + " V ", "").replace("    ", "\t");
                        cls = cls + ll + "\n";
                    }
                    lc++;
                } else {

                    if (ll.startsWith("package ")) {
                        pkg = ll.replace("package ", "module ").replace(";", "");
                        ll = pkg.replace('.', '_') + "\n\nimport os\n// D"+"EBUG: START OF IMPORTS";
                        //ll = "module main\n\nimport os\n// D"+"EBUG: START OF IMPORTS";
                    }

                    if (ll.startsWith("import ")) {
                        if (ll.startsWith("import java.io.File;")) {
                            if (!imports.contains("import jcl.io")) {
                                ll = "import jcl.io";
                                imports.add(ll);
                            }
                        }
                        if (ll.startsWith("import java.nio.file.Files;")) {
                            if (!imports.contains("import jcl.nio")) {
                                ll = "import jcl.nio";
                                imports.add(ll);
                            }
                        }
                        if (ll.startsWith("import java.util.zip.ZipFile;")) {
                            if (!imports.contains("import jcl.util.zip")) {
                                ll = "import jcl.util.zip";
                                imports.add(ll);
                            }
                        }
                        if (ll.startsWith("import java.util.Base64;")) {
                            if (!imports.contains("import jcl.util")) {
                                ll = "import jcl.util";
                                imports.add(ll);
                            }
                        }
                        if (ll.startsWith("import java.util.UUID;")) {
                            if (!imports.contains("import jcl.util")) {
                                ll = "import jcl.util";
                                imports.add(ll);
                            }
                        }

                        if (ll.startsWith("import java.")) {
                            ll = "";
                        } else {
                            imports.add(ll);
                        }
                    }

                    if (ll.contains("Files." + "readAllLines(")) {
                        ll = ll.replace("Files." + "readAllLines", "nio.read_all_lines");
                    }

                    if (lc == ls-1) {
                        ll = "";
                    }

                    if (ll.contains(" throws ")) {
                        ll = ll.replace(" throws", "").replace(" IOException", "").replace(" Exception","");
                    }

                    if (ll.startsWith("public class")) {
                        fnn = ll.split(" ")[2];
                        ll = "// " + ll.split(" ")[2] + ".v ";
                        //ll = "// " + ll.split(" ")[2] + ".v\nstruct " + fnn.toLowerCase() + " {\n}";
                    }
                    ll = ll.replaceFirst("    ", "");

                    if (ll.trim().startsWith("public static void main(String["+"] args)")) {
                        String method_name = "pub fn " + fnn.toLowerCase() + "_main()";

                        ll = ll.replace("public static void main(String[] args)", method_name) + "\n\tmut args := []string{}\n"
                                + "\tfor i in 1 .. os.args.length {\n"
                                + "\t    args << os.args[i]\n"
                                + "\t}";
                    }

                    String t = ll.trim();
                    ll = Systemreplace.system_repl(ll);

                    String[] strs = {"String ", "int ", "File ", "boolean ", "ZipFile ", "UUID ", "byte ", "Object "};
                    for (String st : strs) {
                        if (t.startsWith(st) && t.split(" ")[2].contains("=")) {
                            ll = ll.replaceFirst("=", ":=").replace(st, "mut ").replace(";","");
                        }
                    }

                    if (ll.trim().split(" ").length > 3) {
                        if (ll.trim().split(" ")[3].contains("new")) {
                            String cl = ll.trim().split(" ")[4].split("[(]")[0];
                            String pack = "";
                            if (cl.contains("ZipFile")) {
                                pack = "zip.";
                            } else if (cl.contains("File")) {
                                pack = "io.";
                            }

                            ll = ll.replace(ll.trim().split(" ")[3] + " " + cl, pack + cl.toLowerCase());
                        }
                    }

                    if (ll.endsWith(";")) {
                        // J2V OFF
                        ll = ll.replace("\";\"", "aE-Ea").replace(";", "").replace("aE-Ea", "\";\"");
                        // J2V ON
                        // V ll = ll.replace('";"', 'aE-Ea').replace(';','').replace('aE-Ea','";"')
                    }

                    if (t.startsWith("for (")) {
                        ll = ll.replace("for (","for ").replace(") {"," {").replaceFirst("int ","");
                        if (ll.trim().split(" ")[2].contains("=")) {
                            ll = ll.replaceFirst("=", ":=");
                        }
                        t = ll.trim();
                    }

                    if (t.startsWith("if (") || t.startsWith("} else if (")) {
                        String fixt = ll.replace("if (", "if ").replace(") {", " {");
                        ll = fixt;

                        ll.replaceFirst("[(]","").replaceFirst("[)]","");
                        t = ll.trim();
                    }

                    if (t.startsWith("for ") && t.split(" ")[3].contains(":")) {
                        ll = ll.replaceFirst( t.split(" ")[1] + " ", "mut ").replaceFirst(" : ", " in ");
                    }

                    if (t.split(" ")[0].endsWith("[]")) {
                        String varname = t.split(" ")[1];
                        arr.add(varname);
        
                        ll = ll.replace( t.split(" ")[0] + " ", "mut ").replaceFirst(" = ", " := ").replace('{', '[').replace('}', ']');
                    }

                    for (String s : arr) {
                        if (t.contains(s.toString() + ".add(")) {
                            ll = ll.replace(s.toString() + ".add(", s.toString() + " << (");
                        }
                    }

                    ll = ll.replace(".Length()".toLowerCase(), ".len");
                    ll = ll.replace(".Length".toLowerCase(), ".len");
                    ll = ll.replace("@D" + "eprecated", "[deprecated]");
                    
                    ll = FunctionParser.parse_function(ll);

                    if (t.startsWith("List<")) {
                        String st = t.split(" ")[0];
                        ll = ll.replace(st + " ", "mut ").replace(" = ", " := ");
                        String name = t.split(" ")[1];
                        arr.add(name);
                    }

                    if (ll.contains(" a" + "rraylist<>()")) {
                        String name = t.split(" ")[1];
                        arr.add(name);
                        ll = ll.replace("a" + "rraylist<>()", "[]string{}");
                    }

                    ll = ll.replace("trim"+"()", "trim(' ')");
                    ll = ll.replace("to"+"String", "str");

                    if (t.startsWith("Files.write")) {
                        ll = ll.replace("Files.write", "nio.write");
                    }
                    
                    ll = Snakecase.to_snake(ll);

                    if (ll.contains("Base64"+".")) {
                        ll = ll.replace("Base64"+".", "util.");
                    }

                    if (ll.contains("System"+".getProperty")) {
                        ll = ll.replace("System."+"getProperty(", "util.get_system_property(");
                        if (!imports.contains("import jcl.util")) {
                            cls = cls.replace("// DEBUG: START OF IMPORTS", "// DEBUG: START OF IMPORTS\nimport jcl.util");
                            imports.add("import jcl.util");
                        }
                    }

                    lc++;
                    ll = ll.replace("    ", "\t");
                    cls = cls + ll + "\n";
                }
            }
            cls = cls.replace("// DEBUG:"+" START OF IMPORTS", "").replace("    ", "\t");

            if (debug) {
                System.out.println(cls);
            }

            pkg = pkg.replace("module ", "").replace('.', '/');

            String[] pll = pkg.replace(";", "").split("/");
            String ful = new File(".").getAbsolutePath();
            String nful = "";
            for (String strr : pll) {
                ful = ful + "/" + strr;
                nful = nful + "/" + strr;
                File outdir = new File(ful);
                outdir.mkdir();
            }

            File o_f = new File(ful + "/" + fnn + ".v");
            Files.write(o_f.toPath(), cls.getBytes());

            System.out.println("Transpiled to: " + o_f.getAbsolutePath() + "/" + fnn + ".v");
        } else {
            for (File fi : f.listFiles()) {
                do_file(fi);
            }
        }
        
    }

}
