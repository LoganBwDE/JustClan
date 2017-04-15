package me.loganbwde.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import me.loganbwde.Clan.main;

import java.sql.Connection;

public class MySQL
{
    private String host;
    private int port;
    private String user;
    private String password;
    private String database;
    private main m;
    private Connection connection;
    private Statement statement;

    public MySQL(main main)
    {
        m = main;
        m.getFileManager().getDatabase().addDefault("Database." + ".Host", "localhost");
        m.getFileManager().getDatabase().addDefault("Database." + ".Port", 3306);
        m.getFileManager().getDatabase().addDefault("Database." + ".User", "user");
        m.getFileManager().getDatabase().addDefault("Database." + ".Password", "password");
        m.getFileManager().getDatabase().addDefault("Database." + ".Database", "database");

        m.getFileManager().saveDatabase();
        m.getFileManager().loadFiles();

        host = m.getFileManager().getDatabase().getString("Database." + ".Host");
        port = m.getFileManager().getDatabase().getInt("Database." + ".Port");
        user = m.getFileManager().getDatabase().getString("Database." + ".User");
        password = m.getFileManager().getDatabase().getString("Database." + ".Password");
        database = m.getFileManager().getDatabase().getString("Database." + ".Database");
    }

    public void openConnection() throws SQLException, ClassNotFoundException
    {
        try
        {
            if (connection != null && !connection.isClosed())
            {
                return;
            }

            synchronized (this)
            {
                if (connection != null && !connection.isClosed())
                {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            // Ausgabe: MySQL NICHT EINGETRAGEN
        }
    }

    public Statement getConnection()
    {
        try
        {
            openConnection();
            statement = connection.createStatement();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return statement;
    }

    /*
     * public Connection getConnection() { if(connection != null) {
     * closeConnection(); } try { openConnection(); } catch
     * (ClassNotFoundException e) { e.printStackTrace(); } catch (SQLException
     * e) { e.printStackTrace(); } return connection; }
     * 
     * public void closeResources(ResultSet rs, PreparedStatement st) { if(rs !=
     * null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace();
     * } }
     * 
     * if(st != null) { try { st.close(); } catch (SQLException e) {
     * e.printStackTrace(); } } }
     * 
     * public void closeConnection() { try { connection.close(); } catch
     * (SQLException e) { e.printStackTrace(); } connection = null; }
     * 
     * public void queryUpdate(String query) { Connection con = getConnection();
     * PreparedStatement st = null; try { st = con.prepareStatement(query); }
     * catch (SQLException e) { e.printStackTrace(); } try { st.executeUpdate();
     * } catch (SQLException e) { e.printStackTrace(); } finally {
     * closeResources(null, st); } }
     * 
     * public Connection openConnection() { try {
     * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
     * e1) { e1.printStackTrace(); } try { con = (Connection)
     * DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" +
     * database,user,password); con.setAutoReconnect(true); } catch(SQLException
     * e) { e.printStackTrace(); } return null; }
     * 
     * public boolean hasConnection() { try { return con != null ||
     * con.isValid(1); } catch (SQLException e) { e.printStackTrace(); return
     * false; } }
     * 
     * }
     */
}
