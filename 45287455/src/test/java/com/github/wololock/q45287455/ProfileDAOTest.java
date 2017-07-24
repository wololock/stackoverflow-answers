package com.github.wololock.q45287455;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V9_6;

public class ProfileDAOTest {

    @Test
    public void testFindAllInRangeDAOQuery() throws IOException, LiquibaseException {
        final EmbeddedPostgres postgres = new EmbeddedPostgres(V9_6);
        final String url = postgres.start("localhost", 35432, "test", "postgres", "postgres");
        final DBI dbi = new DBI(url);
        final JdbcConnection jdbcConnection = new JdbcConnection(dbi.open().getConnection());
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

        final Liquibase liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update("test");

        ProfileDAO dao = dbi.open(ProfileDAO.class);
        
        assertThat(dao.findAllInRange(0,2)).hasSize(1);
        assertThat(dao.findAllInRange(0,4)).hasSize(2);

        dao.close();
        postgres.stop();
    }
}
