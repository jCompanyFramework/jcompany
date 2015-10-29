/*  																													
	    			       Jaguar-jCompany Developer Suite.																		
			    		        Powerlogic 2010-2014.

		Please read licensing information in your installation directory.Contact Powerlogic for more 
		information or contribute with this project: suporte@powerlogic.com.br - www.powerlogic.com.br																								
 */ 
package com.powerlogic.jcompany.core.rest.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * jCompany. Value Object. Encapsula dados de Perfil do usuÃ¡rio correntemente
 * autenticado na sessÃ£o.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlcAuthenticatedUserInfo implements Serializable {

	private static final long serialVersionUID = -472267141861819026L;

	public static final String PROPERTY = PlcAuthenticatedUserInfo.class.getName();

	/**
	 * Login do usuÃ¡rio autenticado
	 */
	private String login;

	/**
	 * Nome completo do usuÃ¡rio autenticado
	 */
	private String name;

	/**
	 * Apelido ou nome pelo qual o usuÃ¡rio Ã© conhecido.<br>
	 * O mÃ©todo <code>getDisplayName()</code> retorna o apelido, se
	 * preenchido, se nÃ£o, o nome.
	 * 
	 * @since jCompany 3.x
	 */
	private String nickname;

	/**
	 * Valor para timeout da sessÃ£o do usuÃ¡rio. Se informado, sobrepÃµe a
	 * informaÃ§Ã£o do web.xml.
	 * 
	 * @since jCompany 3.x
	 */
	private Long timeout;

	/**
	 * Email do usuÃ¡rio autenticado
	 */
	private String email;

	/**
	 * Identificador de empresa corrente do usuÃ¡rio autenticado em formato String
	 */
	private String company;

	/**
	 * Identificador para resetar a senha apÃ³s logar
	 */	
	private Boolean changePass;	

	private Map plcVerticalSecurity;

	/**
	 * IP de Origem
	 */
	private String ip;

	@XmlTransient
	private String host;


	/**
	 * Grupos (de usuÃ¡rios) dos quais o usuÃ¡rio Ã© membro.
	 */
	private List<String> groups = new ArrayList<String>();

	/**
	 * Roles do usuÃ¡rio.
	 */
	private List<String> roles = new ArrayList<String>();

	/**
	 * Filtros do usuÃ¡rio. Adicionar no padrÃ£o plcUsuarioPerfilVO.getFiltros().add("nomeFiltro#valor");
	 * @since jcompany 5.5
	 */
	private List<String> filters = new ArrayList<String>();

	/**
	 * Lista de horÃ¡rios obtidos da aplicaÃ§Ã£o, perfil e usuÃ¡rio
	 */
	private List schedule = new ArrayList();

	private Object appSchedule;

	private Object userSchedule;

	/**
	 * Mapa com as configuraÃ§Ãµes de acesso aos recursos da aplicaÃ§Ã£o.
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private Map<String, Object> resources = new HashMap<String, Object>();

	/**
	 * O acesso geral do usuÃ¡rio na aplicaÃ§Ã£o foi negado?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean accessDenied = false;

	/**
	 * O usuÃ¡rio autenticou com certificado?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean authCertificate;

	/**
	 * O usuÃ¡rio autenticou com certificado Ãºnico?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean personalCertificate;

	/**
	 * O usuÃ¡rio Ã© obrigado a estar autenticado com certificado para acessar a
	 * aplicaÃ§Ã£o?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean certificateRequired;

	/**
	 * O usuÃ¡rio Ã© obrigado a estar autenticado com certificado Ãºnico para
	 * acessar a aplicaÃ§Ã£o?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean uniqueCertificateRequired;

	/**
	 * O certificado atende Ã s necessidades para liberar acesso Ã  aplicaÃ§Ã£o?
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private boolean certificateSufficient;

	/**
	 * ExpressÃ£o regular para encontrar e extrair identificador no Certificado.
	 * Default = "CN=(.+),OU".
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private String searchPatternCertificate = "CN=(.+),OU";

	/**
	 * Dos grupos retornados pela execuÃ§Ã£o da expressÃ£o regular, qual utilizar?
	 * Default = 1.
	 * <p>
	 * Esta propriedade nÃ£o Ã© populada automaticamente pelo jCompany, a nÃ£o ser
	 * que o JSecurity esteja configurado, ficando a cargo do desenvolvedor
	 * optar por utilizÃ¡-la ou nÃ£o.
	 * </p>
	 * 
	 * @since jCompany 3.0
	 */
	private int searchGroupCertificate = 1;

	/**
	 * Flag indicando se o profile do usuÃ¡rio foi carregado com sucesso. Esse
	 * controle serve para evitar que o usuÃ¡rio tenha acesso total Ã  aplicaÃ§Ã£o
	 * se ocorrer alguma pane durante a configuraÃ§Ã£o do profile. Qualquer
	 * tentativa de acesso a qualquer recurso serÃ¡ negada.
	 * 
	 * @since jCompany 3.0
	 */
	private boolean profileLoaded;

	/**
	 * Inicializa mapa de filtros
	 */
	public PlcAuthenticatedUserInfo(){
		plcVerticalSecurity = new HashMap();
	}

	public PlcAuthenticatedUserInfo(String username, String host, List<String> roles)
	{
		this.login = username.toLowerCase();
		this.host = host;
		this.roles = roles;
	}


	/**
	 * Verifica se o usuÃ¡rio possui a role informada. Procura nos grupos do
	 * usuÃ¡rio e nas roles.
	 * 
	 * @param role
	 * @return
	 */
	public boolean isUserInRole(String role) {

		if (groups != null ) {
			Iterator<String> i = groups.iterator();
			while (i.hasNext()) {
				String g = i.next();
				if (g.equalsIgnoreCase(role)) {
					return true;
				}
			}
		}

		if (roles != null) {
			Iterator<String> i = roles.iterator();
			while (i.hasNext()) {
				String g = i.next();
				if (g.equalsIgnoreCase(role)) {
					return true;
				}
			}
		}

		return false;
	}

	public Map<String, Object> getResources() {
		return resources;
	}
	public void setResources(Map<String, Object> recursos) {
		this.resources = recursos;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}


	public List<String> getFilters() {
		return filters;
	}

	/**
	 * 
	 * Adicionar os Filtros na seguinte formataÃ§Ã£o: nomeDoFiltro#valorDoFiltro...
	 * @param filtros
	 */
	public void setFilters(List<String> filtros) {
		this.filters = filtros;
	}

	/**
	 * Maps com registro de classes e seguranÃ§a vertival para o usuÃ¡rios
	 * nestas classes, conforme padrÃ£o descrito no @see PlcPerfilUsuarioBO
	 */
	public java.util.Map getPlcVerticalSecurity(){
		return plcVerticalSecurity;
	}

	/**
	 * Lista com horÃ¡rios registrados para o usuÃ¡rio, perfil ou aplicaÃ§Ã£o
	 */
	public List getSchedule() {
		return schedule;
	}

	public void setSchedule(List horarios) {
		this.schedule = horarios;
	}

	public void setPlcVerticalSecurity(java.util.Map newVal){
		plcVerticalSecurity=newVal;
	}

	public List<String> getGroups() {
		return groups;
	}

	/**
	 * @param newVal
	 */
	public void setGroups(List<String> newVal) {
		groups=newVal;
	}

	/**
	 * jCompany 20.  Exibe login do usuÃ¡rio se nÃ£o for nulo
	 * @return login
	 */
	public String toString() {
		if (getLogin() != null || getIp() !=null)
			return getLogin()+" e IP: "+getIp();
		else if (getLogin() != null) {
			return getLogin()+" e IP desconhecido";
		} else
			return "anÃ´nimo";
	}

	/**
	 * @return Retorna o ip.
	 */
	public String getIp() {
		return this.ip;
	}
	/**
	 * @param ip O ip a ser definido.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the empresa.
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param empresa The empresa to set.
	 */
	public void setCompany(String empresa) {
		this.company = empresa;
	}


	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Returns the nome.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param nome The nome to set.
	 */
	public void setName(String nome) {
		this.name = nome;
	}


	public boolean isProfileLoaded() {
		return profileLoaded;
	}

	public void setProfileLoaded(boolean profileCarregadoComSucesso) {
		this.profileLoaded = profileCarregadoComSucesso;
	}

	public boolean isAuthCertificate() {
		return authCertificate;
	}

	public void setAuthCertificate(boolean certificadoAutenticado) {
		this.authCertificate = certificadoAutenticado;
	}

	public boolean isPersonalCertificate() {
		return personalCertificate;
	}

	public void setPersonalCertificate(boolean certificadoPessoal) {
		this.personalCertificate = certificadoPessoal;
	}

	public boolean isCertificateRequired() {
		return certificateRequired;
	}

	public void setCertificateRequired(boolean certificadoObrigatorio) {
		this.certificateRequired = certificadoObrigatorio;
	}

	/**
	 * @see {@link com.powerlogic.jcompany.commons.PlcBaseUsuarioPerfilVOcertificadoUnicoObrigatorio}
	 */
	public boolean isUniqueCertificateRequired() {
		return uniqueCertificateRequired;
	}

	/**
	 * @see com.powerlogic.jcompany.commons.PPlcBaseUsuarioPerfilVOertificadoUnicoObrigatorio
	 */
	public void setUniqueCertificateRequired(boolean certificadoUnicoObrigatorio) {
		this.uniqueCertificateRequired = certificadoUnicoObrigatorio;
	}

	public boolean isCertificateSufficient() {
		return certificateSufficient;
	}

	public void setCertificateSufficient(boolean certificadoSuficiente) {
		this.certificateSufficient = certificadoSuficiente;
	}

	public boolean isAccessDenied() {
		return accessDenied;
	}

	public void setAccessDenied(boolean acessoNegado) {
		this.accessDenied = acessoNegado;
	}

	public int getSearchGroupCertificate() {
		return searchGroupCertificate;
	}

	public void setSearchGroupCertificate(int certificadoBuscaGrupo) {
		this.searchGroupCertificate = certificadoBuscaGrupo;
	}

	public String getSearchPatternCertificate() {
		return searchPatternCertificate;
	}

	public void setSearchPatternCertificate(String certificadoPatternBusca) {
		this.searchPatternCertificate = certificadoPatternBusca;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String apelido) {
		this.nickname = apelido;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}


	public Object getAppSchedule() {
		return appSchedule;
	}


	public void setAppSchedule(Object horarioAplicacao) {
		this.appSchedule = horarioAplicacao;
	}


	public Object getUserSchedule() {
		return userSchedule;
	}


	public void setUserSchedule(Object horarioUsuario) {
		this.userSchedule = horarioUsuario;
	}

	public Boolean getChangePass() {
		return changePass;
	}

	public void setChangePass(Boolean changePass) {
		this.changePass = changePass;
	}

}