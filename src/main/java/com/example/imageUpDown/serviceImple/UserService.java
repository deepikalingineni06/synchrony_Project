package com.example.imageUpDown.serviceImple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.imageUpDown.entity.UserEntity;
import com.example.imageUpDown.exception.RecordNotFoundException;
import com.example.imageUpDown.repository.UserRepository;


 
@Service
public class UserService {
     
    @Autowired
    UserRepository userRepository;
    
    
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<UserEntity> user = repository.findByUserName(userName);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
//
//        return user.map(MyUserDetails::new).get();
//    }
     
    
    public void addUser(UserEntity userEntity) {
    	userRepository.save(userEntity);
	}
    
  
	public UserEntity getUser(String userName) {
		List<UserEntity> lusers=userRepository.findAll();
		
		boolean flag=false;
		
		UserEntity tempUser=null;
			
		for(UserEntity user:lusers)
		{
			if(user.getUserName().equals(userName))
			{
				flag=true;
				tempUser=user;
				break;
			}
		}
		
		if(flag)
		{
			return tempUser;
		}
		
		return null;
	}
    
    public List<UserEntity> getAll()
    {
        List<UserEntity> users = userRepository.findAll();
         
        if(users.size() > 0) {
            return users;
        } else {
            return new ArrayList<UserEntity>();
        }
    }
     
    public UserEntity getUserById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = userRepository.findById(id);
         
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No User record exist for given id");
        }
    }
     
    public UserEntity createOrUpdate(UserEntity entity) throws RecordNotFoundException
    {
        Optional<UserEntity> user = userRepository.findById(entity.getId());
         
        if(user.isPresent())
        {
            UserEntity newEntity = user.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = userRepository.save(newEntity);
             
            return newEntity;
        } else {
            entity = userRepository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteById(Long id) throws RecordNotFoundException
    {
        Optional<UserEntity> user = userRepository.findById(id);
         
        if(user.isPresent())
        {
        	userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id");
        }
    }
}