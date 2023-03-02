package dev.terry.daotests;

import dev.terry.data.ComplaintDAO;
import dev.terry.data.ComplaintDAOPostgres;
import dev.terry.entities.Complaint;
import dev.terry.entities.enums.Priority;
import dev.terry.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDAOTests {

    static ComplaintDAO complaintDAO = new ComplaintDAOPostgres();
//    @BeforeAll() //This method will execute before any tests
//    static void setup(){
//        try(Connection conn = ConnectionUtil.createConnection()){
//            String sql = "create table complaint(\n" +
//                    "\tid serial primary key,\n" +
//                    "\tsubject varchar(50) not null,\n" +
//                    "\tdescription varchar(200) not null,\n" +
//                    "\t-- category varchar(20) not null,\n" +
//                    "\tmeetingId int references meeting(id) default -1,\n" +
//                    "\tpriority varchar(20) not null default 'UNASSIGNED'\n" +
//                    ");";
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }

    @Test
    @Order(1)
    void create_complaint()
    {
        Complaint complaint = new Complaint("Burgers", "Too many burgers in my house :(");
        Complaint savedComplaint = this.complaintDAO.createComplaint(complaint);
        Assertions.assertEquals(complaint.getId(), savedComplaint.getId());

    }

    @Test
    @Order(2)
    void get_all_complaints(){
        Assertions.assertNotEquals(0, this.complaintDAO.getAllComplaints());
    }

    @Test
    @Order(3)
    void update_meetingId(){
        this.complaintDAO.updateMeeting(this.complaintDAO.getComplaintWithId(1), 1);
        Assertions.assertEquals(1, this.complaintDAO.getComplaintWithId(1).getMeetingId());
    }

    @Test
    @Order(4)
    void update_priority(){
        this.complaintDAO.updatePriority(this.complaintDAO.getComplaintWithId(1), Priority.LOW);
        Assertions.assertEquals(Priority.LOW, this.complaintDAO.getComplaintWithId(1).getPriority());
    }

    @Test
    @Order(5)
    void create_complaint_with_meetingid(){
        Complaint complaint = new Complaint("Crayons", "I think I ate too many crayons.");
        Complaint savedComplaint = this.complaintDAO.createComplaintWithMeetingId(complaint, 1);

        Assertions.assertEquals(1, complaintDAO.getComplaintWithId(savedComplaint.getId()).getMeetingId());
    }

//    @AfterAll
//    static void teardown(){
//        try(Connection conn = ConnectionUtil.createConnection()){
//            String sql = "drop table complaint";
//            Statement statement = conn.createStatement();
//            statement.execute(sql);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
}
