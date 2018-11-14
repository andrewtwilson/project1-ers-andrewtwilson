package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
	private Integer reimbursementId = -1;
	private Double amount;
	private java.sql.Timestamp submitted;
	private java.sql.Timestamp resolved = null;
	private String description;
	private Integer author;
	private Integer resolver = null;
	private ReimbursementStatus status;
	private ReimbursementType type;

//	Called when adding a new reimbursement to the database
	public Reimbursement(Double amount, Timestamp submitted,
			String description, Integer author, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
//	Called when retrieving reimbursement from the database
	public Reimbursement(Integer reimbursementId, Double amount, Timestamp submitted, Timestamp resolved,
			String description, Integer author, Integer resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Integer getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(Integer reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(java.sql.Timestamp submitted) {
		this.submitted = submitted;
	}

	public java.sql.Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(java.sql.Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public Integer getResolver() {
		return resolver;
	}

	public void setResolver(Integer resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((reimbursementId == null) ? 0 : reimbursementId.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbursementId == null) {
			if (other.reimbursementId != null)
				return false;
		} else if (!reimbursementId.equals(other.reimbursementId))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

}
