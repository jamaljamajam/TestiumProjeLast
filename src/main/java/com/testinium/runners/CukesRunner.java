package com.testinium.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "pretty",
                    "html:target/cucumber-report.html",
                    "rerun:target/rerun.txt",
                    "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                    "json:target/cucumber.json",
                    "junit:target/junit/junit-report.xml",
            },
            features = "src/main/resources/features",
            glue = "com/testinium/step_definitions",
            dryRun = false,
            tags = "@Proje1",
            publish = true
    )

    public class CukesRunner {
    }


