package org.sid.sec;

import javax.annotation.security.PermitAll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
@PermitAll
@RequestMapping(value="/login")
public String login() {
	return "login";
}
@RequestMapping(value="/")
public String home()
{
	return "redirect:/Commande";}
@RequestMapping(value="/404")
public String ereur() {
return "ereur";
}
}
