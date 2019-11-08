package com.tb.directoryapp.controllers;


import com.tb.directoryapp.contactForm.ContactForm;
import com.tb.directoryapp.converters.ContactToContactForm;
import com.tb.directoryapp.domain.Contact;
import com.tb.directoryapp.domain.Interactions;
import com.tb.directoryapp.domain.Sbjs;
import com.tb.directoryapp.services.ContactService;
import com.tb.directoryapp.services.InteractionsService;
import com.tb.directoryapp.services.SbjsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private InteractionsService interactionsService;
    @Autowired
    private SbjsService sbjsService;
    @Autowired
    private ContactToContactForm contactToContactForm;


    @RequestMapping("/contact/new")
    public String newContact(Model model){
        model.addAttribute("contactForm", new ContactForm());
        return "contactform";
    }

    @PostMapping(value = "/contact")
    public String saveOrUpdateContact(@Valid ContactForm contactForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "contactform";
        }

        Contact savedContact = contactService.saveOrUpdateContactForm(contactForm);
        return "redirect:/contact/show/" + savedContact.getId();
    }

    @GetMapping(value = "/interactions")
    public String saveOrUpdateInteractions(@Valid ContactForm contactForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "contactform";
        }

        Interactions savedContact = interactionsService.saveOrUpdateContactForm(contactForm);
        return "redirect:/contact/new";
    }

    @RequestMapping({"/contact/list", "/contact"})
    public String listContacts(Model model) {
        model.addAttribute("contacts",contactService.listAll());
        return "list";
    }

    // Since we're Merging the List Page(i.e '/contact/list') with HomePage('/contact')
    // You must redirect it(i.e redir-To-List) back to the Main Source, since they share the same Page.
    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/contact/list";
    }

    @RequestMapping("/contact/show/{id}")
    public String getContact(@PathVariable String id, Model model){
        model.addAttribute("contacts", contactService.getById(Long.valueOf(id)));
        return "show";
    }

    // To "Edit" Object....
    // Instantiate the Object, like You did when Saving the Object.
    // Then use the Object's Service to find it by it's ID-Value,
    // Followed by the Process involved during the Saving Process,
    // to Return the "Object".
    @RequestMapping("contact/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Contact contact = contactService.getById(Long.valueOf(id));
        ContactForm contactForm = contactToContactForm.convert(contact);

        model.addAttribute("contactForm", contactForm);
        return "contactform";
    }

    @RequestMapping("/contact/delete/{id}")
    public String delete(@PathVariable String id){
        contactService.delete(Long.valueOf(id));
        return "redirect:/contact/list";
    }

    @PostMapping(value = "/contact.htm")
    public @ResponseBody
    Sbjs processAJAXRequest(
            @RequestParam("type") String type,
            @RequestParam("source") String source,
            @RequestParam("medium") String medium,
            @RequestParam("cmp") String cmp,
            @RequestParam("cnt") String cnt,
            @RequestParam("trm") String trm ) {

        System.out.println("\n" + "************ WEB AGENT INTERPRETER: ************" + "\n" + "\n" +
                           "TYPE: referral === WEB AGENT: Opera" + "\n" +
                           "TYPE: typein === WEB AGENT: Chrome, Firefox, Safari" + "\n" + "\n" +
                           "************ WEB AGENT INFORMATION: ************" + "\n");
        System.out.println("TYPE: " + type);
        System.out.println("SOURCE: " + source);
        System.out.println("MEDIUM: " + medium);
        System.out.println("CAMPAIGN: " + cmp);
        System.out.println("CONTENT: " + cnt);
        System.out.println("TERM: " + trm);

        Sbjs sbjsPojo = new Sbjs();

        sbjsPojo.setType(type);
        sbjsPojo.setSource(source);
        sbjsPojo.setMedium(medium);
        sbjsPojo.setCampaign(cmp);
        sbjsPojo.setContent(cnt);
        sbjsPojo.setTerm(trm);

        System.out.println("*** Sourcebuster Parameters: HAS BEEN SENT TO DB ***");

        return sbjsService.saveOrUpdate(sbjsPojo);

    }

}



