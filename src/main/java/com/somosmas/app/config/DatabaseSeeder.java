package com.somosmas.app.config;

import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.model.entity.Role;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.repository.IActivityRepository;
import com.somosmas.app.repository.IRoleRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final String IMAGE = "C:/Img";
    private static final long ROLE_USER = 1L;
    private static final long ROLE_ADMIN = 2L;
    private static final String PASSWORD_GENERIC = "1234";
    

    @Autowired
    IActivityRepository activityRepository;
    
    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    IRoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadActivityData();
        loadUserData();
    }

    private void loadActivityData() {
        if (activityRepository.count() == 0) {
            activityRepository.save(buildActivity(1L, "Apoyo escolar", "Apoyo escolar nivel primario"));
            activityRepository.save(buildActivity(2L, "Apoyo escolar", "Apoyo escolar nivel Secundario"));
            activityRepository.save(buildActivity(3L, "Tutorias", "Tutorias primaria"));
            activityRepository.save(buildActivity(4L, "Tutorias", "Tutorias secundaria"));
            activityRepository.save(buildActivity(5L, "Ayudantías", "2do año"));
            activityRepository.save(buildActivity(6L, "Acompañamiento escolar y familiar", "Monitorear el estado de los tutoreados"));
        }
    }

    private Activity buildActivity(long id, String name, String content) {
        return new Activity(id, name, content, IMAGE, TimestampUtil.getCurrentTime(), false);
    }
    
	private void loadUserData() {
		if (userRepository.count() == 0) {
			userRepository.save(buildUser(1L, "Yalaury", "Tobosa", "User-Yobosa@gmail.com"));
			userRepository.save(buildUser(2L, "Lucas", "Cordoba", "User-lucascordoba77@gmail.com"));
			userRepository.save(buildUser(3L, "Juan Ignacio", "Caballero", "user-juan.ci.caballero@gmail.com"));
			userRepository.save(buildUser(4L, "Federico", "Aramburu", "user-fedearamburu95@gmail.com"));
			userRepository.save(buildUser(5L, "Damian", "Bruque", "User-daamiale.recoil@gmail.com"));
			userRepository.save(buildUser(6L, "JuanMa", "Davila", "User-juanirppr@gmail.com"));
			userRepository.save(buildUser(7L, "Leandro", "Gomez", "User-le.rego17@gmail.com"));
			userRepository.save(buildUser(8L, "Fernando", "Benitez", "User-benitezf.3105@gmail.com"));
			userRepository.save(buildUser(9L, "Magali", "Kain", "User-kain.magali@gmail.com"));
			userRepository.save(buildUser(10L, "AlkemyUser", "Generic", "AlkemyUser-Generic@gmail.com"));
			
//        	Users Admin
			userRepository.save(buildUserAdmin(11L, "Yalaury", "Tobosa", "Yobosa@gmail.com"));
			userRepository.save(buildUserAdmin(12L, "Lucas", "Cordoba", "lucascordoba77@gmail.com"));
			userRepository.save(buildUserAdmin(13L, "Juan Ignacio", "Caballero", "juan.ci.caballero@gmail.com"));
			userRepository.save(buildUserAdmin(14L, "Federico", "Aramburu", "fedearamburu95@gmail.com"));
			userRepository.save(buildUserAdmin(15L, "Damian", "Bruque", "daamiale.recoil@gmail.com"));
			userRepository.save(buildUserAdmin(16L, "JuanMa", "Davila", "juanirppr@gmail.com"));
			userRepository.save(buildUserAdmin(17L, "Leandro", "Gomez", "le.rego17@gmail.com"));
			userRepository.save(buildUserAdmin(18L, "Fernando", "Benite", "benitezf.3105@gmail.com"));
			userRepository.save(buildUserAdmin(19L, "Magali", "Kain", "kain.magali@gmail.com"));
			userRepository.save(buildUserAdmin(20L, "AlkemyAdmin", "Generics", "AlkemyAdmin-Generics@gmail.com"));
		}
	}

    private User buildUser(long id, String firstName, String lastName, String email ) {
        return new User(id, firstName, lastName, email, bCryptPasswordEncoder.encode(PASSWORD_GENERIC), IMAGE,
        		TimestampUtil.getCurrentTime(),false, roleRepository.findById(ROLE_USER).get());
    }
    
    private User buildUserAdmin(long id, String firstName, String lastName, String email) {
        return new User(id, firstName, lastName, email, bCryptPasswordEncoder.encode(PASSWORD_GENERIC), IMAGE,
        		TimestampUtil.getCurrentTime(),false, roleRepository.findById(ROLE_ADMIN).get());
    }

}
