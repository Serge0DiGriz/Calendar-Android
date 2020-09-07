package com.example.calendar;

public class Lesson {

    private String university, specialty,
            start_time, end_time, date;
    private int course, semester, week, weekday;

    public Lesson(String university, String specialty, int course, int semester,
                  String start_time, String end_time, int week, int weekday) {
        this.university = university;
        this.specialty = specialty;
        this.start_time = start_time;
        this.end_time = end_time;
        this.weekday = weekday;
        this.course = course;
        this.semester = semester;
        this.week = week;
    }

    public Lesson(String university, String specialty, int course,
                  String start_time, String end_time, String date) {
        this.university = university;
        this.specialty = specialty;
        this.start_time = start_time;
        this.end_time = end_time;
        this.date = date;
        this.course = course;
    }

    public String getUniversity() {
        return university;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getWeekday() {
        return weekday;
    }

    public String getDate() {
        return date;
    }

    public int getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "university='" + university + '\'' +
                ", specialty='" + specialty + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", date='" + date + '\'' +
                ", course=" + course +
                ", semester=" + semester +
                ", week=" + week +
                ", weekday=" + weekday +
                "}\n";
    }
}
