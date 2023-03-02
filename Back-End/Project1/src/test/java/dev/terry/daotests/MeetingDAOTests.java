package dev.terry.daotests;

import dev.terry.data.MeetingDAO;
import dev.terry.data.MeetingDAOPostgres;
import dev.terry.entities.Complaint;
import dev.terry.entities.Meeting;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingDAOTests {

    static MeetingDAO meetingDAO = new MeetingDAOPostgres();

    @Test
    @Order(1)
    void create_meeting()
    {
        Meeting meeting = new Meeting("Summary", "Addresss", 23);
        Meeting savedMeeting = this.meetingDAO.createMeeting(meeting);
        Assertions.assertEquals(meeting.getId(), savedMeeting.getId());
    }
    @Test
    @Order(2)
    void get_all_meetings(){
        Assertions.assertNotEquals(0, meetingDAO.getAllMeetings().size());
    }
}
