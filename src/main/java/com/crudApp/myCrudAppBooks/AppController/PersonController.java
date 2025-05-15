package com.crudApp.myCrudAppBooks.AppController;

import com.crudApp.myCrudAppBooks.model.Person;
import com.crudApp.myCrudAppBooks.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //view list of persons
    @GetMapping
    public String listPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "people/list";  
    }

    //create new formular
    @GetMapping("/new")
    public String newPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";  
    }

    //redirect after save
    @PostMapping("/save")
    public String savePerson(@ModelAttribute Person person) {
        personService.savePerson(person);
        return "redirect:/people";  
    }

    //edit person
    @GetMapping("/edit/{id}")
    public String editPersonForm(@PathVariable("id") Long id, Model model) {
        Person person = personService.getPersonById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person ID"));
        model.addAttribute("person", person);
        return "people/edit";  
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, @ModelAttribute Person person) {
        //set ID for updates
    	person.setId(id);  
        personService.savePerson(person);
        
        //redirect after change
        return "redirect:/people";  
    }

    //redirect after delete
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/people";  
    }
}
