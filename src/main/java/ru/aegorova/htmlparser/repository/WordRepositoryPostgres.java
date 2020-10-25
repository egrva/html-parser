package ru.aegorova.htmlparser.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aegorova.htmlparser.HTMLParserMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WordRepositoryPostgres implements WordRepository {

    private Connection connection;

    // logger
    private static final Logger log = LoggerFactory.getLogger(HTMLParserMain.class);

    //language=SQL
    private static final String SQL_INSERT =
            "insert into words (word,num_of_occur) values (?,?);";

    public WordRepositoryPostgres(Connection connection) {
        this.connection = connection;
    }

    // save word and count of occurrences in db
    @Override
    public void save(String word, Integer count) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, word);
            statement.setInt(2, count);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

