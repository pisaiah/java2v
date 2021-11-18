package me.isaiah;

public class Snakecase {

    /**
     * V requires all functions to be
     * in snake case and not carmel.
     * 
     * carmelCase -> snake_case
     */
    public static String to_snake(String inp) {
        String ll = inp;

        ll = ll.replace("S"+"nakecase.", "");
        ll = ll.replace("get"+"Bytes()", "bytes()");
        ll = ll.replace("sub" + "string", "substr");
        ll = ll.replace("lastIndex"+"Of", "last_index_of");
        ll = ll.replace("StringLastIndex"+"Of.", "util.");

        if (ll.contains("getDecoder"+"()")) {
            ll = ll.replace("getDecoder"+"()", "get_decoder()");
        }
        if (ll.contains("getEncoder"+"()")) {
            ll = ll.replace("getEncoder"+"()", "get_encoder()");
        }
        ll = ll.replace("get"+"Name", "get_name");
        ll = ll.replace("list"+"Files", "list_files");
        ll = ll.replace("getAbsolute"+"Path", "get_absolute_path");

        if (ll.contains("." + "toPath()")) {
            ll = ll.replace(".to" + "Path()", ".to_path()");
        }

        if (ll.contains("UUID.randomUUID"+"()")) {
            ll = ll.replace("UUID.", "util.").replace("randomUUID", "random_uuid");
        }
        if (ll.contains("is"+"Directory()")) {
            ll = ll.replace("isDirectory"+"()", "is_directory()");
        }

        if (ll.contains(".e"+"ndsWith")) {
            ll = ll.replace("endsWith", "ends_with");
        }

        if (ll.contains(".s" + "tartsWith")) {
            ll = ll.replace("startsWith", "starts_with");
        }

        if (ll.contains(".r" + "eplaceFirst")) {
            ll = ll.replace("replaceFirst", "replace_once");
        }

        ll = ll.replace("File" + ".separator", "io.file_separator");
        ll = ll.replace("File" + ".pathSeparator", "io.path_separator");

        ll = ll.replace(".s"+"ize()", ".len");
        
        if (ll.contains("." + "toLowerCase()")) {
            ll = ll.replace("toLowerCase", "to_lower");
        }

        if (ll.contains("." + "toUpperCase()")) {
            ll = ll.replace("toUpperCase","to_upper");
        }

        if (ll.contains("." + "getAbsolutePath()")) {
            ll = ll.replace("get"+"AbsolutePath","get_absolute_path");
        }

        return ll;
    }

}