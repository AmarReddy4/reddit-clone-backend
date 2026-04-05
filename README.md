# Reddit Clone Backend

A backend API for a Reddit-like platform built with Spring Boot, featuring user authentication with JWT/OAuth2, subreddit management, posts, comments, voting, and email verification.

## Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring Security** with OAuth2 Resource Server & JWT
- **Spring Data JPA** (Hibernate)
- **Spring Mail** (Mailtrap for development)
- **MySQL** database
- **MapStruct** for object mapping
- **Lombok**
- **Maven**

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+
- SMTP service (Mailtrap or similar) for email verification

## Setup & Run

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd reddit-clone-backend
   ```

2. **Create the MySQL database**
   ```sql
   CREATE DATABASE `spring-reddit-clone`;
   ```

3. **Configure database and mail credentials**

   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/spring-reddit-clone
   spring.datasource.username=root
   spring.datasource.password=password

   spring.mail.host=smtp.mailtrap.io
   spring.mail.port=587
   spring.mail.username=<your-mailtrap-username>
   spring.mail.password=<your-mailtrap-password>
   ```

4. **Generate RSA keys** for JWT signing (place in `src/main/resources/`):
   ```bash
   openssl genrsa -out src/main/resources/app.key 2048
   openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
   ```

5. **Build and run**
   ```bash
   mvn spring-boot:run
   ```

   The application starts on `http://localhost:8080`.

## API Endpoints

### Authentication

| Method | Endpoint                                | Description                      | Auth Required |
|--------|-----------------------------------------|----------------------------------|---------------|
| POST   | `/api/auth/signup`                       | Register a new user              | No            |
| GET    | `/api/auth/accountVerification/{token}`  | Verify account via email token   | No            |
| POST   | `/api/auth/login`                        | Login and receive JWT            | No            |

### Subreddits

| Method | Endpoint              | Description              | Auth Required |
|--------|-----------------------|--------------------------|---------------|
| POST   | `/api/subreddit`       | Create a new subreddit   | Yes           |
| GET    | `/api/subreddit`       | List all subreddits      | Yes           |
| GET    | `/api/subreddit/{id}`  | Get subreddit by ID      | Yes           |

## Key Features

- **User Registration** with email verification (activation link sent via SMTP)
- **JWT Authentication** using RSA public/private key pairs
- **Subreddit Management** -- create and browse communities
- **Posts** with subreddit association, vote counts, and timestamps
- **Comments** on posts with user attribution
- **Voting System** with upvote/downvote support (VoteType enum)
- **MapStruct Mappers** for clean DTO-to-entity conversion

## Project Structure

```
src/main/java/com/reddit/clone/
├── DemoApplication.java              # Entry point
├── config/
│   ├── SecurityConfig.java           # Spring Security & OAuth2 config
│   └── SwaggerConfiguration.java     # Swagger/OpenAPI setup (placeholder)
├── controller/
│   ├── AuthController.java           # Auth endpoints
│   └── SubredditController.java      # Subreddit CRUD endpoints
├── dto/
│   ├── AuthenticationResponse.java   # Login response with JWT
│   ├── LoginRequest.java             # Login request payload
│   ├── RegisterRequest.java          # Registration payload
│   └── SubredditDto.java             # Subreddit data transfer object
├── exceptions/
│   ├── PostNotFoundException.java
│   ├── SpringRedditException.java
│   └── SubredditNotFoundException.java
├── mapper/
│   ├── CommentMapper.java            # Comment entity-DTO mapper
│   ├── PostMapper.java               # Post entity-DTO mapper
│   ├── SubredditMapper.java          # Subreddit entity-DTO mapper
│   └── SubredditMapperImpl.java      # Generated mapper implementation
├── model/
│   ├── Comment.java                  # Comment entity
│   ├── NotificationEmail.java        # Email notification model
│   ├── Post.java                     # Post entity
│   ├── RefreshToken.java             # Refresh token entity
│   ├── Subreddit.java                # Subreddit entity
│   ├── User.java                     # User entity
│   ├── VerificationToken.java        # Account verification token
│   ├── Vote.java                     # Vote entity
│   └── VoteType.java                 # Upvote/Downvote enum
├── repository/
│   ├── CommentRepository.java
│   ├── PostRepository.java
│   ├── RefreshTokenRepository.java
│   ├── SubredditRepository.java
│   ├── UserRepository.java
│   ├── VerificationTokenRepository.java
│   └── VoteRepository.java
├── security/
│   └── JwtProvider.java              # JWT token generation & validation
└── service/
    ├── AuthService.java              # Auth & verification logic
    ├── CommentService.java           # Comment operations (placeholder)
    ├── MailContentBuilder.java       # Email template builder
    ├── MailService.java              # Email sending service
    ├── PostService.java              # Post operations (placeholder)
    ├── RefreshTokenService.java      # Refresh token management
    ├── SubRedditService.java         # Subreddit business logic
    ├── UserDetailsServiceImpl.java   # Spring Security UserDetailsService
    └── VoteService.java              # Vote operations (placeholder)
```
