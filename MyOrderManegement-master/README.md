# MyOrderManagement
Order Management software with five entities 
<br>**1-Product**<br> **2-Stock**<br> **3-Customer**<br> **4-Order**<br> **5-ProductOrder**

# Building the application:
- You can clone the source code or download the repository as a (.zip) file

## Prerequisites

Before getting started, ensure that you have the following installed on your machine:

- Java Development Kit (JDK) 20 or higher
- Maven
- Docker

## Getting Started

Follow the steps below to set up and run the project.

### Clone the Repository

Clone the project repository to your local machine using the following command:

git clone <repository_url>

### Build the Project

Navigate to the project directory:

cd <project_directory>

Build the Maven project:

mvn clean install

### Run the Application

Run the application using the following command:

java -jar target/<project_name>.jar

The application should now be running on http://localhost:8080.

### Add Postman Collection

To add the Postman collection for Swagger documentation, follow these steps:

1. Open Postman and click on "Import" to import a collection.
2. Select the Postman collection file provided (<collection_file_name>.json).
3. The Swagger documentation requests will be available in the imported collection.

### Containerize with Docker

To containerize the application using Docker, follow these steps:

1. Build the Docker image:

   
   docker build -t <image_name> .
   

2. Run the Docker container:

   
   docker run -p 8080:8080 <image_name>
   

   The application should now be running in a Docker container on http://localhost:8080.

# Project Journey 
- Create Github Repository
- Examine the Entities and Relationships structure of the database diagram
- Backend API Implementation for the following entities:
  -  Product
  -  Stock
  -  Customer
  -  Order
  -  ProductOrder
  -  (Repositories, Services, and Controllers) for all entities.
- Insert Swagger UI
- Include JWT in all APIs
- Develop postman request collections
- Produce a Docker image.

