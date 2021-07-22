package controller.web.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import view.api.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/forms")
public class FormsServlet {

    private final INewsService newsService;
    private final IAddressService addressService;
    private final IPassportService passportService;
    private final IDepartmentService departmentService;
    private final IDiseaseService diseaseService;
    private final ISymptomService symptomService;
    private final IMedCardService medCardService;
    private final ITalonService talonService;

    public FormsServlet(INewsService newsService, IAddressService addressService,
                        IPassportService passportService, IDepartmentService departmentService,
                        IDiseaseService diseaseService, ISymptomService symptomService, IMedCardService medCardService, ITalonService talonService) {
        this.newsService = newsService;
        this.addressService = addressService;
        this.passportService = passportService;
        this.departmentService = departmentService;
        this.diseaseService = diseaseService;
        this.symptomService = symptomService;
        this.medCardService = medCardService;
        this.talonService = talonService;
    }

    @GetMapping
    public String doGet() {
        return "forms";
    }

    @PostMapping(value = "/news")
    public String addNews(HttpServletRequest request) {
        String header = request.getParameter("header");
        String text = request.getParameter("text");

        newsService.add(header, text);
        return "redirect:/forms";
    }

    @PostMapping(value = "/address")
    public String addAddress(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String flat = request.getParameter("flat");
        String country = request.getParameter("country");

        addressService.add(id, city, street, house, flat, country);
        return "redirect:/forms";
    }

    @PostMapping(value = "/passport")
    public String addPassport(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String passportId = request.getParameter("passportId");
        String passportNum = request.getParameter("passportNum");
        String country = request.getParameter("country");
        String nationality = request.getParameter("nationality");
        String birthDate = request.getParameter("birthDate");
        String sex = request.getParameter("sex");
        String issueDate = request.getParameter("issueDate");
        String expireDate = request.getParameter("expireDate");
        String birthCountry = request.getParameter("birthCountry");

        passportService.add(id, passportId, passportNum, country, nationality, birthDate, sex, issueDate, expireDate, birthCountry);
        return "redirect:/forms";
    }

    @PostMapping(value = "/department")
    public String addDepartment(HttpServletRequest request) {
        String name = request.getParameter("name");
        String info = request.getParameter("info");

        departmentService.add(name, info);
        return "redirect:/forms";
    }

    @PostMapping(value = "/disease")
    public String addDisease(HttpServletRequest request) {
        String name = request.getParameter("name");
        String info = request.getParameter("code");

        diseaseService.add(name, info);
        return "redirect:/forms";
    }

    @PostMapping(value = "/symptom")
    public String addSymptom(HttpServletRequest request) {
        String name = request.getParameter("name");
        String info = request.getParameter("code");

        symptomService.add(name, info);
        return "redirect:/forms";
    }

    @PostMapping(value = "/medcard")
    public String addMedCard(HttpServletRequest request) {
        int cardId = Integer.parseInt(request.getParameter("id"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String name = request.getParameter("diseaseName");
        String diagnoseDate = request.getParameter("diagnoseDate");
        String dischargeDate = request.getParameter("dischargeDate");

        medCardService.add(cardId, doctorId, name, diagnoseDate, dischargeDate);
        return "redirect:/forms";
    }

    @PostMapping(value = "/talon")
    public String addTalon(HttpServletRequest request) {
        int cardId = Integer.parseInt(request.getParameter("id"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String time = request.getParameter("time");

        talonService.add(cardId, time, doctorId);
        return "redirect:/forms";
    }
}
