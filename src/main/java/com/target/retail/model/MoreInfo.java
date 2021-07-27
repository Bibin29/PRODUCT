package com.target.retail.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * More details about the error as needed
 */
@ApiModel(description = "More details about the error as needed")
@Validated

public class MoreInfo {
	@JsonProperty("field")
	private String field = null;

	@JsonProperty("message")
	private String message = null;

	public MoreInfo field(String field) {
		this.field = field;
		return this;
	}

	/**
	 * Each exact property field that was validated
	 * 
	 * @return field
	 **/
	@ApiModelProperty(required = true, value = "Each exact property field that was validated")
	@NotNull

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public MoreInfo message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Each error message on the property level validation done by the API
	 * 
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "Each error message on the property level validation done by the API")
	@NotNull

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MoreInfo moreInfo = (MoreInfo) o;
		return Objects.equals(this.field, moreInfo.field) && Objects.equals(this.message, moreInfo.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(field, message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MoreInfo {\n");

		sb.append("    field: ").append(toIndentedString(field)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
