package repository;
import entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
public class StudentDAO implements Serializable {
    Logger logger = Logger.getLogger(StudentDAO.class);

    public List<Student> getAllStudent(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Student ").list();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByName(String name){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where lower(fullName) like lower(to_char(concat(concat('%', :p_student_name), '%'))) ");
            query.setParameter("p_student_name", name);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }
    public List<Student> getStudentByBirthday(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            LocalDate today = LocalDate.now();
            int month = today.getMonthValue();
            int day = today.getDayOfMonth();
            Query<Student> query = session.createQuery("from Student where to_number(to_char(birthday, 'MM')) = :p_month and to_number(to_char(birthday, 'dd')) = :p_day");
            query.setParameter("p_month", month);
            query.setParameter("p_day", day);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByGender(String gender){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where gender = :p_student_gender");
            query.setParameter("p_student_gender", gender);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByHometown(String homeTown){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where hometown = :p_student_hometown");
            query.setParameter("p_student_hometown", homeTown);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByClassName(String className){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where className = :p_student_className");
            query.setParameter("p_student_className", className);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByMajor(String major){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where major = :p_student_major");
            query.setParameter("p_student_major", major);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public List<Student> getStudentByAverage(double min, double max){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query<Student> query = session.createQuery("from Student where averageMark between :p_student_markMin and :p_student_markMax");
            query.setParameter("p_student_markMin", min);
            query.setParameter("p_student_markMax", max);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public boolean insertStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean removeStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Student student = session.load(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e);
        }
        return false;
    }
}
