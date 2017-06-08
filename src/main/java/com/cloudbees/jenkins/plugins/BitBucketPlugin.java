package com.cloudbees.jenkins.plugins;

import hudson.Plugin;
import hudson.model.Descriptor.FormException;

import java.io.IOException;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Plugin extension.
 *
 * @author Michael Pailloncy
 */
public class BitBucketPlugin extends Plugin {

    /** Indicates if the plugin is activated. */
    private String ignoredUser = "";

    public BitBucketPlugin() {

    }

    @DataBoundConstructor
    public BitBucketPlugin(String ignoredUser) {

        this.ignoredUser = ignoredUser;
    }

    @Override
    public void configure(StaplerRequest req, JSONObject formData)
            throws IOException, ServletException, FormException
    {

        super.configure(req, formData);
        // get activated value from system configuration save.
        this.setIgnoredUser(formData.getString(FIELD_IGNORED_USER));
        // serialize to XML
        this.save();
    }

    public String getIgnoredUser()
    {
        return this.ignoredUser;
    }

    public void setIgnoredUser(String ignoredUser)
    {

        this.ignoredUser = ignoredUser;
    }

    public static final String FIELD_IGNORED_USER = "bitbucketplugin_ignoreduser";
}