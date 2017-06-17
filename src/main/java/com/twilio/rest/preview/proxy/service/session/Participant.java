/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.proxy.service.session;

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
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends Resource {
    private static final long serialVersionUID = 96793423733524L;

    public enum ParticipantType {
        SMS("sms"),
        VOICE("voice"),
        PHONE("phone");

        private final String value;

        private ParticipantType(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        /**
         * Generate a ParticipantType from a string.
         * @param value string value
         * @return generated ParticipantType
         */
        @JsonCreator
        public static ParticipantType forValue(final String value) {
            return Promoter.enumFromString(value, ParticipantType.values());
        }
    }

    /**
     * Create a ParticipantFetcher to execute fetch.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @param pathSid A string that uniquely identifies this Participant.
     * @return ParticipantFetcher capable of executing the fetch
     */
    public static ParticipantFetcher fetcher(final String pathServiceSid, 
                                             final String pathSessionSid, 
                                             final String pathSid) {
        return new ParticipantFetcher(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Create a ParticipantReader to execute read.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @return ParticipantReader capable of executing the read
     */
    public static ParticipantReader reader(final String pathServiceSid, 
                                           final String pathSessionSid) {
        return new ParticipantReader(pathServiceSid, pathSessionSid);
    }

    /**
     * Create a ParticipantCreator to execute create.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @param identifier The Participant's contact identifier, normally a phone
     *                   number.
     * @return ParticipantCreator capable of executing the create
     */
    public static ParticipantCreator creator(final String pathServiceSid, 
                                             final String pathSessionSid, 
                                             final String identifier) {
        return new ParticipantCreator(pathServiceSid, pathSessionSid, identifier);
    }

    /**
     * Create a ParticipantDeleter to execute delete.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @param pathSid A string that uniquely identifies this Participant.
     * @return ParticipantDeleter capable of executing the delete
     */
    public static ParticipantDeleter deleter(final String pathServiceSid, 
                                             final String pathSessionSid, 
                                             final String pathSid) {
        return new ParticipantDeleter(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Create a ParticipantUpdater to execute update.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     * @param pathSid A string that uniquely identifies this Participant.
     * @return ParticipantUpdater capable of executing the update
     */
    public static ParticipantUpdater updater(final String pathServiceSid, 
                                             final String pathSessionSid, 
                                             final String pathSid) {
        return new ParticipantUpdater(pathServiceSid, pathSessionSid, pathSid);
    }

    /**
     * Converts a JSON String into a Participant object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Participant object using the provided
     * ObjectMapper.
     * 
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Participant object represented by the provided JSON
     */
    public static Participant fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Participant.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String sessionSid;
    private final String serviceSid;
    private final String accountSid;
    private final String friendlyName;
    private final Participant.ParticipantType participantType;
    private final String identifier;
    private final String proxyIdentifier;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private Participant(@JsonProperty("sid")
                        final String sid, 
                        @JsonProperty("session_sid")
                        final String sessionSid, 
                        @JsonProperty("service_sid")
                        final String serviceSid, 
                        @JsonProperty("account_sid")
                        final String accountSid, 
                        @JsonProperty("friendly_name")
                        final String friendlyName, 
                        @JsonProperty("participant_type")
                        final Participant.ParticipantType participantType, 
                        @JsonProperty("identifier")
                        final String identifier, 
                        @JsonProperty("proxy_identifier")
                        final String proxyIdentifier, 
                        @JsonProperty("date_created")
                        final String dateCreated, 
                        @JsonProperty("date_updated")
                        final String dateUpdated, 
                        @JsonProperty("url")
                        final URI url, 
                        @JsonProperty("links")
                        final Map<String, String> links) {
        this.sid = sid;
        this.sessionSid = sessionSid;
        this.serviceSid = serviceSid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.participantType = participantType;
        this.identifier = identifier;
        this.proxyIdentifier = proxyIdentifier;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
    }

    /**
     * Returns The A string that uniquely identifies this Participant..
     * 
     * @return A string that uniquely identifies this Participant.
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The Session Sid..
     * 
     * @return Session Sid.
     */
    public final String getSessionSid() {
        return this.sessionSid;
    }

    /**
     * Returns The Service Sid..
     * 
     * @return Service Sid.
     */
    public final String getServiceSid() {
        return this.serviceSid;
    }

    /**
     * Returns The Account Sid..
     * 
     * @return Account Sid.
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The A human readable description of this resource.
     * 
     * @return A human readable description of this resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The The Type of this Participant.
     * 
     * @return The Type of this Participant
     */
    public final Participant.ParticipantType getParticipantType() {
        return this.participantType;
    }

    /**
     * Returns The The Participant's contact identifier, normally a phone number..
     * 
     * @return The Participant's contact identifier, normally a phone number.
     */
    public final String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns The What this Participant communicates with, normally a phone
     * number..
     * 
     * @return What this Participant communicates with, normally a phone number.
     */
    public final String getProxyIdentifier() {
        return this.proxyIdentifier;
    }

    /**
     * Returns The The date this Participant was created.
     * 
     * @return The date this Participant was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The The date this Participant was updated.
     * 
     * @return The date this Participant was updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The The URL of this resource..
     * 
     * @return The URL of this resource.
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The Nested resource URLs..
     * 
     * @return Nested resource URLs.
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

        Participant other = (Participant) o;

        return Objects.equals(sid, other.sid) && 
               Objects.equals(sessionSid, other.sessionSid) && 
               Objects.equals(serviceSid, other.serviceSid) && 
               Objects.equals(accountSid, other.accountSid) && 
               Objects.equals(friendlyName, other.friendlyName) && 
               Objects.equals(participantType, other.participantType) && 
               Objects.equals(identifier, other.identifier) && 
               Objects.equals(proxyIdentifier, other.proxyIdentifier) && 
               Objects.equals(dateCreated, other.dateCreated) && 
               Objects.equals(dateUpdated, other.dateUpdated) && 
               Objects.equals(url, other.url) && 
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            sessionSid,
                            serviceSid,
                            accountSid,
                            friendlyName,
                            participantType,
                            identifier,
                            proxyIdentifier,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("sessionSid", sessionSid)
                          .add("serviceSid", serviceSid)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("participantType", participantType)
                          .add("identifier", identifier)
                          .add("proxyIdentifier", proxyIdentifier)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}