# codefellowship


This CodeFellowship is build to allow the user to login,view profile for this 
user and create posts.
1. /myprofile: a route to display user information.
2. /users/{id}: a route to  display the basic information of the user with a profile picture.
3. Add post creation and display functionality.
4. Handle non-whitelabel errors.


-----------------------------
This CodeFellowship is building to do a social media application where the users can create posts, follow other users, and view posts in their feed from the users they follow. The application must be designed in a way that prevents SQL injection and HTML injection in the user's posts.
To implement the required functionalities, the application needs to have the following endpoints:
- An endpoint for creating a new user account
- An endpoint for user login
- An endpoint for creating a new post
- An endpoint for viewing a user's profile
- An endpoint for following a user
- An endpoint for viewing the user's feed

This project is a CodeFellowship web application that allows users to sign up, log in, and view basic information in home page. It uses Spring Boot, Thymeleaf, and Spring Security for user authentication and database management.
It contains the following endpoint:
1. `GET /login`: This endpoint renders the login page.
2. `GET /signup`: This endpoint renders the signup page.
3. `POST /signup`: This endpoint receives a signup form submitted by a user.
4. `GET /`: This endpoint renders the home page.

