# data-management
This repository hosts the source code for a Spring Boot application designed to manage Data Collections and Data Files. It provides robust RESTful APIs for creating, updating, deleting, and retrieving data collections.

# Test case
1. Post Data
	a. Try to insert Null value for not null columns
	b. Try to give any extra field in the body
	c. Try to remove any null field from the body

2. Update Data
	a. Try to provide no RequestParam
	b. Try to provide only 1 RequestParam
	c. Try to provide 1 RequestParam with current value and 1 with new value
	d. Try to update the data present in the db

3. Delete Data
	a. Try to delete non-existence Key
	b. Try to delete the existence key
	c. Try to delete the same key again.

4. Make sure the api response are readable and understood according to the exceptions
