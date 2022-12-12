package com.TugasAkhir.spring.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class InvoiceCodeGenerator implements IdentifierGenerator {

@Override
public Serializable generate(SharedSessionContractImplementor  session, Object object)
        throws HibernateException {

    String prefix = "BILL-";
    Connection connection = session.connection();

    try {
        Statement statement=connection.createStatement();

        ResultSet rs=statement.executeQuery("select count(code) as Code from invoice");

        if(rs.next())
        {
            int id=rs.getInt(1)+1;
            String generatedId = prefix + String.valueOf(id);
            return generatedId;
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return null;
}

}
