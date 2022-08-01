package com.jrv.springbootandjavaconcepts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * To retrieve the dynamically stored values from database.
 * The values are stored in table as a key-value pair by developer at the time of development.
 * This can be used where a value is hardcoded in the logic, but may change often. E.g. Version Number, Passwords, etc.
 * This way of storing the data may help to quickly change that value without any code changes.
 * This also helps to hide sensitive data (like passwords) from the Code repository.
 *
 * This can be used anywhere in the Project by Auto-wiring this class and calling the method getValue() with the key as parameter.
 *
 * @author Jayarathinavel
 * @since 01/Aug/2022
 */
public interface Variables extends JpaRepository<VariableEntity, String> {

    /**
     *
     * To fetch the value of a variable stored in database table by providing key of the variable.
     *
     * @param key Key name to fetch its value
     * @return Value of the key mentioned in the parameter
     */
    @Query(value = "SELECT value FROM variables WHERE key = ?1", nativeQuery = true)
    String getValue(String key);
}
