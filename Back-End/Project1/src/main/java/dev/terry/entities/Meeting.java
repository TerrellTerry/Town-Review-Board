package dev.terry.entities;

import dev.terry.entities.enums.Status;

public class Meeting {
    private int id;
    private String summary;
    private String address;
    private Status status;
    private long time;

    public Meeting() {
    }

    public Meeting(String summary, String address, long time) {
        this.summary = summary;
        this.address = address;
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
