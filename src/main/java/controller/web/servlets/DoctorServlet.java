package controller.web.servlets;

import model.dto.Appointment;
import model.dto.MedcardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import view.api.IMedCardService;
import view.api.ITalonService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/doctorDashboard")
public class DoctorServlet {
    private final ITalonService talonService;
    private final IMedCardService medCardService;

    public DoctorServlet(ITalonService talonService, IMedCardService medCardService) {
        this.talonService = talonService;
        this.medCardService = medCardService;
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

    @GetMapping(value = "/medcards")
    public String getMedcards() {
        return "medcards/medcardsMenu";
    }

    @GetMapping(value = "/medcards/createPage")
    public String loadCreateMedcardsPage() {
        return "medcards/medcardCreate";
    }

    @PostMapping(value = "/medcards/create")
    @ResponseBody
    public ResponseEntity<?> createMedcard(@RequestBody MedcardDTO diseaseDTO, HttpServletRequest request) {
        try {
            medCardService.create(diseaseDTO,request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/medcards/deletePage")
    public String loadDeleteMedcardPage() {
        return "medcards/medcardDelete";
    }

    @DeleteMapping(value = "/medcards/delete")
    @ResponseBody
    public ResponseEntity<?> deleteMedcard(@RequestBody MedcardDTO diseaseDTO, HttpServletRequest request) {
        try {
            medCardService.delete(diseaseDTO, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/medcards/updatePage")
    public String loadUpdateMedcardPage() {
        return "medcards/medcardUpdate";
    }

    @PostMapping(value = "/medcards/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MedcardDTO> getMedcardInfo(@RequestBody MedcardDTO diseaseDTO, HttpServletRequest request) {
        try {
            MedcardDTO medcard = medCardService.getMedcardRecord(diseaseDTO, request);
            return new ResponseEntity<>(medcard, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/medcards/update")
    @ResponseBody
    public ResponseEntity<MedcardDTO> updateMedcard(@RequestBody MedcardDTO diseaseDTO) {
        try {
            medCardService.update(diseaseDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/medcards/show")
    public String loadShowMedcardsPage(Model model, HttpServletRequest request) {
        model.addAttribute("medcards", medCardService.getAllRecords(request));
        return "medcards/medcardShow";
    }
}