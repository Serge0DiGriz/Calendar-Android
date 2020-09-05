package com.example.calendar;

public class OneLesson {

    private String university;
    private int semester;
    private int number_week;
    private String weekday;
    private String start_time;
    private String end_time;
    private String specialty;
    private int year_specialty;

    public OneLesson() {
    }

    public OneLesson(String university, int semester, int number_week, String weekday, String start_time, String end_time, String specialty, int year_specialty) {
        this.university = university;
        this.semester = semester;
        this.number_week = number_week;
        this.weekday = weekday;
        this.start_time = start_time;
        this.end_time = end_time;
        this.specialty = specialty;
        this.year_specialty = year_specialty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNumber_week() {
        return number_week;
    }

    public void setNumber_week(int number_week) {
        this.number_week = number_week;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYear_specialty() {
        return year_specialty;
    }

    public void setYear_specialty(int year_specialty) {
        this.year_specialty = year_specialty;
    }

    @Override
    public String toString() {
        return university + " " + semester + " " + number_week + " " + weekday + " " +
                start_time + " " + end_time + " " + specialty + " " + year_specialty;
    }
}
