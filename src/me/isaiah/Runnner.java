package me.isaiah;

// V import net.http

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Runnner {

    // V pub fn comp(a string, back string) ? {
    // V   println(a)
    // V   mut j := os.temp_dir() + '/vcom/'
    // V   os.mkdir(j) or {}
    // V   os.mkdir(j + 'out') or {}
    // V   mut sza_url := 'https://isaiahpatton.github.io/7za.exe'
    // V   if !os.exists(j+"7za.exe") {
    // V     println("Downloading 7Zip, Please wait...")
    // V     http.download_file(sza_url, j+"7za.exe") or {}
    // V   } else {
    // V     mut size := os.file_size(j+"7za.exe")
    // V     if size < 587775 {
    // V       println(size)
    // V       println("Downloading 7Zip, Please wait...")
    // V       http.download_file(sza_url, j+"7za.exe") or {}
    // V     } 
    // V   }
    // V   if os.file_size(j+'v.zip') < 9000 {
    // V     println('Downloading V Compiler version 0.2.4-2021.39.1, Please wait...')
    // V     http.download_file('https://github.com/vlang/v/releases/download/weekly.2021.39.1/v_windows.zip', j+'v.zip') ?
    // V   }
    // V   os.execute(j+'7za.exe x -o' + j + 'out ' + j+'v.zip')
    // V   os.execute(j+'out/v/v.exe -cg -b ' + back + ' ' + os.resource_abs_path(a))
    // V   //mut pro := os.new_process(j+'out/v/v.exe')
    // V   //mut arrg := ['-cg', '-b', back, os.resource_abs_path(a)]
    // V   //pro.set_args(arrg)
    // V   //pro.set_redirect_stdio()
    // V   //pro.wait()
    // V   //println( pro.stderr_read() )
    // V   os.execute(j+'out/v/v.exe -w fmt ' + os.resource_abs_path(a))
    // V }

    // J2V OFF
    public static void run_v(File oF, boolean run, String backend) throws Exception {
        Runtime rt = Runtime.getRuntime();
        String home = System.getProperty("user.home");
        String[] commands = {home + "\\desktop\\v\\v.exe", "-b", backend, oF.getAbsolutePath()};
        if (run) commands = new String[]{home +"\\desktop\\v\\v.exe", "run", oF.getAbsolutePath()};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        String s = null, e = null;
        boolean l = false;
        boolean skip = false;
        while ((s = stdInput.readLine()) != null || (e = stdError.readLine()) != null) {
            if (null != s) System.out.println(s);
            if (null != e) {
                l = e.startsWith("  ") && e.contains(" |");
                if (skip && !l) {
                    if (e.contains("imported but never used")) {
                        String[] con = e.split(" ");
                        String mn = con[3];
                        if (con[4].startsWith("(")) {
                            mn = con[4].replace('(', '\'').replace(")", "");
                        }
                        System.out.println("Warning: unused module import " + mn + " in file " + con[0].split(":")[0]);
                    } else {
                        System.out.println(e);
                    }
                }
                if (e.contains("warning: module ") && e.contains("is imported but never used")) {
                    skip = true;
                }
            }
        }
    }
    // J2V ON

}