# SpringBoot Rest Service

# Features:

#1  Exception Handling
	This project demonstrates a design implementation of Exception handling and management.
	This is done across 3 layers : Controller , Service and DAO
	The DAO layer is expected to do some business validations ( by making a call to a stored procedure in oracle)
	So the stored procedure could return some validation error or pass successfully.
	This Validation error is modelled as a BusinessException which is of type Checked exception.
	This is why the DAO method declares that exception in its 'throws' clause.
	If we get a error from stored procedure - in code we EXPLICITLY throw the business exception.
	
	Also being a DAO method - we could run into some database system exceptions ( db down ) 
	All of the db interaction exceptions are handled by providing a try/catch block.
	We catch SQLException and then rethrow it as our own user defined ApplicationSystemException.
	This exception is a 'Runtime' exception.
	which is why we do NOT declare it in our throws clause.
	So this is how our DAO can throw two types of exceptions :
	Checked Business Exception 
	Unchecked User defined Exception.
	
	The 'Service' layer is pass through.
	It does nothing and simply percolates the exception to Controller.
	Since it will not handle the checked exception - it too is declaring that it 'throws' the checked exception.
	The service layer will also not handle the unchecked exception and simply allow it to pass through.
	
	The 'Controller' is going to handle both exceptions.
	Code will be written in a try / catch block.
	There will be two catch blocks : first catch will be for the checked Business Exception.
	Second catch block will be the 'generic catch all' Exception .
	
	If there are no exceptions then we will return a 201 from within the 'try' block itself.
	If DAO throws a Business exception we will reach the BusinessException catch block.
	We will then send a 400 Bad request Response
	If DAO throws a Application exception ( Runtime ) or if there is any other exception - it will 
	be caught in the 'generic catch all' 'Exception' block.
	From here we will return a 500 Internal Server error response back.
	
#2 Defining 
	This
	  
	
