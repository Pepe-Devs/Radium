package com.pepedevs.corelib.database.sql;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLConsumer<T> {

    void accept(T t) throws SQLException;
}
