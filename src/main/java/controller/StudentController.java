package controller;
import entity.Student;
import service.StudentService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
@Path("/students")
public class StudentController {
        StudentService studentService = new StudentService();

        @GET
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudent() {
            return studentService.getListStudent();
        }

        @GET
        @Path("/name/{name}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByName(@PathParam("name") String name) {
            return studentService.getListStudentByName(name);
        }

        @GET
        @Path("/gender/{gender}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByGender(@PathParam("gender") String gender) {
            return studentService.getListStudentByGender(gender);
        }

        @GET
        @Path("/hometown/{hometown}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByHometown(@PathParam("hometown") String hometown) {
            return studentService.getListStudentByHometown(hometown);
        }

        @GET
        @Path("/class-name/{className}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByClassName(@PathParam("className") String className) {
            return studentService.getListStudentByClassName(className);
        }

        @GET
        @Path("/major/{major}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByMajor(@PathParam("major") String major) {
            return studentService.getListStudentByMajor(major);
        }

        @GET
        @Path("/average-mark/{min}/{max}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Student> getListStudentByAverageMark(@PathParam("min") double min, @PathParam("min") double max) {
            return studentService.getListStudentAverageMark(min, max);
        }

        @POST
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public String addNewSubject(Student student) {
            return studentService.insert(student) ? "Thêm mới thành công" : "Thêm mới thất bại";
        }

        @DELETE
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public String removeStudent(@PathParam("id") int id) {
            return studentService.removeStudent(id) ? "Xóa thành công" : "Xóa thất bại";
        }

        @PUT
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public String UpdateSubject(Student student) {
            return studentService.update(student) ? "Update thành công" : "Update thất bại";
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/birthday")
        public List<Student> getAllBirthdayPeople(){
            return studentService.getListStudentBirthday();
        }
}
