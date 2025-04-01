package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionSQLite {
    private static final DataSource dataSource;
    private static final String URL = "jbdc:sqlite:banco.db";

    // Bloque estático para configurar HikariCP
    // El bloque estatic se ejecuta una vez al cargar la clase
    static {
        HikariConfig config = new HikariConfig();
        //Config con mysql

        //Config con sqlite
        config.setJdbcUrl(URL);

        // Configuración de rendimiento
        config.setMaximumPoolSize(10);   // Máximo de conexiones en el pool
        config.setMinimumIdle(2);        // Mínimo de conexiones inactivas
        config.setIdleTimeout(30000);    // Tiempo antes de cerrar conexiones inactivas (30 seg)
        config.setConnectionTimeout(10000); // Timeout para obtener conexión (10 seg)
        config.setLeakDetectionThreshold(5000); // Detección de fugas de conexiones (5 seg)

        dataSource = new HikariDataSource(config);
    }

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
