package com.l2lhackathon.peers.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class CleanupDataBaseAfterEachTestExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        var dataSource = SpringExtension.getApplicationContext(context).getBean("dataSource", DataSource.class);
        truncateTables(dataSource);
    }

    @SneakyThrows
    private void truncateTables(DataSource dataSource) {
        var scriptName = "cleanup.sql";
        ResourceDatabasePopulator scriptLauncher = new ResourceDatabasePopulator();
        var truncateScript = new ClassPathResource(scriptName);
        scriptLauncher.addScript(truncateScript);
        var reader = new BufferedReader(new InputStreamReader(truncateScript.getInputStream()));
        scriptLauncher.execute(dataSource);
    }

}
