-- Create multiple databases
CREATE DATABASE IF NOT EXISTS spring_state_machine;
CREATE DATABASE IF NOT EXISTS spring_reactive_state_machine;

-- Optional: Create a user and grant permissions
CREATE USER 'springuser'@'%' IDENTIFIED BY 'springpassword';
GRANT ALL PRIVILEGES ON spring_state_machine.* TO 'springuser'@'%';
GRANT ALL PRIVILEGES ON spring_reactive_state_machine.* TO 'springuser'@'%';
FLUSH PRIVILEGES;