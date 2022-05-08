package Chatapp;
import java.util.ArrayList;
import java.sql.*;

public class Chatroom {
    private int id;
    private boolean isgroup;
    private String last_seen;
    ArrayList<User> users;
    ArrayList<Message> messages;
    private String cr_desc;

    public Chatroom() {

    }
    public Chatroom(int id, boolean isgroup, String last_seen, String cr_desc) {
        this.id = id;
        this.isgroup = isgroup;
        this.last_seen = last_seen;
        this.cr_desc = cr_desc;
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIsgroup(boolean isgroup) {
        this.isgroup = isgroup;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public void setCr_desc(String cr_desc) {
        this.cr_desc = cr_desc;
    }
//getters

    public int getId() {
        return id;
    }

    public  boolean isGroup() {
        boolean is_true = false ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");
            Statement statement = connection.createStatement();
            String query = "select is_group from chatroom where id = "+id;

            ResultSet r = statement.executeQuery("select is_group from chatroom where id = "+id);
            r.next();

            if(r.getString("is_group").equals("1") )

            {

                is_true=true ;
                System.out.println("This chat room is group");
                return is_true;
            }
            else if( r.getString("is_group").equals("0") )
            {
                is_true = false;
                System.out.println("This chat room is private");
                return is_true;

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }






        return is_true;
    }
    public String getLast_seen() {
        return last_seen;
    }

    public String getCr_desc() {
        return cr_desc;
    }


    //Functions
    public void showUsers(int RoomID) {

           if (findRoom(RoomID) != 0)
           {
               int[] ids = new int[50];
               try {

                   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");
                   Statement statement = connection.createStatement();

                   // progressing if chatroom id exists or doesnt exist

                   String idQuery = "select * from cr_users where cr_id = " + RoomID;
                   ResultSet rs1 = statement.executeQuery(idQuery);
                   int index = 1;
                   while (rs1.next()) {
                       String id = rs1.getString("user_id");
                       ids[index] = Integer.parseInt(id);
                       index += 1;
                   }
                   index = 1; // index reset
                   while (ids[index] != 0)
                   {
                       int temp = ids[index];
                       String query2 = "SELECT * From usser where id = " + temp;
                       ResultSet rs2 = statement.executeQuery(query2);

                       if (!rs2.next()) {
                           System.out.println("Results are not found !");
                       } else {
                           do {
                               String outputNames = rs2.getString("f_name");
                               System.out.println("Users : " + outputNames);
                           } while (rs2.next());
                       }
                       index += 1;
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
    }



    public String showLastSeen()
    {
        return "";
    }

    ;

    public void loadMessages() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");

            Statement statement = connection.createStatement();
            ResultSet query_for_messages = statement.executeQuery("select * from messages , message_to , usser where cr_id = "+ id +
                    " and  msg_id = messages.id " +
                    "and sender_id =usser.id  order by date , time");


            if(this.messages == null)
                this.messages=new ArrayList<>();

            else if (this.messages.size()!=0)
                this.messages.clear();
            while (query_for_messages.next()) {

                Message temporary = new Message();
                temporary.setDate(query_for_messages.getString("date"));
                temporary.setId(Integer.parseInt(query_for_messages.getString("id")));
                temporary.setSeen(Integer.parseInt(query_for_messages.getString("seen")));
                temporary.setTime(query_for_messages.getString("time"));
                temporary.setText(query_for_messages.getString("text"));
                User temp=new User(Integer.parseInt(query_for_messages.getString("sender_id"))
                        ,Integer.parseInt(query_for_messages.getString("number"))
                        , query_for_messages.getString("f_name")
                        , query_for_messages.getString("password")
                        , query_for_messages.getString("prof_pic")
                        , query_for_messages.getString("prof_desc"));
                temporary.setSender(temp);
                temporary.setType(query_for_messages.getString("type"));

                this.messages.add(temporary);
                temporary = null ;
                temp = null ;

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        ;

    }

    ;

    public int findRoom (int RoomID) // returns an array with the roomIds inside
    {
        int roomId = 0;
        boolean roomFound = false;
        int foundRoomId = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");
            Statement statement = connection.createStatement();


            String Query = "select * from cr_users where cr_id = " + RoomID;
            ResultSet rsss = statement.executeQuery(Query);

            while (rsss.next()) {
                String rooms = rsss.getString("cr_id");
                roomId  = Integer.parseInt(rooms);
            }

                if (roomId == RoomID) {
                    roomFound = true;
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!roomFound) {
            System.out.println("Room Not Found !");
        } else {
            System.out.println(" Room " + RoomID + " found ");
            foundRoomId = RoomID;
        }
        return foundRoomId;
    }
}
