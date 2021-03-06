/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.updater.api.v2010.account;

import com.twilio.rest.converter.Promoter;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.api.v2010.account.ConnectApp;
import com.twilio.rest.updater.Updater;

import java.net.URI;
import java.util.List;

public class ConnectAppUpdater extends Updater<ConnectApp> {
    private String accountSid;
    private final String sid;
    private URI authorizeRedirectUrl;
    private String companyName;
    private HttpMethod deauthorizeCallbackMethod;
    private URI deauthorizeCallbackUrl;
    private String description;
    private String friendlyName;
    private URI homepageUrl;
    private List<ConnectApp.Permission> permissions;

    /**
     * Construct a new ConnectAppUpdater.
     * 
     * @param sid The sid
     */
    public ConnectAppUpdater(final String sid) {
        this.sid = sid;
    }

    /**
     * Construct a new ConnectAppUpdater.
     * 
     * @param accountSid The account_sid
     * @param sid The sid
     */
    public ConnectAppUpdater(final String accountSid, 
                             final String sid) {
        this.accountSid = accountSid;
        this.sid = sid;
    }

    /**
     * The URL the user's browser will redirect to after Twilio authenticates the
     * user and obtains authorization for this Connect App..
     * 
     * @param authorizeRedirectUrl URIL Twilio sends requests when users authorize
     * @return this
     */
    public ConnectAppUpdater setAuthorizeRedirectUrl(final URI authorizeRedirectUrl) {
        this.authorizeRedirectUrl = authorizeRedirectUrl;
        return this;
    }

    /**
     * The URL the user's browser will redirect to after Twilio authenticates the
     * user and obtains authorization for this Connect App..
     * 
     * @param authorizeRedirectUrl URIL Twilio sends requests when users authorize
     * @return this
     */
    public ConnectAppUpdater setAuthorizeRedirectUrl(final String authorizeRedirectUrl) {
        return setAuthorizeRedirectUrl(Promoter.uriFromString(authorizeRedirectUrl));
    }

    /**
     * The company name set for this Connect App..
     * 
     * @param companyName The company name set for this Connect App.
     * @return this
     */
    public ConnectAppUpdater setCompanyName(final String companyName) {
        this.companyName = companyName;
        return this;
    }

    /**
     * The HTTP method to be used when making a request to the
     * `DeauthorizeCallbackUrl`..
     * 
     * @param deauthorizeCallbackMethod HTTP method Twilio WIll use making requests
     *                                  to the url
     * @return this
     */
    public ConnectAppUpdater setDeauthorizeCallbackMethod(final HttpMethod deauthorizeCallbackMethod) {
        this.deauthorizeCallbackMethod = deauthorizeCallbackMethod;
        return this;
    }

    /**
     * The URL to which Twilio will send a request when a user de-authorizes this
     * Connect App..
     * 
     * @param deauthorizeCallbackUrl URL Twilio will send a request when a user
     *                               de-authorizes this app
     * @return this
     */
    public ConnectAppUpdater setDeauthorizeCallbackUrl(final URI deauthorizeCallbackUrl) {
        this.deauthorizeCallbackUrl = deauthorizeCallbackUrl;
        return this;
    }

    /**
     * The URL to which Twilio will send a request when a user de-authorizes this
     * Connect App..
     * 
     * @param deauthorizeCallbackUrl URL Twilio will send a request when a user
     *                               de-authorizes this app
     * @return this
     */
    public ConnectAppUpdater setDeauthorizeCallbackUrl(final String deauthorizeCallbackUrl) {
        return setDeauthorizeCallbackUrl(Promoter.uriFromString(deauthorizeCallbackUrl));
    }

    /**
     * A more detailed human readable description of the Connect App..
     * 
     * @param description A more detailed human readable description
     * @return this
     */
    public ConnectAppUpdater setDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * A human readable name for the Connect App..
     * 
     * @param friendlyName A human readable name for the Connect App.
     * @return this
     */
    public ConnectAppUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The public URL where users can obtain more information about this Connect
     * App..
     * 
     * @param homepageUrl The URL users can obtain more information
     * @return this
     */
    public ConnectAppUpdater setHomepageUrl(final URI homepageUrl) {
        this.homepageUrl = homepageUrl;
        return this;
    }

    /**
     * The public URL where users can obtain more information about this Connect
     * App..
     * 
     * @param homepageUrl The URL users can obtain more information
     * @return this
     */
    public ConnectAppUpdater setHomepageUrl(final String homepageUrl) {
        return setHomepageUrl(Promoter.uriFromString(homepageUrl));
    }

    /**
     * The set of permissions that your ConnectApp requests..
     * 
     * @param permissions The set of permissions that your ConnectApp requests.
     * @return this
     */
    public ConnectAppUpdater setPermissions(final List<ConnectApp.Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    /**
     * The set of permissions that your ConnectApp requests..
     * 
     * @param permissions The set of permissions that your ConnectApp requests.
     * @return this
     */
    public ConnectAppUpdater setPermissions(final ConnectApp.Permission permissions) {
        return setPermissions(Promoter.listOfOne(permissions));
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated ConnectApp
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ConnectApp execute(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/ConnectApps/" + this.sid + ".json",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("ConnectApp update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return ConnectApp.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (authorizeRedirectUrl != null) {
            request.addPostParam("AuthorizeRedirectUrl", authorizeRedirectUrl.toString());
        }
        
        if (companyName != null) {
            request.addPostParam("CompanyName", companyName);
        }
        
        if (deauthorizeCallbackMethod != null) {
            request.addPostParam("DeauthorizeCallbackMethod", deauthorizeCallbackMethod.toString());
        }
        
        if (deauthorizeCallbackUrl != null) {
            request.addPostParam("DeauthorizeCallbackUrl", deauthorizeCallbackUrl.toString());
        }
        
        if (description != null) {
            request.addPostParam("Description", description);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (homepageUrl != null) {
            request.addPostParam("HomepageUrl", homepageUrl.toString());
        }
        
        if (permissions != null) {
            for (ConnectApp.Permission prop : permissions) {
                request.addPostParam("Permissions", prop.toString());
            }
        }
    }
}