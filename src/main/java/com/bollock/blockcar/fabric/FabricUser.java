package com.bollock.blockcar.fabric;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import java.io.Serializable;
import java.util.Set;

/**
 * TODO 클래스를 완성한다.
 * 패브릭 네트워크 초기 세팅을 위해
 * org.hyperledger.fabric.sdk.User를 implements한 클래스가 필요하다.
 */
public class FabricUser implements User, Serializable
{
	private static final long serialVersionUID = 8077132186383604355L;

	private String name;
	private Set<String> roles;
	private String account;
	private String affiliation;
	private Enrollment enrollment;
	private String mspId;

	public FabricUser() {
	}

	public FabricUser(String name, Set<String> roles, String account, String affiliation, Enrollment enrollment,
			String mspId) {
		this.name = name;
		this.roles = roles;
		this.account = account;
		this.affiliation = affiliation;
		this.enrollment = enrollment;
		this.mspId = mspId;
	}
	

	public FabricUser(String name, String affiliation, Enrollment enrollment, String mspId) {
		this.name = name;
		this.affiliation = affiliation;
		this.enrollment = enrollment;
		this.mspId = mspId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public String getMspId() {
		return mspId;
	}

	public void setMspId(String mspId) {
		this.mspId = mspId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
		return "FabricUser [name=" + name + ", roles=" + roles + ", account=" + account + ", affiliation=" + affiliation
				+ ", enrollment=" + enrollment + ", mspId=" + mspId + "]";
	}
	
}
