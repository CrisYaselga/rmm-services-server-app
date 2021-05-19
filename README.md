# rmm-services-server-app
Technical assesment Ninja RMM

# COMPILE PROJECT
1.- Download the repository project in URL: https://github.com/CrisYaselga/rmm-services-server-app

2.- Import maven project: rmm-services-server in any ID, its better in Spring Tool Suite 4

3.- In project edit file application.properties and change parameters of datasource:
	spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
	spring.datasource.username=postgres
	spring.datasource.password=Admin999
  
4.- For Compile, run  maven install whit Java version 11 or 12, and UTF 8 Encoding

5.- Validate that in server database exists a database whit name postgres and in has a public schema, if not exists create

# DEPLOY PROJECT
1.- Get file rmm-services-server-0.0.1-SNAPSHOT.jar in projectDirectory....\rmm-services-server\target

2.- Validate that java version in system run whit version 11 or 12

3.- Open Command Console and excecute this comand: java -jar rmm-services-server-0.0.1-SNAPSHOT.jar

# USE REST API  
1.- First you need create a Customer, this is avalible in POST enpoint: http://localhost:8080/customer 

whit ParamBody, (Example):
{
"customerAccount":"CF_YASELGA",
"customerName":"Cris",
"customerPassword":"Cris1704"
}

2.- Get Token to use others endpoints you must login in GET endpoint:  http://localhost:8080/customerLogin whit request param: customerAccount and customerPassword

Example: http://localhost:8080/customerLogin?customerAccount=CF_YASELGA&customerPassword=Cris1704

If credentials are ok rest service return data of Customer whit a token (Example):
{
    "customerId": 1,
    "customerAccount": "CF_YASELGA",
    "customerName": "Cris",
    "customerPassword": "Cris1704",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJybW1BUEkiLCJzdWIiOiJDRl9ZQVNFTEdBIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYyMTM2MjMxNCwiZXhwIjoxNjIxMzYyOTE0fQ.CZ1l5SE_6Q39DL7NslCPurvJeukOuK1AXSGwFLiHVuv0dmwWtHLsrpZSy7_sSMULxpGhJZOjqsnjtrbN1pV1yw"
}

3.- The Device endpoint is available at http://localhost:8080/device, for use this endpoint you should add Authorizathion in Header whit value of Token generated in step 2

Header request example:
GET /device?customerAccount=CF_YASELGA HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJybW1BUEkiLCJzdWIiOiJDRl9ZQVNFTEdBIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTYyMTM2MzEyOCwiZXhwIjoxNjIxMzYzNzI4fQ.--_DM9cJZzxmpnjSiQBfvndT-51mxscZyoCIpAYPUPHpu-g0T0up-7u_CGrGAR5dZWLJLzEA2JjbXNF-_MNxKA
Cache-Control: no-cache
Postman-Token: 81a8cdff-40cd-7c76-3f42-1e2d4fc10d1c

Operations: 

GET: For get devices of customer, you must be use this operation whit requestParam customerAccount Example http://localhost:8080/device?customerAccount=CF_YASELGA

POST: For add new device of customer, you must be use POST operation whit body param:
{
	"systemName":"Dispositivo G",
	"customerAccount":"CF_YASELGA",
	"deviceType":"Windows Workstation"
}

PUT: For modify a device of customer, you must be use PUT operation whit body param:
{
	"deviceId": 3,
	"systemName":"Dispositivo G",
	"customerAccount":"CF_YASELGA",
	"deviceType":"Windows Workstation"
}

DELETE: For delete device of customer, you must be use DELETE operation whit requestParam: dDevice Example: http://localhost:8080/device?idDevice=3

4.- The Customer Services endpoint is available at http://localhost:8080/customerService, for use this endpoint you should add Authorizathion in Header whit value of Token generated in step 2

Operations: 

GET: To get List of customer services, you must be use this operation whit requestParam customerAccount Example http://localhost:8080/customerService?customerAccount=CF_YASELGA

POST: To add new customer service, you must be use POST operation whit body param:
{
	"serviceName":"PSA",
	"customerAccount":"CF_YASELGA"
}

Note: List of services is available at GET endpoint:  http://localhost:8080/services, to use this endpoint dont need a Token

DELETE: For quit customer service, you must be use DELETE operation whit requestParams: customerAccount and nameService Example: http://localhost:8080/customerService?customerAccount=CF_YASELGA&nameService=Antivirus

5.- To calculate the total monthly cost of Customer its avalible in endpoint: http://localhost:8080/totalMonthlyCost in GET operation, this endpoint need Token of Authorizathion

Example: http://localhost:8080/totalMonthlyCost?customerAccount=CF_YASELGA

