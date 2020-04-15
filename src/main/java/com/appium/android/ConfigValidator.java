package com.appium.android;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.cli.*;
import webalertmisc.PageConfiguration;

import java.io.*;
import java.util.*;

import static webalertmisc.Utility.getConfigFile;
import static webalertmisc.Utility.jsonFileToMap;

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



    private static CommandLine parseCmdInput(String[] args) {
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

    private static void setup() {
        // setup configFile option
        Option configFileOpt = new Option("configFile", true, "Path to the configuration file");
        configFileOpt.setRequired(true);
        configFileOpt.setLongOpt("configFile");

        cliOpts = new Options();
        cliOpts.addOption(configFileOpt);
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

    private static void validatePageConfigs(File configFile) {
        Map<?,?> jsonConfigFileMap = jsonFileToMap(configFile);
        validateConfigFile(jsonConfigFileMap);

        // validate every page configuration in the json config file
        Gson gson = new Gson();
        ArrayList<LinkedTreeMap<?, ?>> jsonConfigFileConfigs = (ArrayList<LinkedTreeMap<?,?>>)jsonConfigFileMap.get("pages");
        for(LinkedTreeMap<?, ?> pageConfLTM : jsonConfigFileConfigs) {
            String pageConfigJsonStr = gson.toJson(pageConfLTM);
            PageConfiguration pageConf = gson.fromJson(pageConfigJsonStr, PageConfiguration.class);

            validatePageConfig(pageConf);
        }
    }

    private static void validationResults() {
        System.out.println("=== Validation Results ===");
        String RED_FAILING = "\u001B[31m";
        String AQUA_PASSING = "\u001B[36m";

        if(!noRepeatedNames) {
            System.out.print(RED_FAILING + "[ \u2717 ] ");
        }
        else {
            System.out.print(AQUA_PASSING + "[ \u2713 ] ");
        }
        System.out.println("No repeated configuration names.");

        if(!noRepeatedAddresses) {
            System.out.print(RED_FAILING + "[ \u2717 ] ");
        }
        else {
            System.out.print(AQUA_PASSING + "[ \u2713 ] ");
        }
        System.out.println("No repeated addresses.");

    }

    public static void main(String[] args) {
        setup();

        cmdline = parseCmdInput(args);

        String pathToConfigFile = cmdline.getOptionValue("configFile");
        File configFile = getConfigFile(pathToConfigFile);

        validatePageConfigs(configFile);

        validationResults();
    }
}
