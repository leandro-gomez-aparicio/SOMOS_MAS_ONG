# README

### CODE STANDARD

- The interfaces should start with prefix "I". Example: IUserRepository.
- The names of the attributes for Java code use camel case, but the name for SQL use underscore.
- The name of the tables should be in singular.
- Exceptions should be handled by ErrorHandler class.

### KEEP IN MIND FOR PULL REQUEST AND CODE REVIEW

- The rule for the title is: "{ticket#}: {jiraTitle}".
- The commits should be: "{ticket#}: {commitDescription}".
- If you add a new endpoint, you should add at least the request and response. Also, you could add any other steps that 
are necessary for endpoint execution.

### SEED DATA USERS FOR TEST

##Id_role		Description
	1			ROLE_USER
	2			ROLE_ADMIN

##FirstName		Last Name 		Mail 								Id_role
Yalaury				Tobosa			User-Yobosa@gmail.com					1
Lucas				Cordoba			User-lucascordoba77@gmail.com			1
Juan Ignacio		Caballero		user-juan.ci.caballero@gmail.com		1
Federico			Aramburu		user-fedearamburu95@gmail.com			1
Damian				Bruque			user-daamiale.recoil@gmail.com			1
JuanMa				Davila			user-juanirppr@gmail.com				1
Leandro				Gomez			user-le.rego17@gmail.com				1
Fernando			Benitez			user-benitezf.3105@gmail.com			1
Magali				Kain			user-kain.magali@gmail.com				1
AlkemyUser			Generic			AlkemyUser-Generic@gmail.com			1

Yalaury				Tobosa			Yobosa@gmail.com						2
Lucas				Cordoba			lucascordoba77@gmail.com				2
Juan Ignacio		Caballero		juan.ci.caballero@gmail.com				2
Federico			Aramburu		fedearamburu95@gmail.com				2
Damian				Bruque			daamiale.recoil@gmail.com				2
JuanMa				Davila			juanirppr@gmail.com						2
Leandro				Gomez			le.rego17@gmail.com						2
Fernando			Benite			benitezf.3105@gmail.com					2
Magali				Kain			kain.magali@gmail.com					2
AlkemyAdmin			Generics		AlkemyAdmin-Generics@gmail.com			2
