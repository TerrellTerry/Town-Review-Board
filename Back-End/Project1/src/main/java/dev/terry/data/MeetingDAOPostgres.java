package dev.terry.data;

import dev.terry.entities.Complaint;
import dev.terry.entities.Meeting;
import dev.terry.entities.enums.Priority;
import dev.terry.entities.enums.Status;
import dev.terry.utils.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOPostgres implements MeetingDAO{
    @Override
    public Meeting createMeeting(Meeting meeting) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into meeting values(default, ?, ?, default, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, meeting.getSummary());
            preparedStatement.setString(2, meeting.getAddress());
            preparedStatement.setLong(3, meeting.getTime());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            meeting.setId(rs.getInt("id"));
            meeting.setStatus(Status.valueOf(rs.getString("status")));

            return meeting;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from meeting";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Meeting> meetings = new ArrayList<>();
            while(rs.next()){
                Meeting meeting = new Meeting();

                meeting.setId(rs.getInt("id"));
                meeting.setSummary(rs.getString("summary"));
                meeting.setAddress(rs.getString("address"));
                meeting.setStatus(Status.valueOf(rs.getString("status")));
                meeting.setTime(rs.getLong("time"));

                meetings.add(meeting);
            }
            return meetings;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Meeting getMeetingWithId(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from meeting where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(!rs.next())
            {
                return null;
            }

            Meeting meeting = new Meeting();

            meeting.setId(rs.getInt("id"));
            meeting.setSummary(rs.getString("summary"));
            meeting.setAddress(rs.getString("address"));
            meeting.setTime(rs.getLong("time"));

            return meeting;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
