package dev.terry.services;

import dev.terry.entities.Complaint;
import dev.terry.entities.enums.Priority;

import java.util.List;

public interface ComplaintService {

    public Complaint createComplaint(Complaint complaint);

    public Complaint createComplaintWithMeetingId(Complaint complaint, int id);

    public List<Complaint> getAllComplaints();

    public Complaint getComplaintWithId(int id);

    public Complaint updateMeeting(Complaint complaint, int meetingId);

    public Complaint updatePriority(Complaint complaint, Priority priority);
}
