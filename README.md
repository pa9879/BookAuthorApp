# BookAuthorApp

1. Get a list of all authors

    REQUEST - GET 
  
    PATH - /authors

2. Get an author by id

    REQUEST - GET 
    
    PATH - /authors/{id}
    
3. Given an author id return his books
    
    REQUEST - GET 
    
    PATH - /authors/{id}/books
    
4. Get all the books
    
    REQUEST - GET 
    
    PATH - /books 

5. Add a new book
    
    REQUEST - POST
    
    PATH - /books
    
    Content Type - Application/JSON
     
     Request Body - Book Object

6. Add a new author
     
     REQUEST - POST
     
     PATH - /authors
     
     Content Type - Application/JSON
     
     Request Body - Author Object
