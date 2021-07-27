package com.target.retail.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * error represents error code, message, developer message and more info for a
 * given api
 */
@ApiModel(description = "error represents error code, message, developer message and more info for a given api")
@Validated

public class ApiError {
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("developerMessage")
	private String developerMessage = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("moreInfo")
	@Valid
	private List<MoreInfo> moreInfo = new ArrayList<>();

	public ApiError code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Error Code represents a alpha-numeric error code received from the error
	 * 
	 * @return code
	 **/
	@ApiModelProperty(required = true, value = "Error Code represents a alpha-numeric error code received from the error")
	@NotNull

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ApiError developerMessage(String developerMessage) {
		this.developerMessage = developerMessage;
		return this;
	}

	/**
	 * Developer Message represents technical details about the error message
	 * 
	 * @return developerMessage
	 **/
	@ApiModelProperty(required = true, value = "Developer Message represents technical details about the error message")
	@NotNull

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public ApiError message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Message represents a textual description of a given error code
	 * 
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "Message represents a textual description of a given error code")
	@NotNull

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiError moreInfo(List<MoreInfo> moreInfo) {
		this.moreInfo = moreInfo;
		return this;
	}

	public ApiError addMoreInfoItem(MoreInfo moreInfoItem) {
		this.moreInfo.add(moreInfoItem);
		return this;
	}

	/**
	 * More details about the error as needed
	 * 
	 * @return moreInfo
	 **/
	@ApiModelProperty(required = true, value = "More details about the error as needed")
	@NotNull

	@Valid

	public List<MoreInfo> getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(List<MoreInfo> moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiError error = (ApiError) o;
		return Objects.equals(this.code, error.code) && Objects.equals(this.developerMessage, error.developerMessage)
				&& Objects.equals(this.message, error.message) && Objects.equals(this.moreInfo, error.moreInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, developerMessage, message, moreInfo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    developerMessage: ").append(toIndentedString(developerMessage)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    moreInfo: ").append(toIndentedString(moreInfo)).append("\n");
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
