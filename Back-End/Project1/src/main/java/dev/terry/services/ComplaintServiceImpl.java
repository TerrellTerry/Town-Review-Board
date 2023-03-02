package dev.terry.services;

import dev.terry.data.ComplaintDAO;
import dev.terry.entities.Complaint;
import dev.terry.entities.enums.Priority;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService{
    private ComplaintDAO complaintDAO;
    public ComplaintServiceImpl(ComplaintDAO complaintDAO){
        this.complaintDAO = complaintDAO;
    }

    @Override
    public Complaint createComplaint(Complaint complaint) {
        complaintChecker(complaint);
        return this.complaintDAO.createComplaint(complaint);
    }

    @Override
    public Complaint createComplaintWithMeetingId(Complaint complaint, int id) {
        complaintChecker(complaint);
        return this.complaintDAO.createComplaintWithMeetingId(complaint, id);
    }

    void complaintChecker(Complaint complaint){
        if(complaint.getSubject().equals("") || complaint.getDescription().equals("")){
            //can't be blank
            throw new RuntimeException("Cannot have field be empty");
        }
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return this.complaintDAO.getAllComplaints();
    }

    @Override
    public Complaint getComplaintWithId(int id) {
        return this.complaintDAO.getComplaintWithId(id);
    }

    @Override
    public Complaint updateMeeting(Complaint complaint, int meetingId) {
        return this.complaintDAO.updateMeeting(complaint, meetingId);
    }

    @Override
    public Complaint updatePriority(Complaint complaint, Priority priority) {
        return this.complaintDAO.updatePriority(complaint, priority);
    }
}
