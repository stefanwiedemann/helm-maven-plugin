package com.kiwigrid.core.k8deployment.helmplugin;

import java.util.Arrays;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Mojo for packaging charts
 *
 * @author Fabian Schlegel
 * @since 06.11.17
 */
@Mojo(name = "package", defaultPhase = LifecyclePhase.PACKAGE)
public class PackageMojo extends AbstractHelmMojo {

	public void execute()
			throws MojoExecutionException
	{
		for (String inputDirectory : getChartDirectories(getChartDirectory())) {
			if(getExcludes() != null && Arrays.asList(getExcludes()).contains(inputDirectory)) {
				getLog().debug("Skip excluded directory " + inputDirectory);
				continue;
			}
			getLog().info("Packaging chart " + inputDirectory + "...");
			callCli(getHelmExecuteable()
					+ " package "
					+ inputDirectory
					+ " -d "
					+ getOutputDirectory(), "Unable to package chart at " + inputDirectory, true);
		}
	}
}
