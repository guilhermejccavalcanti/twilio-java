/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.notify.v1.service.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.DateConverter;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBinding extends Resource {
    private static final long serialVersionUID = 19604548272115L;

    public enum BindingType {
        APN("apn"),
        GCM("gcm"),
        SMS("sms"),
        FCM("fcm"),
        FACEBOOK_MESSENGER("facebook-messenger"),
        ALEXA("alexa");

        private final String value;

        private BindingType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a BindingType from a string.
         * @param value string value
         * @return generated BindingType
         */
        @JsonCreator
        public static BindingType forValue(final String value) {
            return Promoter.enumFromString(value, BindingType.values());
        }
    }

    /**
     * Create a UserBindingFetcher to execute fetch.
     * 
     * @param pathServiceSid The service_sid
     * @param pathIdentity The identity
     * @param pathSid The sid
     * @return UserBindingFetcher capable of executing the fetch
     */
    public static UserBindingFetcher fetcher(final String pathServiceSid, 
                                             final String pathIdentity, 
                                             final String pathSid) {
        return new UserBindingFetcher(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Create a UserBindingDeleter to execute delete.
     * 
     * @param pathServiceSid The service_sid
     * @param pathIdentity The identity
     * @param pathSid The sid
     * @return UserBindingDeleter capable of executing the delete
     */
    public static UserBindingDeleter deleter(final String pathServiceSid, 
                                             final String pathIdentity, 
                                             final String pathSid) {
        return new UserBindingDeleter(pathServiceSid, pathIdentity, pathSid);
    }

    /**
     * Create a UserBindingCreator to execute create.
     * 
     * @param pathServiceSid The service_sid
     * @param pathIdentity The identity
     * @param bindingType The binding_type
     * @param address The address
     * @return UserBindingCreator capable of executing the create
     */
    public static UserBindingCreator creator(final String pathServiceSid, 
                                             final String pathIdentity, 
                                             final UserBinding.BindingType bindingType, 
                                             final String address) {
        return new UserBindingCreator(pathServiceSid, pathIdentity, bindingType, address);
    }

    /**
     * Create a UserBindingReader to execute read.
     * 
     * @param pathServiceSid The service_sid
     * @param pathIdentity The identity
     * @return UserBindingReader capable of executing the read
     */
    public static UserBindingReader reader(final String pathServiceSid, 
                                           final String pathIdentity) {
        return new UserBindingReader(pathServiceSid, pathIdentity);
    }

    /**
     * Converts a JSON String into a UserBinding object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return UserBinding object represented by the provided JSON
     */
    public static UserBinding fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserBinding.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a UserBinding object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return UserBinding object represented by the provided JSON
     */
    public static UserBinding fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, UserBinding.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String serviceSid;
    private final String credentialSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String notificationProtocolVersion;
    private final String endpoint;
    private final String identity;
    private final String bindingType;
    private final String address;
    private final List<String> tags;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private UserBinding(@JsonProperty("sid")
                        final String sid, 
                        @JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("service_sid")
                        final String serviceSid, 
                        @JsonProperty("credential_sid")
                        final String credentialSid, 
                        @JsonProperty("date_created")
                        final String dateCreated, 
                        @JsonProperty("date_updated")
                        final String dateUpdated, 
                        @JsonProperty("notification_protocol_version")
                        final String notificationProtocolVersion, 
                        @JsonProperty("endpoint")
                        final String endpoint, 
                        @JsonProperty("identity")
                        final String identity, 
                        @JsonProperty("binding_type")
                        final String bindingType, 
                        @JsonProperty("address")
                        final String address, 
                        @JsonProperty("tags")
                        final List<String> tags, 
                        @JsonProperty("url")
                        final URI url, 
                        @JsonProperty("links")
                        final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.serviceSid = serviceSid;
        this.credentialSid = credentialSid;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.notificationProtocolVersion = notificationProtocolVersion;
        this.endpoint = endpoint;
        this.identity = identity;
        this.bindingType = bindingType;
        this.address = address;
        this.tags = tags;
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The The sid.
     * 
     * @return The sid
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The The account_sid.
     * 
     * @return The account_sid
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The The service_sid.
     * 
     * @return The service_sid
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The The credential_sid.
     * 
     * @return The credential_sid
     */
    public final String getCredentialSid() {
        return this.credentialSid;
    }

    /**
     * Returns The The date_created.
     * 
     * @return The date_created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date_updated.
     * 
     * @return The date_updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The notification_protocol_version.
     * 
     * @return The notification_protocol_version
     */
    public final String getNotificationProtocolVersion() {
        return this.notificationProtocolVersion;
    }

    /**
     * Returns The The endpoint.
     * 
     * @return The endpoint
     */
    public final String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Returns The The identity.
     * 
     * @return The identity
     */
    public final String getIdentity() {
        return this.identity;
    }

    /**
     * Returns The The binding_type.
     * 
     * @return The binding_type
     */
    public final String getBindingType() {
        return this.bindingType;
    }

    /**
     * Returns The The address.
     * 
     * @return The address
     */
    public final String getAddress() {
        return this.address;
    }

    /**
     * Returns The The tags.
     * 
     * @return The tags
     */
    public final List<String> getTags() {
        return this.tags;
    }

    /**
     * Returns The The url.
     * 
     * @return The url
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The The links.
     * 
     * @return The links
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserBinding other = (UserBinding) o;

        return Objects.equals(sid, other.sid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(serviceSid, other.serviceSid) && 
               Objects.equals(credentialSid, other.credentialSid) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(notificationProtocolVersion, other.notificationProtocolVersion) && 
               Objects.equals(endpoint, other.endpoint) && 
               Objects.equals(identity, other.identity) && 
               Objects.equals(bindingType, other.bindingType) && 
               Objects.equals(address, other.address) && 
               Objects.equals(tags, other.tags) && 
               Objects.equals(url, other.url) && 
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            serviceSid,
                            credentialSid,
                            dateCreated,
                            dateUpdated,
                            notificationProtocolVersion,
                            endpoint,
                            identity,
                            bindingType,
                            address,
                            tags,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("serviceSid", serviceSid)
                          .add("credentialSid", credentialSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("notificationProtocolVersion", notificationProtocolVersion)
                          .add("endpoint", endpoint)
                          .add("identity", identity)
                          .add("bindingType", bindingType)
                          .add("address", address)
                          .add("tags", tags)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}