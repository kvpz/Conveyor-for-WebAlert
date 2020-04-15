package com.appium.android;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.cli.*;
import webalertmisc.PageConfiguration;

import java.io.*;
import java.util.*;

/**
 * A config file is valid if it passes the following criteria:
 * - the supplied path to the config file is valid
 * - there must be no repeated page config name
 */
public class ConfigValidator {

    private static Options cliOpts;
    private static CommandLine cmdline;
    private static Set<String> configNamesMapForTesting = new TreeSet<String>();
    private static Set<String> addressesMapForTesting = new TreeSet<String>();
    private static boolean noRepeatedNames = true;
    private static boolean noRepeatedAddresses = true;

    public static File getConfigFile(String filePath) {
        File cfile = new File(filePath);
        if(!cfile.exists()) {
            System.out.println("'" + filePath + "'" + " does not identify an existing file.");
            System.exit(-1);
        }

        return cfile;
    }

    private static void validateConfigFile(Map<?,?> jsonMap) {
        if(! jsonMap.containsKey("pages")) {
            System.out.println("There is no key 'pages' in the config file.");
            System.exit(-1);
        }
    }

    private static void validatePageConfig(PageConfiguration conf) {
        if(configNamesMapForTesting.contains(conf.getName())) {
            System.out.println("Error: found duplicate config name '" + conf.getName() + "'");
            noRepeatedNames = false;
        }

        if(addressesMapForTesting.contains(conf.getAddress())) {
            System.out.println("Error: found duplicate address '" + conf.getAddress() + "'");
            noRepeatedAddresses = false;
        }

        configNamesMapForTesting.add(conf.getName());
        addressesMapForTesting.add(conf.getAddress());
    }

    public static Map<?,?> jsonFileToMap(File configFile) {
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader(configFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Map<?,?> map = gson.fromJson(reader, Map.class);

        validateConfigFile(map);

        return map;
    }

    public static void setup() {
        // setup configFile option
        Option configFileOpt = new Option("configFile", true, "Path to the configuration file");
        configFileOpt.setRequired(true);
        configFileOpt.setLongOpt("configFile");

        cliOpts = new Options();
        cliOpts.addOption(configFileOpt);
    }

    public static CommandLine parseCmdInput(String[] args) {
        CommandLine cmd = null;

        try {
            cmd = new DefaultParser().parse(cliOpts, args);
        }
        catch(ParseException e) {
            System.out.println("Issue parsing CLI options");
            System.out.println(e);
            System.exit(-1);
        }

        return cmd;
    }

    public static void validationResults() {
        System.out.println("=== Validation Results ===");

        if(!noRepeatedNames) {
            System.out.print("\u001B[31m [ \u2717 ] ");
            System.out.println("No repeated configuration names.");
        }
        else {
            System.out.print("\u001B[36m [ \u2713 ] ");
            System.out.println("No repeated configuration names.");
        }

        if(!noRepeatedAddresses) {
            System.out.print("\u001B[31m [ \u2717 ] ");
            System.out.println("No repeated addresses.");
        }
        else {
            System.out.print("\u001B[36m [ \u2713 ] ");
            System.out.println("No repeated addresses.");
        }
    }

    public static void main(String[] args) {
        setup();

        cmdline = parseCmdInput(args);

        String pathToConfigFile = cmdline.getOptionValue("configFile");
        File configFile = getConfigFile(pathToConfigFile);

        Map<?,?> jsonRoot = jsonFileToMap(configFile);

        Gson gson = new Gson();
        for(LinkedTreeMap<?,?> pageConfigMap : (ArrayList<LinkedTreeMap<?,?>>)jsonRoot.get("pages")) {
            String pageConfigJsonStr = gson.toJson(pageConfigMap);
            PageConfiguration pageConf = gson.fromJson(pageConfigJsonStr, PageConfiguration.class);

            validatePageConfig(pageConf);
        }

        validationResults();
    }
}
