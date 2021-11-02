# chmod 400 "~/Documents/aws/ec2-okr-service-key-pair.cer"

echo "Excluindo diretório /build"
rm -rf build/

echo "Gerando arquivo .jar"
#./gradlew bootJar

echo "-----> Copiando arquivo 'execute_commands_on_ec2.sh' da máquina local para instancia ec2"
scp -i "ec2-okr-service-key-pair.cer" execute_commands_on_ec2.sh ec2-user@ec2-18-191-236-201.us-east-2.compute.amazonaws.com:/home/ec2-user

echo "-----> Copiando arquivo .jar da máquina local para instancia ec2"
scp -i "ec2-okr-service-key-pair.cer" build/libs/okr-service-0.0.1.jar ec2-user@ec2-18-191-236-201.us-east-2.compute.amazonaws.com:/home/ec2-user

echo "-----> Concedendo permissões para arquivo 'execute_commands_on_ec2.sh'"
ssh -i "ec2-okr-service-key-pair.cer" ec2-user@ec2-18-191-236-201.us-east-2.compute.amazonaws.com chmod +x execute_commands_on_ec2.sh

echo "-----> Conectando a instancia ec2 e iniciando o servidor usando o comando java -jar"
ssh -i "ec2-okr-service-key-pair.cer" ec2-user@ec2-18-191-236-201.us-east-2.compute.amazonaws.com ./execute_commands_on_ec2.sh
