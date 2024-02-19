package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {

    private final DAOFactory daoFactory;
    private final static String QUERY_REGISTER = "INSERT INTO registration (studentid, termid, crn) VALUES (?,?,?)";
    private final static String QUERY_DROP = "DELETE FROM registration WHERE studentid=? AND termid=? AND crn=?";
    private final static String QUERY_WITHDRAW = "DELETE FROM registration WHERE studentid=? AND termid=?";
    private final static String QUERY_LIST = "SELECT * FROM registration WHERE studentid=? AND termid=?";

    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean create(int studentid, int termid, int crn) {

        boolean result = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_REGISTER);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                int res = ps.executeUpdate();
                if (res > 0) result = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public boolean delete(int studentid, int termid, int crn) {

        boolean result = false;

        PreparedStatement ps = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_DROP);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);

                int res = ps.executeUpdate();
                if (res > 0) result = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public boolean delete(int studentid, int termid) {

        boolean result = false;

        PreparedStatement ps = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_WITHDRAW);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                int res = ps.executeUpdate();
                if (res > 0) result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

    public String list(int studentid, int termid) {

        String result = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_LIST);

                ps.setInt(1, studentid);
                ps.setInt(2, termid);

                result = DAOUtility.getResultSetAsJson(ps.executeQuery());

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return result;

    }

}