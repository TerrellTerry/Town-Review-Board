package dev.terry.services;

import dev.terry.data.ComplaintDAO;
import dev.terry.data.MeetingDAO;
import dev.terry.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService{
    private MeetingDAO meetingDAO;
    public MeetingServiceImpl(MeetingDAO meetingDAO){
        this.meetingDAO = meetingDAO;
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        if(meeting.getSummary().equals("") || meeting.getAddress().equals(""))
        {
            throw new RuntimeException("Address or summary cannot be left empty.");
        }
        return this.meetingDAO.createMeeting(meeting);
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }

    @Override
    public Meeting getMeetingWithId(int id) {
        return this.meetingDAO.getMeetingWithId(id);
    }
}
