package br.com.imt.dao

import br.com.imt.models.Games
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
 interface IBaseDAO<T> {
     fun insert(obj: T);
     fun update(obj: T);
     fun delete(id: Int);
     fun get(id: Int): T;
     fun getAll(): List<T>;
}