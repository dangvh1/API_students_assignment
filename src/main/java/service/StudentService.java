package service;
import entity.Student;
import repository.StudentDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
public class StudentService {
    StudentDAO studentDAO = new StudentDAO();

    public List<Student> getListStudent() {
        return studentDAO.getAllStudent();
    }

    public List<Student> getListStudentBirthday() {
        return studentDAO.getStudentByBirthday();
    }

    public List<Student> getListStudentByName(String name){
        return studentDAO.getStudentByName(name);
    }

    public List<Student> getListStudentByGender(String gender){
        return studentDAO.getStudentByGender(gender);
    }

    public List<Student> getListStudentByHometown(String hometown){
        return studentDAO.getStudentByHometown(hometown);
    }

    public List<Student> getListStudentByClassName(String className){
        return studentDAO.getStudentByClassName(className);
    }

    public List<Student> getListStudentByMajor(String major){
        return studentDAO.getStudentByMajor(major);
    }

    public List<Student> getListStudentAverageMark(double min, double max){
        return studentDAO.getStudentByAverage(min, max);
    }

    public boolean insert(Student student) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            student.setBirthday(sdf.parse(String.valueOf(student.getBirthday())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Student> students = studentDAO.getAllStudent();
        students.sort((o1, o2) -> o1.getId() < o2.getId() ? 1 : -1);
        int id = students.get(0).getId() + 1;
        student.setId(id);
        if (student.getFullName() == null || student.getFullName().length() > 50 || student.getFullName().length() < 1){
            return false;
        }
        if (student.getBirthday() == null){
            return false;
        }
        if (student.getClassName() == null ){
            return false;
        }
        if (student.getMajor() == null){
            return false;
        }
        if (student.getHometown() == null){
            return false;
        }
        if (student.getGender() == null){
            return false;
        }
        if (student.getAverageMark() > 10 || student.getAverageMark() < 0){
            return false;
        }
        return studentDAO.insertStudent(student);
    }

    public boolean update(Student student) {
        if(student.getId() <= 0){
            return false;
        }
        if (student.getFullName() == null || student.getFullName().length() > 50 || student.getFullName().length() < 1){
            return false;
        }
        if (student.getBirthday() == null){
            return false;
        }else {
            Period period = Period.between(LocalDate.ofEpochDay(student.getBirthday().getDate()), LocalDate.now());
            if(period.getYears() > 100 || period.getYears() < 0){
                return false;
            }
        }
        if (student.getClassName() == null ){
            return false;
        }
        if (student.getMajor() == null){
            return false;
        }
        if (student.getHometown() == null){
            return false;
        }
        if (student.getGender() == null){
            return false;
        }
        if (student.getAverageMark() > 10 || student.getAverageMark() < 0){
            return false;
        }
        return studentDAO.update(student);
    }

    public boolean removeStudent(int id) {
        return studentDAO.removeStudent(id);
    }
}
