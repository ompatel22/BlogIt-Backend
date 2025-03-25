# BlogIt Web App - Backend

This is the backend repository for the Blogging Web App, built using Spring Boot.

## Features
- RESTful API for blog management
- Database integration with NeonDB
- Cloudinary support for image uploads

## Tech Stack
- **Spring Boot** - Backend framework
- **NeonDB** - Hosted PostgreSQL database
- **Cloudinary** - Image hosting and management
- **Java** - Primary programming language

## Getting Started

### Prerequisites
Make sure you have the following installed:
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/) (for dependency management)

### Environment Variables
Before running the project, create a `.env` file in the root directory and provide the following details:

```env
CLOUD_NAME=your_cloudinary_username
API_KEY=your_cloudinary_api_key
API_SECRET=your_cloudinary_api_secret
DS_USER=your_neon_db_username
DS_PASS=your_neon_db_password
