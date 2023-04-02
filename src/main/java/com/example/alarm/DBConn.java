package com.example.alarm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DBConn {

    Connection conn;
    public DBConn()
    {
        try {

            // 1) Register the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            Database_connection db=new Database_connection();
             conn=db.conn;

        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        } catch (ClassNotFoundException e) {
            System.out.println(" Failed to register driver. Exception code : " + e);
        }
    }

    public void close()
    {
        try
        {
            ((java.sql.Connection) conn).close();
        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
    }

    public void insertvalues(String mname, int doses, String uneet, String type, int reps, String rmtime, String stardet, String endet, String weekde, int repeet) throws ParseException
    {
        CurrentTime ctime = new CurrentTime(stardet);
        String[] instance = ctime.getNextTime((int) (24/reps), rmtime);
        try {

            Statement stmt = ((java.sql.Connection) conn).createStatement();
            int taken = 0;
            String query = "Insert into demo values('"+currentUser.user_name+"','"+mname+"','"+doses+"','"+uneet+"', '"+type+"', '"+reps+"','"+rmtime+"', '"+instance[3]+"', '"+stardet+"', '"+endet+"', '"+weekde+"', '"+repeet+"','"+taken+"')";
            int a = stmt.executeUpdate(query);
            if (a > 0) {
                System.out.println("Data is inserted");
            } else {
                System.out.println("Insertion failed");
            }
            stmt.close();
            //((java.sql.Connection) conn).close();


        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
    }

    private ObservableList<demoinfo> list = FXCollections.observableArrayList();
    public ObservableList<demoinfo> getTable()
    {
        //ObservableList<demoinfo> list = FXCollections.observableArrayList();
        try
        {
            PreparedStatement ps = conn.prepareStatement("select * from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                list.add(new demoinfo(rs.getString("med_name"), rs.getString("remtime"), rs.getInt("doses")));
            }



        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return list;

    }

    public ObservableList<demoinfo> getuniqqTable()
    {
        //ObservableList<demoinfo> list = FXCollections.observableArrayList();
        try
        {
            PreparedStatement ps = conn.prepareStatement("select distinct med_name, remtime, doses from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                list.add(new demoinfo(rs.getString("med_name"), rs.getString("remtime"), rs.getInt("doses")));
            }


        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return list;

    }


    public demoinfo[] forDisplay()
    {
        demoinfo[] llst = new demoinfo[list.size()];
        try
        {
            PreparedStatement ps = conn.prepareStatement("select distinct med_name, remtime, doses from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();
            int i = 0;

            while(rs.next())
            {
                llst[i] = new demoinfo(rs.getString("med_name"), rs.getString("remtime"), rs.getInt("doses"));
                //list.add(new demoinfo(rs.getString("med_name"), rs.getString("remtime"), rs.getInt("doses")));
                i++;
            }



        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return llst;
    }

    public HashMap<String, Boolean> getAlarms()
    {
        HashMap<String, Boolean> ans = new HashMap<String, Boolean>();

        try
        {
            PreparedStatement ps = conn.prepareStatement("select remtime from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                ans.put(rs.getString("remtime"), true);
            }

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return ans;
    }

    public HashMap<Pair<String, String>, Boolean> getTimes()
    {
        //HashMap<String, HashMap<String, Boolean>> timeandweek = new HashMap<String, HashMap<String, Boolean>>();
        HashMap<Pair<String, String>, Boolean> timeandweek = new HashMap<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement("select remtime, weekday from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Pair<String, String> temp = new Pair<String, String>(rs.getString("remtime"), rs.getString("weekday"));
                timeandweek.put(temp, Boolean.TRUE);

            }

            Iterator it = timeandweek.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry mpel = (Map.Entry)it.next();
                System.out.println(mpel.getKey() + " " + mpel.getValue());
            }

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return timeandweek;

    }
    public int doss()
    {
        int cnt = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("select doses from demo where username = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                cnt += rs.getInt("doses");
            }

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
        return cnt;
    }

    public String updatenextTimes(String mname, String almtime, String weekde)
    {
        String[] ans = new String[5];

        try
        {
            PreparedStatement ps = conn.prepareStatement("select repeatation, nextTime  from demo where med_name = ? AND remtime = ? AND weekday = ? AND username = ?");
            ps.setString(1, mname);
            ps.setString(2, almtime);
            ps.setString(3, weekde);
            ps.setString(4, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            CurrentTime tm = new CurrentTime();
            String temp = new String();

            while(rs.next()) {
                System.out.println(rs.getInt("repeatation") + " " + rs.getString("nextTime"));
                temp = rs.getString("nextTime");
                ans = tm.getNextTime((int) 24 / rs.getInt("repeatation"), rs.getString("nextTime"));
                System.out.println("My answer is" + ans[3]);
            }

            ps = conn.prepareStatement("update demo set remtime = ?, nextTime = ? where med_name = ? AND weekday = ? AND username = ?");
            ps.setString(1, temp);
            ps.setString(2, ans[3]);
            ps.setString(3, mname);
            ps.setString(4, weekde);
            ps.setString(5, currentUser.user_name);

            ps.executeUpdate();

            //System.out.println(ans[3]);

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
        return ans[3];
    }

    public void removeExpDates(String str)
    {
        try
        {
            Statement stmt = conn.createStatement();
            String quer = "delete from demo where endDate = '"+str+"' AND username = '"+currentUser.user_name+"'";

            int a = stmt.executeUpdate(quer);
            if (a > 0) {
                System.out.println("Data is deleted");
            } else {
                System.out.println("Deletion failed");
            }
            stmt.close();

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
    }

    public String[] getWeeks(String mname, String almtime, int ds)
    {
        String[] arr = new String[7];
        try {
            PreparedStatement ps = conn.prepareStatement("select weekday from demo where med_name = ? AND remtime = ? AND doses = ? AND username = ?");

            ps.setString(1, mname);
            ps.setString(2, almtime);
            ps.setString(3, String.valueOf(ds));
            ps.setString(4, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while(rs.next())
            {
                arr[i] = rs.getString("weekday");
                i++;
            }

        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        return arr;
    }

    public void deleteItem(String mname, String dos, String rmtime)
    {
        try
        {
            Statement stmt = ((java.sql.Connection) conn).createStatement();
            int i = Integer.parseInt(dos);
            String quer = "delete from demo where med_name = '"+mname+"' AND doses = "+dos+" AND remtime = '"+rmtime+"' AND username = '"+currentUser.user_name+"'";

            int a = stmt.executeUpdate(quer);
            if (a > 0) {
                System.out.println("Data is deleted");
            } else {
                System.out.println("Deletion failed");
            }
            stmt.close();

        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
    }

    public void setTaken(String mname, String rmtime, String dos, String weekde)
    {
        try {
            PreparedStatement ps = conn.prepareStatement("update demo set taken = ? where med_name = ? AND remtime = ? AND doses = ? AND weekday = ? AND username = ?");

            int tkn = 1;
            ps.setInt(1, tkn);
            ps.setString(2, mname);
            ps.setString(3, rmtime);
            ps.setString(4, dos);
            ps.setString(5, weekde);
            ps.setString(6, currentUser.user_name);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }
    }



}