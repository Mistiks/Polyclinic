package controller.web.servlets;

import model.dto.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "appointments/doctorDashboard";
    }

    @GetMapping(value = "/appointments")
    public String getAppointments() {
        return "appointments/appointmentsMenu";
    }

    @GetMapping(value = "/appointments/createPage")
    public String loadCreatePage() {
        return "appointments/appointmentCreate";
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
        return "appointments/appointmentDelete";
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

    @GetMapping(value = "/appointments/updatePage")
    public String loadUpdatePage() {
        return "appointments/appointmentUpdate";
    }

    @PutMapping(value = "/appointments/update")
    @ResponseBody
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment, HttpServletRequest request) {
        try {
            talonService.update(appointment,request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/appointments/show")
    public String loadShowPage(Model model, HttpServletRequest request) {
        model.addAttribute("appointments", talonService.getAllTalons(request));
        return "appointments/appointmentShow";
    }
}