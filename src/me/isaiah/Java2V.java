package me.isaiah;

import java.io.File;
import java.nio.file.Files;

public class Java2V {

    public static void main(String[] args) throws Exception {
        System.out.println(" -========================================-");
        System.out.println("  \tJ2V version 0.0.1-Oct26.  ");
        System.out.println("  \tCopyright (c) MMXXI by Isaiah. ");
        System.out.println(" -========================================-");
        System.out.println("");

        boolean debug = false;
        String backend = "c";
        debug = true; // JAVA ONLY

        String file_name = "src/"; // Test
        String main_class = "me/isaiah/Java2V.java";

        if (args.length <= 1 && !debug) {
            String exe = "";
            // V exe = os.args[0]
            System.err.println("Usage: " + exe + " <source folder> <main class name>");
            System.exit(1);
        }
        if (args.length == 3) {
            if (args[1].contains("-debug")) {
                debug = true;
            }
            if (args[1].contains("-js")) {
                backend = "js";
            }
        }
        if (args.length > 0) {
            file_name = args[0];
            main_class = args[1];
        }

        File fi = new File(file_name);
        File mc = new File(main_class.replace(".java", ".v"));

        for (File f : fi.listFiles()) {
            FileParser.do_file(f); // JAVA ONLY
            // V do_file(f)
        }
        System.out.println("Transpiled files from Java to V complete.\n");

        System.out.println("Generating bootstrap for " + mc.getName() + "...");
        String bs_cn = main_class.replace(".java", "").replace("/", ".");
        File bootstrap = new File(bs_cn + ".v");
        String[] name_s = main_class.split("/");
        String name = name_s[name_s.length - 2];
        String cn = name_s[name_s.length - 1].replace(".java", "");
        String namen = name_s[0];
        for (int i = 1; i < name_s.length - 1; i ++) {
            namen = namen + "/" + name_s[i];
        }

        String cont = "module main\n\n"
                + "import " + namen.replace('/','.') + "\n\n"
                + "fn main() {\n\t" + name + "." + cn.toLowerCase() + "_main()\n}\n";

        Files.write(bootstrap.toPath(), cont.getBytes());

        System.out.println("Compiling V files with " + backend.toUpperCase() + " backend...");
        System.out.println("Running \"v.exe -b " + backend + " " + bootstrap.getName() + "\" ...\n");

        Runnner.run_v(bootstrap,false,backend); // JAVA ONLY

        // V comp(bootstrap.get_name(), backend) or { println(err) }
        bootstrap.delete();
    }

}