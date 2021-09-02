# README

### CODE STANDARD

- The interfaces should start with prefix "I". Example: IUserRepository.
- The names of the attributes for Java code use camel case, but the name for SQL use underscore.
- The name of the tables should be in singular.

### KEEP IN MIND FOR PULL REQUEST AND CODE REVIEW

- The rule for the title is: "{ticket#}: {jiraTitle}".
- The commits should be: "{ticket#}: {commitDescription}".
- If you add a new endpoint, you should add at least the request and response. Also, you could add any other steps that 
are necessary for endpoint execution.
