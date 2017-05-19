package services.user.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import exception.CaveatEmptorException;
import model.UserDto;
import services.user.IUserService;

@Stateless
@ApplicationPath("/resources")
@Path("persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends Application{
	
	@EJB
	IUserService iUserService;
	
	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@GET
	@Path("{username}")
	public UserDto getLoggedUser(@PathParam("username")String username){
		
		try {
			
			return iUserService.getUserByUsername(username);
			
		} catch (CaveatEmptorException e) {


			return null;
			
		}
		
	}
}
