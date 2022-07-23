package com.cosmos.services.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.cosmos.dtos.general.PageDTO;
import com.cosmos.dtos.project.UserDTO;
import com.cosmos.exceptions.InvalidInputException;
import com.cosmos.models.setups.EUser;
import com.cosmos.models.setups.EUserType;
import com.cosmos.repositories.UserDAO;
import com.cosmos.services.types.IUserType;
import com.cosmos.utilities.specs.SpecBuilder;
import com.cosmos.utilities.specs.SpecFactory;
import com.cosmos.utils.GlobalFunctions;

@Service
public class SUser implements IUser {
	
	@Autowired
	private GlobalFunctions globalFunction;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private IUserType sUserType;
	
	@Autowired
	private SpecFactory specFactory;
	
	  @Override
	    public Page<EUser> getPaginatedList(PageDTO pageDTO, List<String> allowedFields) {
	    	  String searchQuery = pageDTO.getSearch();

	          PageRequest pageRequest = globalFunction.getPageRequest(pageDTO);

	          return userDAO.findAll(buildFilterSpec(searchQuery, allowedFields), pageRequest);
	    }

	    
	    @Override
	    public EUser getById(Integer userId, Boolean throwException) {
	        Optional<EUser> userStatus = userDAO.findById(userId);
	        if (!userStatus.isPresent() && throwException) {
	            throw new InvalidInputException("user with given id not found", "userId");
	        }
	        return userStatus.get();
	    }
	  
		@Override
		public EUser create(UserDTO userDTO) {
			
			EUser user = new EUser();
			
			EUserType uType= sUserType.getById(userDTO.getUserTypeId(),true);
			
			user.setUserFullName(userDTO.getUserFullName());
			user.setUserEmail(userDTO.getUserEmail());
			user.setUserMobile(userDTO.getUserMobile());
			user.setUserReset(userDTO.getUserReset());
			user.setUserActive(userDTO.getUserStatus());
			user.setEUserType(uType);
			
			return userDAO.save(user);
		}
		
		@Override
		public EUser update(UserDTO userDTO) {
			
			EUser user = getById(userDTO.getId(), true);
			
			EUserType uType= sUserType.getById(userDTO.getUserTypeId(),true);
			
			user.setUserFullName(userDTO.getUserFullName());
			user.setUserEmail(userDTO.getUserEmail());
			user.setUserMobile(userDTO.getUserMobile());
			user.setUserReset(userDTO.getUserReset());
			user.setUserActive(userDTO.getUserStatus());
			user.setEUserType(uType);
			
			return userDAO.save(user);
		}
		
		@Override
		public void delete(UserDTO userDTO) {
			
			EUser user = getById(userDTO.getId(), true);
		
			userDAO.delete(user);
		}
		
		  @SuppressWarnings("unchecked")
		    public Specification<EUser> buildFilterSpec(String searchQuery, List<String> allowedFields) {

		        SpecBuilder<EUser> specBuilder = new SpecBuilder<>();

		        specBuilder = (SpecBuilder<EUser>) specFactory.generateSpecification(searchQuery, specBuilder, allowedFields);

		        return specBuilder.build();
		    }


		@Override
		public Optional<EUser> getById(Integer userId) {
			
			 Optional<EUser> user = userDAO.findById(userId);
			 
		      return user;
		}


		@Override
		public Optional<EUser> getByMobileOrEmail(String contact) {
			
			return userDAO.findMobileOrEmail(contact);
		}

}
