@echo off
REM === Run multiple Spring Boot services ===
REM Open a new terminal window for each service

start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\ApiGateway && mvn spring-boot:run"
start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\AdminService && mvn spring-boot:run"
start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\HospitalService && mvn spring-boot:run"
start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\DoctorService && mvn spring-boot:run"
start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\PatientService && mvn spring-boot:run"
start cmd /k "cd /d C:\Users\Ascendion\IdeaProjects\HealthCareManagementSystem\ReviewService && mvn spring-boot:run"

echo All services started in separate windows.
pause
