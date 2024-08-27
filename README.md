Scalable Tracking Number Generator API 

Tracking Number Generation Algo 

generate a UUID  

convert it to bytes 

encode those bytes to Base32 

Service Implementation 
spring boot application with jpa dependency to use jpa or hibernate for database interaction 

 

Controller -> receive and return the request, validate payload 

service -> for business logic 

util -> util class having method to return Unique Tracking Number 

Dao -> Interact with data base and save the generated number 

Response will be wrapped object containing Unique Tracking Number 

 

 

 

Database Design and Table Structure 

Master Tables 

lu_country 

lu_customer 

 

	Table Name: lu_country 

Columns 

Data Type 

country_id 

Int auto_increment not null 

country code (Alpha2) 

varchar 2 unique not null 

display_name 

varchar 255 not null 

audit columns (create_user,create_time , update user, update time) 

 

 

Table Name: lu_customer 

Columns 

Data Type 

customer_id 

Int auto_increment not null 

customer_name 

varchar 255 not null 

customer_slug 

varchar 255 unique not null 

audit columns 
(create_user,create_time , update user, update time) 

 

 Tracing details table 

tracking_details  

Columns 

Data Type 

order_id 

Int auto_increment not null 

origin_country_code 

varchar 2 not null 

destination_country_code 

varchar 2 not null 

weight 

double 

order_tracking_number 

varchar (16) unique 

customer_id 

foreign key with lu_customer 

audit columns (create_user,create_time , update user, update time) 

 

 

 

 

 

3. Tracking details table 

a. tracking_history 

Columns 

Data Type 

tracking_history_id 

Int auto_increment not null 

tracking_id 

foreign key with  

order_details 

tracking_status 

Enum (Cancelled, Delivered, InTransit, Processing, Returned) 

audit columns 

Create_user,create_time , comments 

 

Infra 

Create AWS Account 

Create an EC2 Instance 

Install java and SQL to the new Instance Created 

create MySQL user, connect to workbench, Run all DDL and DML 

upload the jar file and run it as service 

Run Redis server for caching 

We can increase or decrease the capacity based on traffic. 

Horizontal scaling and Load balancer to distribute the load. 

 

URL: http://localhost:8080/order/next-tracking-number 

Response:  

{ 

    "status": "SUCCESS", 

    "response": { 

        "createdAt": 2018-11-20T19:29:32+08:00, 

        "trackingNumber": "HWYY6GDEAJF3PNXY" 

    } 

} 

 

curl --location '52.65.105.95:8080/tracking/next-tracking-number?origin_country_id=IN&destination_country_id=IN&weight=1.223&created_at=2018-11-20T19%3A29%3A32+08%3A00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics'
