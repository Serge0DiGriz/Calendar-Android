package com.example.calendar;

public class CorrectedLesson {

    private String university;
    private String data_lesson;
    private String start_time;
    private String end_time;
    private String specialty;
    private int year_specialty;

    public CorrectedLesson() {
    }

    public CorrectedLesson(String university, String data_lesson, String start_time, String end_time, String specialty, int year_specialty) {
        this.university = university;
        this.data_lesson = data_lesson;
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

    public String getData_lesson() {
        return data_lesson;
    }

    public void setData_lesson(String data_lesson) {
        this.data_lesson = data_lesson;
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
        return university + " " + data_lesson + " " + start_time + " " + end_time + " " + specialty + " " + year_specialty;
    }
}
