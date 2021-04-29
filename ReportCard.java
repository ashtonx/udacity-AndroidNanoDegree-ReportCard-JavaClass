package com.example.android.reportcard;

import java.util.ArrayList;
import java.util.List;

class ReportCard {
    public final static int MAX_GRADE = 6; //using polish school scale
    public final static int MIN_GRADE = 1;
    public final static int NULL = -1;
    private ArrayList<Course> reportCard;

    ReportCard() {
        reportCard = new ArrayList<>();
    }

    private int findCourse(String name) {
        for (int i = 0; i < reportCard.size(); ++i) {
            if (reportCard.get(i).getName().equals(name)) return i;
        }
        return NULL;
    }

    public void addCourse(String name) {
        reportCard.add(new Course(name));
    }

    public void removeCourse(String name) {
        int i = findCourse(name);
        if (i != NULL) reportCard.remove(i);
    }


    public String getCourseName(int position) {
        if (position > 0 && position < reportCard.size()) return reportCard.get(position).getName();
        return null;
    }

    public void changeCourseName(String name, final String newName) {
        int i = findCourse(name);
        int test = findCourse(newName);
        if (i != NULL && test == NULL) reportCard.get(i).changeName(newName);
    }

    public void addGrade(final String courseName, final int grade) {
        int i = findCourse(courseName);
        if (i != NULL) reportCard.get(i).addGrade(grade);
    }

    public List getGrades(String name) {
        int i = findCourse(name);
        if (i != NULL) return reportCard.get(i).getGrades();
        return null;
    }

    public List getGrades(int position) {
        if (position > 0 && position < reportCard.size())
            return reportCard.get(position).getGrades();
        return null;
    }

    public float getAverage(String name) {
        int i = findCourse(name);
        if (i != NULL) return reportCard.get(i).getAverage();
        return NULL;
    }

    public int getSize() {
        return reportCard.size();
    }


    @Override
    public String toString() {
        String out = "";
        for (Course c : reportCard) out += c.toString();
        return out;
    }

    private class Course {
        private String mName;
        private ArrayList<Integer> mGrades;

        public Course(String name) {
            mName = name;
            mGrades = new ArrayList<>();
        }

        public String getName() {
            return mName;
        }

        public void changeName(String name) {
            mName = name;
        }

        public void addGrade(int grade) {
            if (grade > MIN_GRADE && grade < MAX_GRADE) mGrades.add(grade);
        }

        public List getGrades() {
            return mGrades;
        }

        public float getAverage() {
            int sum = 0;
            if (!mGrades.isEmpty()) {
                for (int grade : mGrades) sum += grade;
                return (sum / mGrades.size());
            }
            return sum;
        }

        @Override
        public String toString() {
            String out = "Course name: " + mName + "\n" +
                    "Grades:";
            for (int grade : mGrades) {
                out += "\t" + grade;
            }
            out += "\n";
            out += "Average: " + getAverage() + "\n";
            return out;
        }
    }
}
