package controller.web.servlets;

import model.dto.Appointment;
import model.dto.UserProfileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import view.api.ITalonService;
import view.api.IUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/doctorDashboard")
public class DoctorServlet {
    private final IUserService userService;
    private final ITalonService talonService;

    public DoctorServlet(IUserService userService, ITalonService talonService) {
        this.userService = userService;
        this.talonService = talonService;
    }

    @GetMapping
    public String doGet() {
        return "doctorDashboard";
    }

    @GetMapping(value = "/appointments")
    public String getAppointments() {
        return "appointmentsMenu";
    }

    @GetMapping(value = "/appointments/createPage")
    public String loadCreatePage() {
        return "appointmentCreate";
    }

    @PostMapping(value = "/appointments/create")
    @ResponseBody
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment, HttpServletRequest request) {
        try {
            talonService.create(appointment,request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/appointments/deletePage")
    public String loadDeletePage() {
        return "appointmentDelete";
    }

    @DeleteMapping(value = "/appointments/delete")
    @ResponseBody
    public ResponseEntity<?> deleteAppointment(@RequestBody Appointment appointment, HttpServletRequest request) {
        try {
            talonService.delete(appointment,request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
