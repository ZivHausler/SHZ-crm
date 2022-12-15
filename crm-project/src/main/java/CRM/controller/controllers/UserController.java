package CRM.controller.controllers;

import CRM.controller.facades.UserFacade;
import CRM.entity.response.Response;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class.getName());


    @Autowired
    private UserFacade userFacade;

    @GetMapping(value = "{id}")
    public ResponseEntity<Response> get(@DestinationVariable Long id){

        Response response = userFacade.get(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
