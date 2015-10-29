===================================================================================================================
Setup Ambiente de Desenvolvimento - jCompany + AngularJS
===================================================================================================================

	Funcional nos sistemas operacionais Windows e Linux

===================================================================================================================
	Pré-Requisitos:

		Instalação do Weblogic e configuração do profile

			https://blogs.oracle.com/WebLogicServer/entry/oracle_weblogic_server_12_11

			Configurar o DataSource, apontando para: "jdbc/rhdemo"

		Instalação do jCompany 6.5.0 ou superior e configuração do Weblogic como Server

			http://www.powerlogic.org/download.html

		Instalação do node.js e configuração para executar via linha de comando

			https://nodejs.org/download/release/


===================================================================================================================

		1 - Checkout dos projetos via git (O Eclipse já está com o Plugin Git Instalado)

			$ git clone https://github.com/jCompanyFramework/jcompany.git


		2 - Compilar o framework - Instalar os projetos no repositório maven local

			Importar os projetos no Eclipse, via "Import Maven Project's"
				Caminho: ${DIR}/jcompany/

				Executar a rotina "Instalar Projeto no Repositório LOCAL"

			Acessar o diretório onde o projeto foi salvo

				${DIR}/jcompany/pom.xml
			
			$ clean install

			Observação: No diretório do jcompany, existe um pom.xml para build geral dos módulos

				 jcompany-bom 
				 jcompany-service 
 

		3 - Deploy do rhdemo-service no weblogic
		
			Executar o script de criação das tabelas do banco

				${DIR}/jcompany/rhdemo-service/banco.sql

			O build será executado no diretório do projeto (ou via eclipse)

				${DIR}/jcompany/rhdemo-service/pom.xml

			$ clean install -P weblogic-deploy -Dadminurl=t3://127.0.0.1:7001 -Dtargets=AdminServer -Duser=${USER WEBLOGIC} -Dpassword=${PASSWORD WEBLOGIC}


		4 - Deploy do rhdemo-spa

			O build será executado no diretório do projeto via linha de comando

				${DIR}/jcompany/rhdemo-spa/
		
				Comandos para instalar as dependências:

					$npm install -g gulp

					$npm install -g bower

				Comandos para atualizar dependencias

					$npm install

					$bower install

				Executar node em desenvolvimento:

					$gulp serve
 
