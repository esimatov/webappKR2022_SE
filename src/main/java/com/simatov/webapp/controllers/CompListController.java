package com.simatov.webapp.controllers;

import com.simatov.webapp.models.*;
import com.simatov.webapp.repo.ComputerRepository;
import com.simatov.webapp.repo.RateRepository;
import com.simatov.webapp.repo.SessionRepository;
import com.simatov.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CompListController {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    RateRepository rateRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Computer Checker");
        List<Session> activeSessions = new ArrayList<>();
        List<Session> sessions = (List<Session>) sessionRepository.findAll();

        for (int i=0; i < sessions.size(); i++) {
            Date date = new Date();
            if (!sessions.get(i).getFdate().after(date)) {
                Session session = sessions.get(i);
                session.setActive(false);
                sessionRepository.save(session);
                Computer computer = computerRepository.findById(sessions.get(i).getComputerId()).orElseThrow();
                computer.setStatus(Status.FREE);
                computerRepository.save(computer);
            }
        }
        for (int i=0; i < sessions.size(); i++) {
            if (sessions.get(i).isActive()) activeSessions.add(sessions.get(i));
        }
        model.addAttribute("sessions", activeSessions);
        return "index";
    }

    @GetMapping("/newsession")
    public String newSession(Model model) {
        Iterable<Rate> rates = rateRepository.findAll();
        model.addAttribute("rates", rates);
        Iterable<Computer> comps = computerRepository.findAll();
        model.addAttribute("comps", comps);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        Iterable<Session> sessions = sessionRepository.findAll();
        model.addAttribute("sessions", sessions);
        return "new-session";
    }

    @PostMapping("/newsession")
    public String addSession(@RequestParam Long userId,
                             @RequestParam Long compId,
                             @RequestParam int time,
                             @RequestParam int sessionType,
                             @RequestParam String comment,
                             Model model) {
        User user = userRepository.findById(userId).orElseThrow();
        Computer comp = computerRepository.findById(compId).orElseThrow();
        if (comp.getStatus() == Status.FREE) {
            if ((sessionType == 1 && time <= user.getWorkingCompTime()) || (sessionType == 2 && time <= user.getGamingCompTime())) {
                Session session = new Session(true, time, sessionType, comment, user, comp);
                sessionRepository.save(session);
                comp.setStatus(Status.BUSY);
                computerRepository.save(comp);
                if (sessionType == 1) user.setWorkingCompTime(user.getWorkingCompTime() - time);
                if (sessionType == 2) user.setGamingCompTime(user.getGamingCompTime() - time);
                userRepository.save(user);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/history")
    public String sessionsHistory(Model model) {
        Iterable<Session> sessions = sessionRepository.findAll();
        model.addAttribute("sessions", sessions);
        return "sessions-history";
    }
}
