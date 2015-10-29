# jcompany



Setup Ambiente de Desenvolvimento - jCompany + AngularJS

	Funcional nos sistemas operacionais Windows e Linux

	Pré-Requisitos:

		Instalação do Weblogic e configuração do profile
			https://blogs.oracle.com/WebLogicServer/entry/oracle_weblogic_server_12_11

			Configurar o DataSource, apontando para: "jdbc/rhdemo"

		Instalação do jCompany 6.5.0 ou superior e configuração do Weblogic como Server
			http://www.powerlogic.org/download.html

		Instalação do node.js 
			https://nodejs.org/download/release/



		Passo 1 - Checkout dos projetos via git (O Eclipse já está com o Plugin Git Instalado)

			$ git clone https://github.com/jCompanyFramework/jcompany.git

		Passo 2 - Compilar o framework - Instalar no repositório maven local

			Acessar o diretório onde o projeto foi salvo

				${DIR}/jcompany/pom.xml
			
			$ clean install

			Observação: No diretório do jcompany, existe um pom.xml para build geral dos módulos

				 jcompany-bom 
				 jcompany-service 
				 rhdemo-service 
 
		Passo 3 - Deploy do rhdemo-service no weblogic
		
			Executar o script de criação das tabelas do banco

				${DIR}/jcompany/rhdemo-service/pom.xml

			O build será executado no diretório do projeto (ou via eclipse)

				${DIR}/jcompany/rhdemo-service/pom.xml

			$ clean install -P weblogic-deploy -Dadminurl=t3://127.0.0.1:7001 -Dtargets=AdminServer -Duser=${USER WEBLOGIC} -Dpassword=${PASSWORD WEBLOGIC}



					 
